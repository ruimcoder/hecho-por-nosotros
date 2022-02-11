package com.folcamp.hechopornosotros.service;

import com.folcamp.hechopornosotros.config.exceptions.NotFoundException;
import com.folcamp.hechopornosotros.models.dto.PuntoDeVentaCardsDTO;
import com.folcamp.hechopornosotros.models.dto.PuntoDeVentaDTO;
import com.folcamp.hechopornosotros.models.dto.PuntoDeVentaNewDTO;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import com.folcamp.hechopornosotros.models.entity.PuntoDeVentaEntity;
import com.folcamp.hechopornosotros.models.entity.UserEmprendimientoEntity;
import com.folcamp.hechopornosotros.models.mapper.PuntoDeVentaMapper;
import com.folcamp.hechopornosotros.models.repositories.EmprendimientoRepository;
import com.folcamp.hechopornosotros.models.repositories.PuntoDeVentaRepository;
import com.folcamp.hechopornosotros.models.repositories.UserEmprendimientoRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
@Service
public class PuntoDeVentaService {
    @Autowired
    private PuntoDeVentaRepository puntoDeVentaRepository;
    @Autowired
    private PuntoDeVentaMapper puntoDeVentaMapper;
    @Autowired
    private EmprendimientoRepository emprendimientoRepository;
    @Autowired
    private UserEmprendimientoRepository userEmprendimientoRepository;

    public List<PuntoDeVentaDTO> getPuntosDeVenta() {
        List<PuntoDeVentaEntity> puntosDeVentaEntities = puntoDeVentaRepository.findAll();
        List<PuntoDeVentaDTO> puntosDeVentaDTOS = new ArrayList<>();

        for (PuntoDeVentaEntity puntoDeVentaEntity : puntosDeVentaEntities) {
            puntosDeVentaDTOS.add(puntoDeVentaMapper.mapPuntoDeVentaEntityToPuntoDeVentaDTO(puntoDeVentaEntity));
        }

        return puntosDeVentaDTOS;
    }

    public PuntoDeVentaDTO getPuntoDeVenta(Long id) {
        Optional<PuntoDeVentaEntity> optionalPuntoDeVentaEntity = puntoDeVentaRepository.findById(id);
        if (optionalPuntoDeVentaEntity.isPresent()) {
            PuntoDeVentaEntity puntoDeVentaEntity = optionalPuntoDeVentaEntity.get();
            PuntoDeVentaDTO puntoDeVentaDTO = puntoDeVentaMapper.mapPuntoDeVentaEntityToPuntoDeVentaDTO(puntoDeVentaEntity);

            return puntoDeVentaDTO;
        } else {
            throw new RuntimeException("No se encontro punto de venta");
        }
    }

    public ResponseEntity<PuntoDeVentaNewDTO> createPuntoDeVenta(PuntoDeVentaNewDTO puntoDeVentaNewDTO) {
        Optional<EmprendimientoEntity> optionalEmprendimientoEntity = emprendimientoRepository.findById(puntoDeVentaNewDTO.getEmprendimientoId());
        if (!optionalEmprendimientoEntity.isPresent()) {
            throw new NotFoundException("No se encontro emprendimiento");
        }

        EmprendimientoEntity emprendimientoEntity = optionalEmprendimientoEntity.get();
        PuntoDeVentaEntity puntoDeVentaEntity = puntoDeVentaMapper.mapPuntoDeVentaNewDtoToPuntoDeVentaEntity(puntoDeVentaNewDTO);
        puntoDeVentaEntity.setEmprendimiento(emprendimientoEntity);
        puntoDeVentaRepository.save(puntoDeVentaEntity);
        return new ResponseEntity<>(puntoDeVentaNewDTO, HttpStatus.OK);
    }

    public ResponseEntity<PuntoDeVentaDTO> deletePuntoDeVenta(Long id) {
        Optional<PuntoDeVentaEntity> optionalPuntoDeVentaEntity = puntoDeVentaRepository.findById(id);

        if (optionalPuntoDeVentaEntity.isPresent()) {
            PuntoDeVentaEntity puntoDeVentaEntity = optionalPuntoDeVentaEntity.get();
            PuntoDeVentaDTO puntoDeVentaDTO = puntoDeVentaMapper.mapPuntoDeVentaEntityToPuntoDeVentaDTO(puntoDeVentaEntity);
            puntoDeVentaRepository.deleteById(id);
            return new ResponseEntity<>(puntoDeVentaDTO, HttpStatus.OK);
        } else {
            throw new RuntimeException("No se encontro punto de venta");
        }
    }

    public ResponseEntity<PuntoDeVentaNewDTO> editPuntoDeVenta(Long id, PuntoDeVentaNewDTO puntoDeVentaNewDTO) {
        Optional<PuntoDeVentaEntity> optionalPuntoDeVentaEntity = puntoDeVentaRepository.findById(id);

        if (optionalPuntoDeVentaEntity.isPresent()) {
            PuntoDeVentaEntity puntoDeVentaEntity = optionalPuntoDeVentaEntity.get();
            puntoDeVentaMapper.mapPuntoDeVentaUpdateDtoToPuntoDeVentaEntity(puntoDeVentaEntity, puntoDeVentaNewDTO);
            puntoDeVentaRepository.save(puntoDeVentaEntity);
            return new ResponseEntity<>(puntoDeVentaNewDTO, HttpStatus.OK);
        } else {
            throw new RuntimeException("No se encontro punto de venta");
        }
    }

    public ResponseEntity<List<PuntoDeVentaCardsDTO>> findPuntoByEmprendimiento(String token) throws FirebaseAuthException {

        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token.substring(7));
        String uid = decodedToken.getUid();

        List<UserEmprendimientoEntity> userEmprendimientoEntities = userEmprendimientoRepository.findAllByUid(uid);

        List<PuntoDeVentaCardsDTO> puntoDeVentaCardsDTOS = new ArrayList<>();

        for (UserEmprendimientoEntity userEmprendimientoEntity:userEmprendimientoEntities){
            EmprendimientoEntity emprendimientoEntity = userEmprendimientoEntity.getEmprendimiento();

            List<PuntoDeVentaEntity> puntoDeVentaEntityList= emprendimientoEntity.getPuntosDeVenta();
            List<PuntoDeVentaDTO> puntoDeVentaDTOList = new ArrayList<>();

            for (PuntoDeVentaEntity puntoDeVentaEntity: puntoDeVentaEntityList){
                puntoDeVentaDTOList.add(puntoDeVentaMapper.mapPuntoDeVentaEntityToPuntoDeVentaDTO(puntoDeVentaEntity));
            }
            PuntoDeVentaCardsDTO puntoDeVentaCardsDTO = new PuntoDeVentaCardsDTO(emprendimientoEntity.getId(), puntoDeVentaDTOList);
            puntoDeVentaCardsDTOS.add(puntoDeVentaCardsDTO);
        }



        return new ResponseEntity<>(puntoDeVentaCardsDTOS, HttpStatus.OK);
    }
}
