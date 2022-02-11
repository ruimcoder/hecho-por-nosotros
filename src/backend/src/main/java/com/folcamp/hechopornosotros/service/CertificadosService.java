package com.folcamp.hechopornosotros.service;

import com.folcamp.hechopornosotros.models.dto.CertificadosNewDTO;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoFileEntity;
import com.folcamp.hechopornosotros.models.entity.FileEntity;
import com.folcamp.hechopornosotros.models.repositories.EmprendimientoFileRepository;
import com.folcamp.hechopornosotros.models.repositories.EmprendimientoRepository;
import com.folcamp.hechopornosotros.models.repositories.FileRepository;
import com.folcamp.hechopornosotros.util.UploadBeanImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CertificadosService {
    @Autowired
    private EmprendimientoRepository emprendimientoRepository;
    @Autowired
    private StorageService storageService;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private EmprendimientoFileRepository emprendimientoFileRepository;

    @Transactional
    public ResponseEntity<CertificadosNewDTO> create(CertificadosNewDTO certificadosNewDTO) {
        if (!emprendimientoRepository.existsById(certificadosNewDTO.getEmprendimientoId())) {
            throw new RuntimeException("No se encontro emprendimiento");
        }

        List<UploadBeanImage> uploadBeanImages = certificadosNewDTO.getUploadBeanImages();

        for (UploadBeanImage uploadBeanImage : uploadBeanImages) {
            if (!uploadBeanImage.getType().equals("certificado")) {
                throw new RuntimeException("No todos los elementos son del tipo certificado");
            }
        }

        for (UploadBeanImage uploadBeanImage : uploadBeanImages) {
            storageService.storeImageEmprendimiento(uploadBeanImage, certificadosNewDTO.getEmprendimientoId());
        }

        return new ResponseEntity<>(certificadosNewDTO, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<CertificadosNewDTO> update(CertificadosNewDTO certificadosNewDTO) {

        if (!emprendimientoRepository.existsById(certificadosNewDTO.getEmprendimientoId())) {
            throw new RuntimeException("No se encontro emprendimiento");
        }

        List<UploadBeanImage> uploadBeanImages = certificadosNewDTO.getUploadBeanImages();

        for (UploadBeanImage uploadBeanImage : uploadBeanImages) {
            if (!uploadBeanImage.getType().equals("certificado")) {
                throw new RuntimeException("No todos los elementos son del tipo certificado");
            }
        }

        EmprendimientoEntity emprendimientoEntity = emprendimientoRepository.getById(certificadosNewDTO.getEmprendimientoId());

        for (int i = emprendimientoEntity.getEmprendimientoFileEntity().size() - 1; i >= 0; i--) {

            boolean eliminar = true;

            if (emprendimientoEntity.getEmprendimientoFileEntity().get(i).getType().equals("certificado")) {

                for (UploadBeanImage uploadBeanImage : uploadBeanImages) {
                    if (uploadBeanImage.getImage().startsWith("https://")) {
                        if (emprendimientoEntity.getEmprendimientoFileEntity().get(i).getFileEntity().getUrl().equals(uploadBeanImage.getImage())) {
                            eliminar = false;
                            break;
                        }
                    }
                }
            } else {
                eliminar = false;
            }

            if (eliminar) {
                String name = emprendimientoEntity.getEmprendimientoFileEntity().get(i).getFileEntity().getName();
                emprendimientoEntity.getEmprendimientoFileEntity().remove(i);
                storageService.deleteFileToS3(name);
            }

        }

        emprendimientoRepository.save(emprendimientoEntity);

        for (UploadBeanImage uploadBeanImage : uploadBeanImages) {
            if (uploadBeanImage.getImage().startsWith("data:image")) {
                storageService.storeImageEmprendimiento(uploadBeanImage, certificadosNewDTO.getEmprendimientoId());
            }
        }

        return new ResponseEntity<>(certificadosNewDTO, HttpStatus.OK);
    }

}
