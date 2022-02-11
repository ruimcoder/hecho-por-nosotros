package com.folcamp.hechopornosotros.service;

import com.folcamp.hechopornosotros.models.dto.UserDTO;
import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    public List<UserDTO> findAll(){
        List<UserDTO> users = new ArrayList<>();
        ListUsersPage page = null;
        try {
            page = FirebaseAuth.getInstance().listUsers(null);
        } catch (FirebaseAuthException e) {
            return users;
        }
        while (page != null) {
            for (ExportedUserRecord user : page.getValues()) {
                users.add(new UserDTO(user));
            }
            page = page.getNextPage();
        }

        return users;
    }

    public UserDTO find(String uid) throws FirebaseAuthException {
        return new UserDTO(FirebaseAuth.getInstance().getUser(uid));
    }

    public UserDTO findByEmail(String email) throws FirebaseAuthException {
        return new UserDTO(FirebaseAuth.getInstance().getUserByEmail(email));
    }

    public UserDTO delete(String uid) throws FirebaseAuthException {
        UserDTO userDataVO = this.find(uid);
        FirebaseAuth.getInstance().deleteUser(uid);
        return userDataVO;
    }

    public UserDTO setClaims(String uid, Map<String, Object> claims) throws FirebaseAuthException {
        FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
        return new UserDTO(FirebaseAuth.getInstance().getUser(uid));
    }

}


