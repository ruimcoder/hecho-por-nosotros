package com.folcamp.hechopornosotros.controller;

import com.folcamp.hechopornosotros.models.dto.ContactoDTO;
import com.folcamp.hechopornosotros.models.dto.ContactoNewDTO;
import com.folcamp.hechopornosotros.models.dto.EmprendimientoDTO;
import com.folcamp.hechopornosotros.models.dto.EmprendimientoNewDTO;
import com.folcamp.hechopornosotros.service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
@RestController
@RequestMapping("/contactos")
public class ContactoController {
    @Autowired
    private ContactoService contactoService;


    @GetMapping("")
    public ResponseEntity<List<ContactoDTO>> getContactos() {
        return new ResponseEntity<>(contactoService.getContactos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactoDTO> getContacto(@PathVariable Long id) {
        return new ResponseEntity<>(contactoService.getContacto(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ContactoNewDTO> createContacto(@RequestBody ContactoNewDTO contactoNewDTO) {
        return contactoService.createContacto(contactoNewDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContactoDTO> deleteContacto(@PathVariable Long id) {
        return contactoService.deleteContacto(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactoNewDTO> editContacto(@PathVariable Long id, @RequestBody ContactoNewDTO contactoNewDTO) {
        return contactoService.editContacto(id, contactoNewDTO);
    }
}
