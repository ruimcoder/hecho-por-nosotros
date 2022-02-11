package com.folcamp.hechopornosotros.service;

import com.folcamp.hechopornosotros.config.exceptions.BadRequestException;
import com.folcamp.hechopornosotros.config.exceptions.NotFoundException;
import com.folcamp.hechopornosotros.models.dto.EmprendimientoMetaDTO;
import com.folcamp.hechopornosotros.models.dto.EmprendimientoMetaNewDTO;
import com.folcamp.hechopornosotros.models.dto.ListEmprendimientoMetaDTO;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoMetaEntity;
import com.folcamp.hechopornosotros.models.entity.MetaEntity;
import com.folcamp.hechopornosotros.models.mapper.EmprendimientoMetaMapper;
import com.folcamp.hechopornosotros.models.repositories.EmprendimientoMetaRepository;
import com.folcamp.hechopornosotros.models.repositories.EmprendimientoRepository;
import com.folcamp.hechopornosotros.models.repositories.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmprendimientoMetaService {
    @Autowired
    private EmprendimientoMetaRepository emprendimientoMetaRepository;
    @Autowired
    private EmprendimientoMetaMapper emprendimientoMetaMapper;
    @Autowired
    private EmprendimientoRepository emprendimientoRepository;
    @Autowired
    private MetaRepository metaRepository;

    public List<EmprendimientoMetaDTO> getEmpMetas(Long id) {
        List<EmprendimientoMetaEntity> emprendimientoMetaEntities = emprendimientoMetaRepository.findAll();
        Optional<EmprendimientoEntity> optionalEmprendimientoEntity = emprendimientoRepository.findById(id);
        if (!optionalEmprendimientoEntity.isPresent())
            throw new NotFoundException("No se encontro emprendimiento");
        EmprendimientoEntity emprendimientoEntity = optionalEmprendimientoEntity.get();
        List<EmprendimientoMetaDTO> emprendimientoMetaDTOS = new ArrayList<>();

        for (EmprendimientoMetaEntity emprendimientoMetaEntity : emprendimientoMetaEntities) {
            emprendimientoMetaDTOS.add(emprendimientoMetaMapper.mapEmprendimientoMetaDtoToEmprendimientoMetaEntity(emprendimientoMetaEntity));
        }
        return emprendimientoMetaDTOS;
    }


    public ResponseEntity<EmprendimientoMetaNewDTO> create(EmprendimientoMetaNewDTO emprendimientoMetaNewDTO) {

        if (!emprendimientoRepository.existsById(emprendimientoMetaNewDTO.getIdEmprendimiento())) {
            throw new BadRequestException("No existe el Emprendimiento con el ID especificado");
        }

        EmprendimientoEntity emprendimiento = emprendimientoRepository.findById(emprendimientoMetaNewDTO.getIdEmprendimiento()).get();
        List<EmprendimientoMetaEntity> emprendimientoMetaEntityList = new ArrayList<>();

        for (Long idMeta : emprendimientoMetaNewDTO.getMetas()) {

            if (!metaRepository.existsById(idMeta)) {
                throw new BadRequestException("No existe la Meta con el ID especificado");
            }

            if (emprendimientoMetaRepository.findByEmprendimientoAndMeta(emprendimientoRepository.getById(emprendimientoMetaNewDTO.getIdEmprendimiento()), metaRepository.getById(idMeta)).isPresent()) {
                throw new BadRequestException("El emprendimiento ya tiene esta Meta asignada");
            }

            MetaEntity meta = metaRepository.findById(idMeta).get();

            EmprendimientoMetaEntity emprendimientoMetaEntity = new EmprendimientoMetaEntity();
            emprendimientoMetaEntity.setMeta(meta);
            emprendimientoMetaEntity.setEmprendimiento(emprendimiento);

            emprendimientoMetaEntityList.add(emprendimientoMetaEntity);
        }

        if (emprendimientoMetaNewDTO.getComentarioMeta() != null){
            emprendimiento.setComentario_meta(emprendimientoMetaNewDTO.getComentarioMeta());
            emprendimientoRepository.save(emprendimiento);
        }

        emprendimientoMetaRepository.saveAll(emprendimientoMetaEntityList);

        return new ResponseEntity<>(emprendimientoMetaNewDTO, HttpStatus.OK);
    }

    public ResponseEntity<EmprendimientoMetaDTO> deleteEmpMeta(Long id) {
        Optional<EmprendimientoMetaEntity> optionalEmprendimientoMetaEntity = emprendimientoMetaRepository.findById(id);

        if (optionalEmprendimientoMetaEntity.isPresent()) {
            EmprendimientoMetaEntity emprendimientoMetaEntity = optionalEmprendimientoMetaEntity.get();
            EmprendimientoMetaDTO emprendimientoMetaDTO = emprendimientoMetaMapper.mapEmprendimientoMetaEntityToEmprendimientoMetaDTO(emprendimientoMetaEntity);
            emprendimientoMetaRepository.deleteById(id);
            return new ResponseEntity<>(emprendimientoMetaDTO, HttpStatus.OK);
        } else {
            throw new RuntimeException("no se encontro Emprendimiento Meta");
        }
    }

    public ResponseEntity<?> editEmpMeta(EmprendimientoMetaNewDTO emprendimientoMetaNewDTO) {
        Optional<EmprendimientoEntity> optionalEmprendimientoEntity = emprendimientoRepository.findById(emprendimientoMetaNewDTO.getIdEmprendimiento());

        if (optionalEmprendimientoEntity.isPresent()) {

            for (Long idMeta : emprendimientoMetaNewDTO.getMetas()) {
                if (!metaRepository.existsById(idMeta)) {
                    throw new BadRequestException("No existe la Meta con el ID especificado");
                }
            }

            EmprendimientoEntity emprendimientoEntity = optionalEmprendimientoEntity.get();
            List<EmprendimientoMetaEntity> emprendimientoMetaEntityList = new ArrayList<>();
            List<EmprendimientoMetaEntity> emprendimientoMetaEntities = emprendimientoEntity.getEmprendimientoMetaEntities();
            emprendimientoEntity.setEmprendimientoMetaEntities(null);

            emprendimientoMetaRepository.deleteAll(emprendimientoMetaEntities);

            for (Long idMeta : emprendimientoMetaNewDTO.getMetas()) {

                MetaEntity meta = metaRepository.findById(idMeta).get();

                EmprendimientoMetaEntity emprendimientoMetaEntity = new EmprendimientoMetaEntity();
                emprendimientoMetaEntity.setMeta(meta);
                emprendimientoMetaEntity.setEmprendimiento(emprendimientoEntity);

                emprendimientoMetaEntityList.add(emprendimientoMetaEntity);
            }

            emprendimientoEntity.setEmprendimientoMetaEntities(emprendimientoMetaEntityList);
            if (emprendimientoMetaNewDTO.getComentarioMeta() != null){
                emprendimientoEntity.setComentario_meta(emprendimientoMetaNewDTO.getComentarioMeta());
                emprendimientoRepository.save(emprendimientoEntity);
            }
            emprendimientoRepository.save(emprendimientoEntity);

            return new ResponseEntity<>(emprendimientoMetaNewDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no se encontró emprendimiento", HttpStatus.NOT_FOUND);
        }
    }
}


