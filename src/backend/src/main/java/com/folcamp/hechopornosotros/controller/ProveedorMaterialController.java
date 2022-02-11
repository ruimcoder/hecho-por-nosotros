package com.folcamp.hechopornosotros.controller;

import com.folcamp.hechopornosotros.models.dto.ListProveedorMaterialDTO;
import com.folcamp.hechopornosotros.models.dto.ProveedorMaterialDTO;
import com.folcamp.hechopornosotros.models.dto.ProveedorMaterialNewDTO;
import com.folcamp.hechopornosotros.service.ProveedorMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
@RestController
@RequestMapping("/proveedor-material")
public class ProveedorMaterialController {

    @Autowired
    private ProveedorMaterialService proveedorMaterialService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ProveedorMaterialDTO>> index(@PathVariable Long id){
        return new ResponseEntity<>(proveedorMaterialService.getProMateriales(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProveedorMaterialDTO> delete(@PathVariable Long id) {
        return proveedorMaterialService.deleteProdMateriales(id);
    }
}
