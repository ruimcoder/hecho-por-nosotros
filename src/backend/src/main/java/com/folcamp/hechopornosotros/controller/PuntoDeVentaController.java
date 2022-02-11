package com.folcamp.hechopornosotros.controller;

import com.folcamp.hechopornosotros.models.dto.PuntoDeVentaNewDTO;
import com.folcamp.hechopornosotros.models.dto.PuntoDeVentaDTO;
import com.folcamp.hechopornosotros.service.PuntoDeVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT,RequestMethod.OPTIONS})
@RestController
@RequestMapping("/puntoventa")
public class PuntoDeVentaController {

    @Autowired
    private PuntoDeVentaService puntoDeVentaService;

    @GetMapping("")
    public ResponseEntity<List<PuntoDeVentaDTO>> index(){
        return new ResponseEntity<>(puntoDeVentaService.getPuntosDeVenta(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuntoDeVentaDTO> show(@PathVariable Long id){
        return new ResponseEntity<>(puntoDeVentaService.getPuntoDeVenta(id),HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<PuntoDeVentaNewDTO> store(@RequestBody PuntoDeVentaNewDTO puntoDeVentaNewDTO) {
        return puntoDeVentaService.createPuntoDeVenta(puntoDeVentaNewDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PuntoDeVentaDTO> delete(@PathVariable Long id){
        return puntoDeVentaService.deletePuntoDeVenta(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PuntoDeVentaNewDTO> update(@PathVariable Long id, @RequestBody PuntoDeVentaNewDTO puntoDeVentaNewDTO) {
        return puntoDeVentaService.editPuntoDeVenta(id, puntoDeVentaNewDTO);
    }
}
