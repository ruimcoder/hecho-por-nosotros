package com.folcamp.hechopornosotros.controller;

import com.folcamp.hechopornosotros.models.dto.UserDTO;
import com.folcamp.hechopornosotros.models.dto.UserEmprendedorDTO;
import com.folcamp.hechopornosotros.service.EmprendimientoService;
import com.folcamp.hechopornosotros.service.UserEmprendedorService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
@RestController
@RequestMapping ("/user-emprendedor")
public class UserEmprendedorController {

    @Autowired
    private UserEmprendedorService userEmprendedorService;


    @GetMapping()
    public ResponseEntity<?> getUsers() throws FirebaseAuthException {
        return userEmprendedorService.index();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) throws FirebaseAuthException {
        return userEmprendedorService.show(id);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserEmprendedorDTO userEmprendedorDTO) throws FirebaseAuthException {
        return userEmprendedorService.store(userEmprendedorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws FirebaseAuthException {
        return userEmprendedorService.delete(id);
    }
}