package com.folcamp.hechopornosotros.controller;

import com.folcamp.hechopornosotros.models.dto.MaterialesDTO;
import com.folcamp.hechopornosotros.models.dto.MaterialesNewDTO;
import com.folcamp.hechopornosotros.service.MaterialesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
@RestController
@RequestMapping("/materiales")
public class MaterialesController {
    @Autowired
    private MaterialesService materialesService;

    @GetMapping("")
    public ResponseEntity<List<MaterialesDTO>> getMateriales(){
        return new ResponseEntity<>(materialesService.getMateriales(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialesDTO> getMateriales(@PathVariable Long id){
        return new ResponseEntity<>(materialesService.getMateriales(id),HttpStatus.OK);
    }

    @PostMapping("/default")
    public ResponseEntity<?> createDefaultMateriales() {
        return materialesService.createDefaultMateriales();
    }
}
