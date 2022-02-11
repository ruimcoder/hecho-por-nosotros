package com.folcamp.hechopornosotros.controller;

import com.folcamp.hechopornosotros.models.dto.ProveedorDTO;
import com.folcamp.hechopornosotros.models.dto.ProveedorNewDTO;
import com.folcamp.hechopornosotros.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
@RestController
@RequestMapping("/proveedor")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("")
    public ResponseEntity<List<ProveedorDTO>> getProveedores(){
        return new ResponseEntity<>(proveedorService.getProveedor(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> getProveedor(@PathVariable Long id){
        return new ResponseEntity<>(proveedorService.getProveedor(id),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ProveedorNewDTO> createProveedor(@RequestBody ProveedorNewDTO proveedorNewDTO) {
        return proveedorService.createProveedor(proveedorNewDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProveedorDTO> deleteProveedor(@PathVariable Long id) {
        return proveedorService.deleteProveedor(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorNewDTO> editProveedor(@PathVariable Long id, @RequestBody ProveedorNewDTO proveedorNewDTO) {
        return proveedorService.editProveedor(id, proveedorNewDTO);
    }
}
