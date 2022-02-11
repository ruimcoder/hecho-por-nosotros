package com.folcamp.hechopornosotros.controller;

import com.folcamp.hechopornosotros.models.dto.*;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import com.folcamp.hechopornosotros.security.roles.IsSuper;
import com.folcamp.hechopornosotros.service.EmprendimientoService;
import com.folcamp.hechopornosotros.service.ProveedorService;
import com.folcamp.hechopornosotros.service.PuntoDeVentaService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
@RestController
@RequestMapping("/emprendimientos")
public class EmprendimientoController {

    @Autowired
    private EmprendimientoService emprendimientoService;
    @Autowired
    private PuntoDeVentaService puntoDeVentaService;
    @Autowired
    private ProveedorService proveedorService;


    @GetMapping("")
    public ResponseEntity<List<EmprendimientoDTO>> getEmprendimientos(@RequestHeader ("Authorization") String token) throws FirebaseAuthException {
        return new ResponseEntity<>(emprendimientoService.getEmprendimientos(token), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprendimientoDetailDTO> getEmprendimiento(@PathVariable Long id) {
        return emprendimientoService.getEmprendimiento(id);
    }

    @PostMapping("")
    public ResponseEntity<EmprendimientoDTO> createEmprendimiento(@RequestHeader ("Authorization") String token, @RequestBody EmprendimientoNewDTO emprendimientoNewDto) throws FirebaseAuthException {
        return emprendimientoService.createEmprendimiento(token, emprendimientoNewDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmprendimientoDTO> deleteEmprendimiento(@PathVariable Long id) {
        return emprendimientoService.deleteEmprendimiento(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprendimientoNewDTO> editEmprendimiento(@PathVariable Long id, @RequestBody EmprendimientoNewDTO emprendimientoNewDto) {
        return emprendimientoService.editEmprendimiento(id, emprendimientoNewDto);
    }

    @GetMapping("/cards")
    public ResponseEntity<List<CardDTO>> getCard(@RequestHeader ("Authorization") String token) throws FirebaseAuthException {
        return emprendimientoService.getCards(token);
    }

    @GetMapping("/cards_puntoventa")
    public ResponseEntity<List<PuntoDeVentaCardsDTO>> getCardPuntoventa(@RequestHeader ("Authorization") String token) throws FirebaseAuthException {
        return puntoDeVentaService.findPuntoByEmprendimiento(token);
    }

    @GetMapping("/cards_proveedores")
    public ResponseEntity<List<ProveedorDetailCardsDTO>> getCardProveedores(@RequestHeader ("Authorization") String token) throws FirebaseAuthException {
        return proveedorService.findProveedorByEmprendimiento(token);
    }

    @PutMapping("/{id}/publicado")
    public ResponseEntity<String> editPublicado(@PathVariable Long id){
        return emprendimientoService.editPublicado(id);
    }

    @GetMapping("/counts")
    @IsSuper
    public ResponseEntity<CountsDTO> getContadores (){
        return emprendimientoService.getContadores();
    }

}
