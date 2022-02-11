package com.folcamp.hechopornosotros.service;

import com.folcamp.hechopornosotros.models.dto.MaterialesDTO;
import com.folcamp.hechopornosotros.models.dto.MaterialesNewDTO;
import com.folcamp.hechopornosotros.models.entity.MaterialesEntity;
import com.folcamp.hechopornosotros.models.entity.MetaEntity;
import com.folcamp.hechopornosotros.models.entity.ProveedorEntity;
import com.folcamp.hechopornosotros.models.mapper.MaterialesMapper;
import com.folcamp.hechopornosotros.models.repositories.MaterialesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialesService {
    @Autowired
    private MaterialesRepository materialesRepository;
    @Autowired
    private MaterialesMapper materialesMapper;


    public List<MaterialesDTO> getMateriales() {
        List<MaterialesEntity> materialesEntities = materialesRepository.findAll();
        List<MaterialesDTO> materialesDTOS = new ArrayList<>();

        for (MaterialesEntity materialesEntity : materialesEntities) {
            materialesDTOS.add(materialesMapper.mapMaterialesEntityToMaterialesDTO(materialesEntity));
        }
        return materialesDTOS;
    }

    public MaterialesDTO getMateriales(Long id) {
        Optional<MaterialesEntity> optionalMaterialesEntity = materialesRepository.findById(id);
        if (optionalMaterialesEntity.isPresent()) {
            MaterialesEntity materialesEntity = optionalMaterialesEntity.get();
            MaterialesDTO materialesDTO = materialesMapper.mapMaterialesEntityToMaterialesDTO(materialesEntity);

            return materialesDTO;
        } else {
            throw new RuntimeException("No se encontro material");
        }
    }

    public ResponseEntity<MaterialesNewDTO> createMateriales(MaterialesNewDTO materialesNewDTO) {
        MaterialesEntity materialesEntity = materialesMapper.mapMaterialesNewDtoToMaterialesEntity(materialesNewDTO);
        materialesRepository.save(materialesEntity);
        return new ResponseEntity<>(materialesNewDTO, HttpStatus.OK);
    }

    public ResponseEntity<MaterialesDTO> deleteMateriales(Long id){
        Optional<MaterialesEntity> optionalMaterialesEntity = materialesRepository.findById(id);

        if (optionalMaterialesEntity.isPresent()) {
            MaterialesEntity materialesEntity = optionalMaterialesEntity.get();
            MaterialesDTO materialesDTO = materialesMapper.mapMaterialesEntityToMaterialesDTO(materialesEntity);
            materialesRepository.deleteById(id);
            return new ResponseEntity<>(materialesDTO, HttpStatus.OK);
        } else {
            throw new RuntimeException("No se encontro material");
        }
    }

    public ResponseEntity<?> editMateriales(Long id, MaterialesNewDTO materialesNewDTO) {
        Optional<MaterialesEntity> optionalMaterialesEntity = materialesRepository.findById(id);

        if (optionalMaterialesEntity.isPresent()) {
            MaterialesEntity materialesEntity = optionalMaterialesEntity.get();
            materialesMapper.mapMaterialesUpdateDtoToMaterialesEntity(materialesEntity, materialesNewDTO);
            materialesRepository.save(materialesEntity);
            return new ResponseEntity<>(materialesNewDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No existe el material", HttpStatus.NOT_FOUND);

        }

    }

    public ResponseEntity<?> createDefaultMateriales() {
        List<MaterialesEntity> materialesEntities = materialesRepository.findAll();
        String[] materiales = {"Algodón",
                "Alpaca",
                "Botones",
                "Cremalleras",
                "Hilo",
                "Lana",
                "Madera",
                "Metal",
                "Seda",
                "Vicuña",
                "Tela",
                "Otros"};
        if (materialesEntities.isEmpty()){
            for (String material : materiales) {
                MaterialesEntity materialesEntity = new MaterialesEntity();

                materialesEntity.setNombre(material);
                materialesRepository.save(materialesEntity);
            }
        }

        return new ResponseEntity<>( "Datos cargados",HttpStatus.OK);
    }

}
