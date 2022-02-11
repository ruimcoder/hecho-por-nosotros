package com.folcamp.hechopornosotros.service;

import com.folcamp.hechopornosotros.config.exceptions.NotFoundException;
import com.folcamp.hechopornosotros.models.dto.*;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoFileEntity;
import com.folcamp.hechopornosotros.models.entity.UserEmprendimientoEntity;
import com.folcamp.hechopornosotros.models.mapper.EmprendimientoMapper;
import com.folcamp.hechopornosotros.models.repositories.EmprendimientoFileRepository;
import com.folcamp.hechopornosotros.models.repositories.EmprendimientoRepository;
import com.folcamp.hechopornosotros.models.repositories.UserEmprendimientoRepository;
import com.folcamp.hechopornosotros.security.roles.RoleConstants;
import com.folcamp.hechopornosotros.util.UploadBeanImage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
@Service
public class EmprendimientoService {

    @Autowired
    private EmprendimientoRepository emprendimientoRepository;
    @Autowired
    private EmprendimientoMapper emprendimientoMapper;
    @Autowired
    private ContactoService contactoService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private EmprendimientoFileRepository emprendimientoFileRepository;
    @Autowired
    private UserEmprendimientoRepository userEmprendimientoRepository;

    public List<EmprendimientoDTO> getEmprendimientos(String token) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token.substring(7));
        String uid = decodedToken.getUid();

        if (decodedToken.getClaims().containsKey(RoleConstants.ROLE_SUPER)) {
            List<EmprendimientoEntity> emprendimientoEntities = emprendimientoRepository.findAll();
            List<EmprendimientoDTO> emprendimientoDTOS = new ArrayList<>();

            for (EmprendimientoEntity emprendimientoEntity : emprendimientoEntities) {
                emprendimientoDTOS.add(emprendimientoMapper.mapEmprendimientoDtoToEmprendimientoEntity(emprendimientoEntity));
            }
            return emprendimientoDTOS;
        }

        List<UserEmprendimientoEntity> userEmprendimientoEntities = userEmprendimientoRepository.findAllByUid(uid);

        List<EmprendimientoDTO> emprendimientoDTOS = new ArrayList<>();

        for (UserEmprendimientoEntity userEmprendimientoEntity : userEmprendimientoEntities) {
            emprendimientoDTOS.add(emprendimientoMapper.mapEmprendimientoDtoToEmprendimientoEntity(userEmprendimientoEntity.getEmprendimiento()));
        }
        return emprendimientoDTOS;


    }

    public ResponseEntity<EmprendimientoDetailDTO> getEmprendimiento(Long id) {

        if (!emprendimientoRepository.existsById(id)) {
            throw new RuntimeException("No se encontro emprendimiento");
        }
        EmprendimientoEntity emprendimientoEntity = emprendimientoRepository.findById(id).get();

        EmprendimientoDetailDTO emprendimientoDetailDTO = emprendimientoMapper.mapEmprendimientoEntityToEmprendimientoDetailDTO(emprendimientoEntity);

        return new ResponseEntity<>(emprendimientoDetailDTO, HttpStatus.OK);

    }

    public ResponseEntity<EmprendimientoDTO> createEmprendimiento(String token, EmprendimientoNewDTO emprendimientoNewDto) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token.substring(7));
        String uid = decodedToken.getUid();

        EmprendimientoEntity emprendimientoEntity = emprendimientoMapper.mapEmprendimientoNewDtoToEmprendimientoEntity(emprendimientoNewDto);
        emprendimientoEntity = emprendimientoRepository.save(emprendimientoEntity);
        UserEmprendimientoEntity userEmprendimientoEntity = new UserEmprendimientoEntity();
        userEmprendimientoEntity.setEmprendimiento(emprendimientoEntity);
        userEmprendimientoEntity.setUid(uid);
        userEmprendimientoRepository.save(userEmprendimientoEntity);
        if (emprendimientoNewDto.getUploadBeanImage().getType().equals("logo")) {
            storageService.storeImageEmprendimiento(emprendimientoNewDto.getUploadBeanImage(), emprendimientoEntity.getId());
        } else {
            throw new RuntimeException("El tipo de imagen no es logo");
        }

        if (emprendimientoNewDto.getPortada().getType().equals("portada")) {
            storageService.storeImageEmprendimiento(emprendimientoNewDto.getPortada(), emprendimientoEntity.getId());
        } else {
            throw new RuntimeException("El tipo de imagen no es portada");
        }

        EmprendimientoDTO emprendimientoDTO = emprendimientoMapper.mapEmprendimientoEntityToEmprendimientoDto(emprendimientoEntity);
        return new ResponseEntity<>(emprendimientoDTO, HttpStatus.OK);
    }

    public ResponseEntity<EmprendimientoDTO> deleteEmprendimiento(Long id) {
        Optional<EmprendimientoEntity> optionalEmprendimientoEntity = emprendimientoRepository.findById(id);

        if (optionalEmprendimientoEntity.isPresent()) {
            EmprendimientoEntity emprendimientoEntity = optionalEmprendimientoEntity.get();
            EmprendimientoDTO emprendimientoDto = emprendimientoMapper.mapEmprendimientoEntityToEmprendimientoDto(emprendimientoEntity);
            Optional<UserEmprendimientoEntity> userEmprendimientoEntityOptional = userEmprendimientoRepository.findByEmprendimiento(emprendimientoEntity);
            if(userEmprendimientoEntityOptional.isPresent()){
                userEmprendimientoRepository.delete(userEmprendimientoEntityOptional.get());
            }
            emprendimientoRepository.delete(emprendimientoEntity);
            return new ResponseEntity<>(emprendimientoDto, HttpStatus.OK);
        } else {
            throw new RuntimeException("No se encontro emprendimiento");
        }
    }

    public ResponseEntity<EmprendimientoNewDTO> editEmprendimiento(Long id, EmprendimientoNewDTO emprendimientoNewDto) {
        Optional<EmprendimientoEntity> optionalEmprendimientoEntity = emprendimientoRepository.findById(id);

        if (optionalEmprendimientoEntity.isPresent()) {
            EmprendimientoEntity emprendimientoEntity = optionalEmprendimientoEntity.get();
            if (emprendimientoNewDto.getUploadBeanImage().getType().equals("logo")) {
                updateLogo(emprendimientoNewDto.getUploadBeanImage(), id);
            }

            if (emprendimientoNewDto.getPortada().getType().equals("portada")) {
                updatePortada(emprendimientoNewDto.getPortada(), id);
            }
            emprendimientoMapper.mapEmprendimientoUpdateDtoToEmprendimientoEntity(emprendimientoEntity, emprendimientoNewDto);
            emprendimientoRepository.save(emprendimientoEntity);
            return new ResponseEntity<>(emprendimientoNewDto, HttpStatus.OK);
        } else {
            throw new RuntimeException("No se encontro emprendimineto");
        }
    }

    public ResponseEntity<List<CardDTO>> getCards(String token) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token.substring(7));
        String uid = decodedToken.getUid();

        List<EmprendimientoEntity> emprendimientoEntities;
        List<CardDTO> cardDTOS = new ArrayList<>();

        if (decodedToken.getClaims().containsKey(RoleConstants.ROLE_SUPER)) {
            emprendimientoEntities = emprendimientoRepository.findAll();
        }else{
            List<UserEmprendimientoEntity> userEmprendimientoEntities = userEmprendimientoRepository.findAllByUid(uid);
            emprendimientoEntities = new ArrayList<>();

            for(UserEmprendimientoEntity userEmprendimientoEntity: userEmprendimientoEntities){
                emprendimientoEntities.add(userEmprendimientoEntity.getEmprendimiento());
            }

        }

        for (EmprendimientoEntity emprendimientoEntity : emprendimientoEntities) {
            String logo = null;
            String portada = null;
            List<EmprendimientoFileEntity> emprendimientoFileEntities =
                    emprendimientoFileRepository.findByEmprendimientoEntityAndType(emprendimientoEntity, "logo");

            List<EmprendimientoFileEntity> emprendimientoFileEntities1 =
                    emprendimientoFileRepository.findByEmprendimientoEntityAndType(emprendimientoEntity, "portada");

            if (!emprendimientoFileEntities.isEmpty()) {
                logo = emprendimientoFileEntities.get(0).getFileEntity().getUrl();
            }

            if (!emprendimientoFileEntities1.isEmpty()) {
                portada = emprendimientoFileEntities1.get(0).getFileEntity().getUrl();
            }

            cardDTOS.add(emprendimientoMapper.mapEmprendimientoDtoToCardDTO(emprendimientoEntity, logo, portada));
        }

        return new ResponseEntity<>(cardDTOS, HttpStatus.OK);
    }

    private void updateLogo(UploadBeanImage uploadBeanImage, Long id) {
        EmprendimientoEntity emprendimientoEntity = emprendimientoRepository.getById(id);
        if (uploadBeanImage.getImage().startsWith("data:image")) {

            int i = 0;
            for (EmprendimientoFileEntity emprendimientoFileEntity : emprendimientoEntity.getEmprendimientoFileEntity()) {
                if (emprendimientoFileEntity.getType().equals("logo")) {

                    String name = emprendimientoFileEntity.getFileEntity().getName();
                    emprendimientoEntity.getEmprendimientoFileEntity().remove(i);
                    storageService.deleteFileToS3(name);
                    break;
                }
                i++;
            }
            storageService.storeImageEmprendimiento(uploadBeanImage, id);
        }
    }

    private void updatePortada(UploadBeanImage uploadBeanImage, Long id) {
        EmprendimientoEntity emprendimientoEntity = emprendimientoRepository.getById(id);
        if (uploadBeanImage.getImage().startsWith("data:image")) {

            int i = 0;
            for (EmprendimientoFileEntity emprendimientoFileEntity : emprendimientoEntity.getEmprendimientoFileEntity()) {
                if (emprendimientoFileEntity.getType().equals("portada")) {

                    String name = emprendimientoFileEntity.getFileEntity().getName();
                    emprendimientoEntity.getEmprendimientoFileEntity().remove(i);
                    storageService.deleteFileToS3(name);
                    break;
                }
                i++;
            }
            storageService.storeImageEmprendimiento(uploadBeanImage, id);
        }
    }

    public ResponseEntity<String> editPublicado(Long id) {

        Optional<EmprendimientoEntity> optionalEmprendimientoEntity = emprendimientoRepository.findById(id);
        if (!optionalEmprendimientoEntity.isPresent()) {
            throw new NotFoundException("No se encontró emprendimiento");
        }
        EmprendimientoEntity emprendimientoEntity = optionalEmprendimientoEntity.get();

        emprendimientoEntity.setPublicado(true);

        emprendimientoRepository.save(emprendimientoEntity);

        return new ResponseEntity("Publicado", HttpStatus.OK);

    }

    public ResponseEntity<CountsDTO> getContadores() {

        Long creados = emprendimientoRepository.count();
        Long publicados = emprendimientoRepository.countByPublicado(true);
        Long noPublicados = emprendimientoRepository.countByPublicado(false);

        CountsDTO countsDTO = new CountsDTO();
        countsDTO.setCreados(creados);
        countsDTO.setPublicados(publicados);
        countsDTO.setNoPublicados(noPublicados);

        return new ResponseEntity<>(countsDTO, HttpStatus.OK);
    }
}
