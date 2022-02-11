package com.folcamp.hechopornosotros.security.roles;


import com.folcamp.hechopornosotros.security.models.SecurityProperties;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class RoleService {
//    @Autowired
//    FirebaseAuth firebaseAuth;

    @Autowired
    private SecurityProperties securityProps;

    public void addRole(String uid, String role) throws Exception {
        try {
//            UserRecord user = FirebaseAuth.getInstance().getUser(uid);
            Map<String, Object> claims = new HashMap<>();
//            user.getCustomClaims().forEach((k, v) -> claims.put(k, v));
            if (securityProps.getValidApplicationRoles().contains(role)) {
//                if (!claims.containsKey(role)) {
                    claims.put(role, true);
//                }
                FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
            } else {
                throw new Exception("Not a valid Application role, Allowed roles => "
                        + securityProps.getValidApplicationRoles().toString());
            }

        } catch (FirebaseAuthException e) {
            log.error("Firebase Auth Error ", e);
        }

    }

//    public void removeRole(String uid, String role) {
//        try {
//            UserRecord user = FirebaseAuth.getInstance().getUser(uid);
//            Map<String, Object> claims = new HashMap<>();
//            user.getCustomClaims().forEach((k, v) -> claims.put(k, v));
//            if (claims.containsKey(role)) {
//                claims.remove(role);
//            }
//            FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
//        } catch (FirebaseAuthException e) {
//            log.error("Firebase Auth Error ", e);
//        }
//    }
}
