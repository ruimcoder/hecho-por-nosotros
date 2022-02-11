package com.folcamp.hechopornosotros.controller;

import com.folcamp.hechopornosotros.models.dto.CertificadosNewDTO;
import com.folcamp.hechopornosotros.models.dto.InformacionDTO;
import com.folcamp.hechopornosotros.service.HistoriaTecnicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
@RestController
@RequestMapping("/informacion")
public class HistoriaTecnicaController {
    @Autowired
    private HistoriaTecnicaService historiaTecnicaService;

    @PostMapping("/historia")
    public ResponseEntity<InformacionDTO> createH(@RequestBody InformacionDTO informacionDTO) {
        return historiaTecnicaService.createHistoria(informacionDTO);
    }

    @PostMapping("/tecnica")
    public ResponseEntity<InformacionDTO> createT(@RequestBody InformacionDTO informacionDTO) {
        return historiaTecnicaService.createTecnica(informacionDTO);
    }

    @PutMapping("/historia/{id}")
    public ResponseEntity<InformacionDTO> updateH(@RequestBody InformacionDTO informacionDTO, @PathVariable Long id) {
        return historiaTecnicaService.updateHistoria(informacionDTO, id);
    }

    @PutMapping("/tecnica/{id}")
    public ResponseEntity<InformacionDTO> updateT(@RequestBody InformacionDTO informacionDTO, @PathVariable Long id) {
        return historiaTecnicaService.updateTecnica(informacionDTO, id);
    }
}
