package com.folcamp.hechopornosotros.controller;

import com.folcamp.hechopornosotros.models.dto.UserDTO;
import com.folcamp.hechopornosotros.service.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
@RestController
@RequestMapping ("/users")
public class UserController {

@Autowired
private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDTO find(@PathVariable("uid") String uid) throws FirebaseAuthException {
        return userService.find(uid);
    }

    @PutMapping("/claims/{uid}")
    public UserDTO setClaims(@PathVariable(name = "uid") String uid, @RequestBody Map<String,Object> claims) throws FirebaseAuthException {
        return userService.setClaims(uid, claims);
    }

    @RequestMapping(value = "/delete/{uid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDTO delete(@PathVariable("uid") String uid) throws FirebaseAuthException{
        return userService.delete(uid);
    }
}


