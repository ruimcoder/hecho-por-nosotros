package com.folcamp.hechopornosotros.controller;

import com.folcamp.hechopornosotros.models.dto.MetaDTO;
import com.folcamp.hechopornosotros.models.dto.MetaNewDTO;
import com.folcamp.hechopornosotros.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
@RestController
@RequestMapping("/metas")
public class MetaController {

    @Autowired
    private MetaService metaService;

    @GetMapping("")
    public ResponseEntity<List<MetaDTO>> getMetas(){
        return new ResponseEntity<>(metaService.getMetas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetaDTO> getMeta(@PathVariable Long id){
        return new ResponseEntity<>(metaService.getMeta(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<MetaNewDTO> createMeta(@RequestBody MetaNewDTO metaNewDTO) {
        return metaService.createMeta(metaNewDTO);
    }

    @PostMapping("/default")
    public ResponseEntity<?> createDefaultMeta() {
       return metaService.createDefaultMeta();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MetaDTO> deleteMeta(@PathVariable Long id) {
        return metaService.deleteMeta(id);
    }
}
