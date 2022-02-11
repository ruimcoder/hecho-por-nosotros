package com.folcamp.hechopornosotros.controller;

import com.folcamp.hechopornosotros.models.dto.CertificadosNewDTO;
import com.folcamp.hechopornosotros.service.CertificadosService;
import com.folcamp.hechopornosotros.util.UploadBeanImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
@RestController
@RequestMapping("/certificados")
public class CertificadoController {

    @Autowired
    private CertificadosService certificadoService;

    @PostMapping("")
    public ResponseEntity<CertificadosNewDTO> create(@RequestBody CertificadosNewDTO certificadosNewDTO) {
        return certificadoService.create(certificadosNewDTO);
    }

    @PutMapping("")
    public ResponseEntity<CertificadosNewDTO> update(@RequestBody CertificadosNewDTO certificadosNewDTO) {
        return certificadoService.update(certificadosNewDTO);
    }
}
