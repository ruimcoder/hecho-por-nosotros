package com.folcamp.hechopornosotros.controller;

import com.folcamp.hechopornosotros.models.dto.*;
import com.folcamp.hechopornosotros.service.EmprendimientoMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
@RestController
@RequestMapping("/emprendimiento-meta")
public class EmprendimientoMetaController {
    @Autowired
    private EmprendimientoMetaService emprendimientoMetaService;

    @GetMapping("/{id}")
    public ResponseEntity<List<EmprendimientoMetaDTO>> getEmpMetas(@PathVariable Long id){
        return new ResponseEntity<>(emprendimientoMetaService.getEmpMetas(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createEmpMeta(@RequestBody EmprendimientoMetaNewDTO emprendimientoMetaNewDTO) {
        return emprendimientoMetaService.create(emprendimientoMetaNewDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmprendimientoMetaDTO> deleteEmpMeta(@PathVariable Long id) {
        return emprendimientoMetaService.deleteEmpMeta(id);
    }

    @PutMapping("")
    public ResponseEntity<?> editEmpMeta(@RequestBody EmprendimientoMetaNewDTO emprendimientoMetaNewDTO) {
        return emprendimientoMetaService.editEmpMeta(emprendimientoMetaNewDTO);
    }

}
