package com.folcamp.hechopornosotros.service;

import com.folcamp.hechopornosotros.config.exceptions.BadRequestException;
import com.folcamp.hechopornosotros.config.exceptions.NotFoundException;
import com.folcamp.hechopornosotros.models.dto.InformacionDTO;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import com.folcamp.hechopornosotros.models.entity.InformacionEntity;
import com.folcamp.hechopornosotros.models.repositories.EmprendimientoRepository;
import com.folcamp.hechopornosotros.models.repositories.InformacionRepository;
import com.folcamp.hechopornosotros.util.UploadBeanImage;
import io.grpc.Grpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriaTecnicaService {
    @Autowired
    private EmprendimientoRepository emprendimientoRepository;
    @Autowired
    private StorageService storageService;
    @Autowired
    private InformacionRepository informacionRepository;

    @Transactional
    public ResponseEntity<InformacionDTO> createHistoria(InformacionDTO informacionDTO) {
        if (!emprendimientoRepository.existsById(informacionDTO.getEmprendimientoId())) {
            throw new RuntimeException("No se encontro emprendimiento");
        }

        EmprendimientoEntity emprendimientoEntity = emprendimientoRepository.findById(informacionDTO.getEmprendimientoId()).get();

        List<InformacionEntity> informacionEntities =
                informacionRepository.findByEmprendimientoEntityAndTipo(emprendimientoEntity, "historia");

        if (!informacionEntities.isEmpty()) {
            throw new RuntimeException("Ya existe historia para este emprendimiento");
        }

        if (!informacionDTO.getTipo().equals("historia")) {
            throw new RuntimeException("El tipo no es historia");
        }

        InformacionEntity informacionEntity = new InformacionEntity();
        informacionEntity.setEmprendimientoEntity(emprendimientoEntity);
        informacionEntity.setTexto(informacionDTO.getTexto());
        informacionEntity.setTipo(informacionDTO.getTipo());
        informacionEntity.setUrlVideo(informacionDTO.getUrlVideo());

        informacionRepository.save(informacionEntity);

        List<UploadBeanImage> uploadBeanImages = informacionDTO.getUploadBeanImages();

        for (UploadBeanImage uploadBeanImage : uploadBeanImages) {
            if (!uploadBeanImage.getType().equals("foto")) {
                throw new RuntimeException("No todos los elementos son del tipo foto");
            }
        }

        for (UploadBeanImage uploadBeanImage : uploadBeanImages) {
            storageService.storeImageInformacion(uploadBeanImage, informacionEntity.getId());
        }

        return new ResponseEntity<>(informacionDTO, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<InformacionDTO> createTecnica(InformacionDTO informacionDTO) {
        if (!emprendimientoRepository.existsById(informacionDTO.getEmprendimientoId())) {
            throw new RuntimeException("No se encontro emprendimiento");
        }

        EmprendimientoEntity emprendimientoEntity = emprendimientoRepository.findById(informacionDTO.getEmprendimientoId()).get();

        List<InformacionEntity> informacionEntities =
                informacionRepository.findByEmprendimientoEntityAndTipo(emprendimientoEntity, "tecnica");

        if (!informacionEntities.isEmpty()) {
            throw new RuntimeException("Ya existe tecnica para este emprendimiento");
        }

        if (!informacionDTO.getTipo().equals("tecnica")) {
            throw new RuntimeException("El tipo no es tecnica");
        }

        InformacionEntity informacionEntity = new InformacionEntity();
        informacionEntity.setEmprendimientoEntity(emprendimientoEntity);
        informacionEntity.setTexto(informacionDTO.getTexto());
        informacionEntity.setTipo(informacionDTO.getTipo());
        informacionEntity.setUrlVideo(informacionDTO.getUrlVideo());

        informacionEntity = informacionRepository.save(informacionEntity);

        List<UploadBeanImage> uploadBeanImages = informacionDTO.getUploadBeanImages();

        for (UploadBeanImage uploadBeanImage : uploadBeanImages) {
            if (!uploadBeanImage.getType().equals("foto")) {
                throw new RuntimeException("No todos los elementos son del tipo foto");
            }
        }

        for (UploadBeanImage uploadBeanImage : uploadBeanImages) {
            storageService.storeImageInformacion(uploadBeanImage, informacionEntity.getId());
        }

        return new ResponseEntity<>(informacionDTO, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<InformacionDTO> updateHistoria(InformacionDTO informacionDTO, Long informacionId) {


        if (!informacionDTO.getTipo().equals("historia")) {
            throw new BadRequestException("El tipo no es historia");
        }

        Optional<InformacionEntity> optionalInformacionEntity = informacionRepository.findById(informacionId);

        if (!optionalInformacionEntity.isPresent()) {
            throw new NotFoundException("No se encontro historia");
        }


        InformacionEntity informacionEntity = optionalInformacionEntity.get();

        if (!informacionEntity.getTipo().equals("historia")) {
            throw new BadRequestException("El tipo no es historia");
        }

        informacionEntity.setTexto(informacionDTO.getTexto());
        informacionEntity.setTipo(informacionDTO.getTipo());
        informacionEntity.setUrlVideo(informacionDTO.getUrlVideo());

        informacionRepository.save(informacionEntity);

        List<UploadBeanImage> uploadBeanImages = informacionDTO.getUploadBeanImages();

        for (UploadBeanImage uploadBeanImage : uploadBeanImages) {
            if (!uploadBeanImage.getType().equals("foto")) {
                throw new RuntimeException("No todos los elementos son del tipo foto");
            }
        }


        for (int i = informacionEntity.getFileInformacionEntities().size() - 1; i >= 0; i--) {

            boolean eliminar = true;

            if (informacionEntity.getFileInformacionEntities().get(i).getType().equals("foto")) {

                for (UploadBeanImage uploadBeanImage : uploadBeanImages) {
                    if (uploadBeanImage.getImage().startsWith("https://")) {
                        if (informacionEntity.getFileInformacionEntities().get(i).getFileEntity().getUrl().equals(uploadBeanImage.getImage())) {
                            eliminar = false;
                            break;
                        }
                    }
                }
            } else {
                eliminar = false;
            }

            if (eliminar) {
                String name = informacionEntity.getFileInformacionEntities().get(i).getFileEntity().getName();
                informacionEntity.getFileInformacionEntities().remove(i);
                storageService.deleteFileToS3(name);
            }
        }

        informacionRepository.save(informacionEntity);


        for (UploadBeanImage uploadBeanImage : uploadBeanImages) {
            if (uploadBeanImage.getImage().startsWith("data:image")) {
                storageService.storeImageInformacion(uploadBeanImage, informacionEntity.getId());
            }
        }

        return new ResponseEntity<>(informacionDTO, HttpStatus.OK);
    }

    public ResponseEntity<InformacionDTO> updateTecnica(InformacionDTO informacionDTO, Long informacionId) {

        if (!informacionDTO.getTipo().equals("tecnica")) {
            throw new BadRequestException("El tipo no es tecnica");
        }

        Optional<InformacionEntity> optionalInformacionEntity = informacionRepository.findById(informacionId);

        if (!optionalInformacionEntity.isPresent()) {
            throw new NotFoundException("No se encontro tecnica");
        }

        InformacionEntity informacionEntity = optionalInformacionEntity.get();

        if (!informacionEntity.getTipo().equals("tecnica")) {
            throw new BadRequestException("El tipo no es tecnica");
        }

        informacionEntity.setTexto(informacionDTO.getTexto());
        informacionEntity.setTipo(informacionDTO.getTipo());
        informacionEntity.setUrlVideo(informacionDTO.getUrlVideo());

        informacionRepository.save(informacionEntity);

        List<UploadBeanImage> uploadBeanImages = informacionDTO.getUploadBeanImages();

        for (UploadBeanImage uploadBeanImage : uploadBeanImages) {
            if (!uploadBeanImage.getType().equals("foto")) {
                throw new RuntimeException("No todos los elementos son del tipo foto");
            }
        }

        for (int i = informacionEntity.getFileInformacionEntities().size() - 1; i >= 0; i--) {

            boolean eliminar = true;

            if (informacionEntity.getFileInformacionEntities().get(i).getType().equals("foto")) {

                for (UploadBeanImage uploadBeanImage : uploadBeanImages) {
                    if (uploadBeanImage.getImage().startsWith("https://")) {
                        if (informacionEntity.getFileInformacionEntities().get(i).getFileEntity().getUrl().equals(uploadBeanImage.getImage())) {
                            eliminar = false;
                            break;
                        }
                    }
                }
            } else {
                eliminar = false;
            }

            if (eliminar) {
                String name = informacionEntity.getFileInformacionEntities().get(i).getFileEntity().getName();
                informacionEntity.getFileInformacionEntities().remove(i);
                storageService.deleteFileToS3(name);
            }
        }

        informacionRepository.save(informacionEntity);

        for (UploadBeanImage uploadBeanImage : uploadBeanImages) {
            if (uploadBeanImage.getImage().startsWith("data:image")) {
                storageService.storeImageInformacion(uploadBeanImage, informacionEntity.getId());
            }
        }

        return new ResponseEntity<>(informacionDTO, HttpStatus.OK);

    }
}
