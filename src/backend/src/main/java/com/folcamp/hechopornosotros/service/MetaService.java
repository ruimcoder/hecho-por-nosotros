package com.folcamp.hechopornosotros.service;

import com.folcamp.hechopornosotros.models.dto.MetaDTO;
import com.folcamp.hechopornosotros.models.dto.MetaNewDTO;
import com.folcamp.hechopornosotros.models.entity.MetaEntity;
import com.folcamp.hechopornosotros.models.mapper.MetaMapper;
import com.folcamp.hechopornosotros.models.repositories.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MetaService {
    @Autowired
    private MetaRepository metaRepository;
    @Autowired
    private MetaMapper metaMapper;

    public List<MetaDTO> getMetas(){
        List<MetaEntity> metaEntities = metaRepository.findAll();
        List<MetaDTO> metaDTOS = new ArrayList<>();

        for (MetaEntity metaEntity : metaEntities) {
            metaDTOS.add(metaMapper.mapMetaEntityToMetaDTO(metaEntity));
        }
        return metaDTOS;
    }

    public MetaDTO getMeta(Long id) {
        Optional<MetaEntity> optionalMetaEntity = metaRepository.findById(id);
        if (optionalMetaEntity.isPresent()) {
            MetaEntity metaEntity = optionalMetaEntity.get();
            MetaDTO metaDTO = metaMapper.mapMetaEntityToMetaDTO(metaEntity);

            return  metaDTO;
        } else {
            throw new RuntimeException("No se encontro Meta");
        }
    }

    public ResponseEntity<MetaNewDTO> createMeta(MetaNewDTO metaNewDTO) {
        MetaEntity metaEntity = metaMapper.mapMetaNewDtoToMetaEntity(metaNewDTO);
        metaRepository.save(metaEntity);
        return new ResponseEntity<>(metaNewDTO, HttpStatus.OK);
    }

    public ResponseEntity<MetaDTO> deleteMeta(Long id) {
        Optional<MetaEntity> optionalMetaEntity = metaRepository.findById(id);

        if (optionalMetaEntity.isPresent()) {
            MetaEntity metaEntity = optionalMetaEntity.get();
            MetaDTO metaDTO = metaMapper.mapMetaEntityToMetaDTO(metaEntity);
            metaRepository.deleteById(id);
            return new ResponseEntity<>(metaDTO, HttpStatus.OK);
        } else {
            throw new RuntimeException("No se encontro Meta");
        }
    }

    public ResponseEntity<?> editMeta(Long id, MetaNewDTO metaNewDTO) {
        Optional<MetaEntity> optionalMetaEntity = metaRepository.findById(id);

        if (optionalMetaEntity.isPresent()) {
            MetaEntity metaEntity = optionalMetaEntity.get();
            metaMapper.mapMetaUpdateDtoToMetaEntity(metaEntity, metaNewDTO);
            metaRepository.save(metaEntity);
            return new ResponseEntity<>(metaNewDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontro Meta", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> createDefaultMeta() {
        List<MetaEntity> metaEntities = metaRepository.findAll();
        String[] metas = {"Fin de la pobreza",
                "Hambre cero",
                "Salud y bienestar",
                "Educación de calidad",
                "Igualdad de genero",
                "Agua limpia y saneamiento",
                "Energia asquible y no contaminante",
                "Trabajo decente y crecimiento economico",
                "Industria, innovación e infraestructura",
                "Reducción de las desigualdades",
                "Ciudades y comunidades sostenibles",
                "Producción y consumo responsable",
                "Acción por el clima",
                "Vida submarina",
                "Vida de ecosistemas terrestres",
                "Paz, justicia e instituciones solidas",
                "Alianzas para lograr los objetivos"};
        if (metaEntities.isEmpty()){
            for (String meta : metas) {
                MetaEntity metaEntity = new MetaEntity();

                metaEntity.setNombre(meta);
                metaRepository.save(metaEntity);
            }
        }

        return new ResponseEntity<>( "Datos cargados",HttpStatus.OK);
    }

}
