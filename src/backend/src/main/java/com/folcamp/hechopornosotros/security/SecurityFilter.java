package com.folcamp.hechopornosotros.security;



import com.folcamp.hechopornosotros.security.models.Credentials;
import com.folcamp.hechopornosotros.security.models.SecurityProperties;
import com.folcamp.hechopornosotros.security.models.User;
import com.folcamp.hechopornosotros.security.roles.RoleConstants;
import com.folcamp.hechopornosotros.security.roles.RoleService;
import com.folcamp.hechopornosotros.security.utils.CookieUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    SecurityService securityService;

    @Autowired
    SecurityProperties restSecProps;

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    SecurityProperties securityProps;

    @Autowired
    RoleService securityRoleService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        verifyToken(request);
        filterChain.doFilter(request, response);
    }

    private void verifyToken(HttpServletRequest request) {
        String session = null;
        FirebaseToken decodedToken = null;
        Credentials.CredentialType type = null;
        boolean strictServerSessionEnabled = securityProps.getFirebaseProps().isEnableStrictServerSession();
        Cookie sessionCookie = cookieUtils.getCookie("session");
        String token = securityService.getBearerToken(request);
        //logger.info("Token: "+token);
        try {
            if (sessionCookie != null) {
                session = sessionCookie.getValue();
                decodedToken = FirebaseAuth.getInstance().verifySessionCookie(session,
                        securityProps.getFirebaseProps().isEnableCheckSessionRevoked());
                type = Credentials.CredentialType.SESSION;
            } else if (!strictServerSessionEnabled) {
                if (token != null && !token.equalsIgnoreCase("undefined")) {
                    decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
                    type = Credentials.CredentialType.ID_TOKEN;
                }
            }
        } catch (FirebaseAuthException e) {
            //e.printStackTrace();
            log.error("Firebase Exception:: ", e.getLocalizedMessage());
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        User user = firebaseTokenToUserDto(decodedToken);
        // Handle roles
        if (user != null) {
            // Handle Super Role
            if (securityProps.getSuperAdmins().contains(user.getEmail())) {
                if (!decodedToken.getClaims().containsKey(RoleConstants.ROLE_SUPER)) {
                    //System.out.println(decodedToken.getUid()+"  "+ RoleConstants.ROLE_SUPER);
                    try {
                        securityRoleService.addRole(decodedToken.getUid(), RoleConstants.ROLE_SUPER);
                    } catch (Exception e) {
                        log.error("Super Role registeration expcetion ", e);
                    }
                }
                authorities.add(new SimpleGrantedAuthority(RoleConstants.ROLE_SUPER));
            }
            // Handle Other roles
            decodedToken.getClaims().forEach((k, v) -> authorities.add(new SimpleGrantedAuthority(k)));
            // Set security context
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
                    new Credentials(type, decodedToken, token, session), authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

    }

    private User firebaseTokenToUserDto(FirebaseToken decodedToken) {
        User user = null;
        if (decodedToken != null) {
            user = new User();
            user.setUid(decodedToken.getUid());
            user.setName(decodedToken.getName());
            user.setEmail(decodedToken.getEmail());
            user.setPicture(decodedToken.getPicture());
            user.setIssuer(decodedToken.getIssuer());
            user.setEmailVerified(decodedToken.isEmailVerified());
        }
        return user;
    }
}
