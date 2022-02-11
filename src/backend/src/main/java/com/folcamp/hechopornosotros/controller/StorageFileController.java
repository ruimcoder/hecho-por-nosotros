package com.folcamp.hechopornosotros.controller;

import com.folcamp.hechopornosotros.models.dto.EmprendimientoFileDTO;
import com.folcamp.hechopornosotros.models.dto.InformacionFileDTO;
import com.folcamp.hechopornosotros.service.StorageService;
import com.folcamp.hechopornosotros.util.UploadBeanImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT})
@RestController
@RequestMapping("/s3")
public class StorageFileController {
    @Autowired
    StorageService storageService;

    @DeleteMapping("/delete/{keyName}")
    public ResponseEntity<String> delete(@PathVariable String keyName) {
        return new ResponseEntity<>(storageService.deleteFileToS3(keyName), HttpStatus.OK);
    }
}
