package com.folcamp.hechopornosotros.service;

import com.folcamp.hechopornosotros.config.exceptions.BadRequestException;
import com.folcamp.hechopornosotros.config.exceptions.NotFoundException;
import com.folcamp.hechopornosotros.models.dto.ListProveedorMaterialDTO;
import com.folcamp.hechopornosotros.models.dto.ProveedorMaterialDTO;
import com.folcamp.hechopornosotros.models.dto.ProveedorMaterialNewDTO;
import com.folcamp.hechopornosotros.models.entity.MaterialesEntity;
import com.folcamp.hechopornosotros.models.entity.ProveedorEntity;
import com.folcamp.hechopornosotros.models.entity.ProveedorMaterialEntity;
import com.folcamp.hechopornosotros.models.mapper.ProveedorMaterialMapper;
import com.folcamp.hechopornosotros.models.repositories.MaterialesRepository;
import com.folcamp.hechopornosotros.models.repositories.ProveedorMaterialRepository;
import com.folcamp.hechopornosotros.models.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorMaterialService {
    @Autowired
    private ProveedorMaterialRepository proveedorMaterialRepository;
    @Autowired
    private ProveedorMaterialMapper proveedorMaterialMapper;
    @Autowired
    private MaterialesRepository materialesRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;


    public List<ProveedorMaterialDTO> getProMateriales(Long id) {
        List<ProveedorMaterialEntity> proveedorMaterialEntities = proveedorMaterialRepository.findAll();
            Optional <ProveedorEntity> optionalProveedorEntity = proveedorRepository.findById(id);
        if (!optionalProveedorEntity.isPresent())
            throw new NotFoundException("No se encontro proveedor");
        ProveedorEntity proveedorEntity = optionalProveedorEntity.get();
        List<ProveedorMaterialDTO> proveedorMaterialDTOS = new ArrayList<>();

        for (ProveedorMaterialEntity proveedorMaterialEntity : proveedorMaterialEntities) {
            proveedorMaterialDTOS.add(proveedorMaterialMapper.mapProveedorMaterialEntityToProveedorMaterialDTO(proveedorMaterialEntity));
        }
        return proveedorMaterialDTOS;
    }

    public ProveedorMaterialDTO getProdMaterial(Long id) {
        Optional<ProveedorMaterialEntity> optionalProveedorMaterialEntity = proveedorMaterialRepository.findById(id);
        if (optionalProveedorMaterialEntity.isPresent()) {
            ProveedorMaterialEntity proveedorMaterialEntity = optionalProveedorMaterialEntity.get();
            ProveedorMaterialDTO proveedorMaterialDTO = proveedorMaterialMapper.mapProveedorMaterialEntityToProveedorMaterialDTO(proveedorMaterialEntity);

            return proveedorMaterialDTO;
        } else {
            throw new RuntimeException("No se encontro material proveedor");
        }
    }

    private void createProdMateriales(ProveedorMaterialNewDTO proveedorMaterialNewDTO) {
        if (!materialesRepository.existsById(proveedorMaterialNewDTO.getId_material()))
            throw new BadRequestException("No existe el Material con el  ID especificado");
        if (!proveedorRepository.existsById(proveedorMaterialNewDTO.getId_proveedor()))
            throw new BadRequestException("No existe el Proveedor con el  ID especificado");
        if (proveedorMaterialRepository.findByMaterialAndProveedor(materialesRepository.getById(proveedorMaterialNewDTO.getId_material()), proveedorRepository.getById(proveedorMaterialNewDTO.getId_proveedor())).isPresent())
            throw new BadRequestException("El proveedor ya tiene este material asignado");
        MaterialesEntity material = materialesRepository.findById(proveedorMaterialNewDTO.getId_material()).get();
        ProveedorEntity proveedor = proveedorRepository.findById(proveedorMaterialNewDTO.getId_proveedor()).get();
        ProveedorMaterialEntity proveedorMaterialEntity = new ProveedorMaterialEntity();
        proveedorMaterialEntity.setMaterial(material);
        proveedorMaterialEntity.setProveedor(proveedor);
        if (material.getNombre().equals("Otros")) {
            proveedorMaterialEntity.setEspecificar(proveedorMaterialNewDTO.getEspecificar());
        }

        proveedorMaterialRepository.save(proveedorMaterialEntity);

        material.getMaterialProveedorEntity().add(proveedorMaterialEntity);
        materialesRepository.save(material);

    }

    public ResponseEntity<ListProveedorMaterialDTO> createArrayList(ListProveedorMaterialDTO listProveedorMaterialDTO) {
        for (ProveedorMaterialNewDTO proveedorMaterialNewDTO : listProveedorMaterialDTO.getProveedorMaterialNewDTOList()) {
            createProdMateriales(proveedorMaterialNewDTO);
        }
        return new  ResponseEntity <>(listProveedorMaterialDTO,HttpStatus.OK);
    }

    public ResponseEntity<ProveedorMaterialDTO> deleteProdMateriales(Long id) {
        Optional<ProveedorMaterialEntity> optionalProveedorMaterialEntity = proveedorMaterialRepository.findById(id);
        System.out.println("holas");

        if (optionalProveedorMaterialEntity.isPresent()) {
            ProveedorMaterialEntity proveedorMaterialEntity = optionalProveedorMaterialEntity.get();
            ProveedorMaterialDTO proveedorMaterialDTO = proveedorMaterialMapper.mapProveedorMaterialEntityToProveedorMaterialDTO(proveedorMaterialEntity);
            proveedorMaterialRepository.deleteById(id);
            return new ResponseEntity<>(proveedorMaterialDTO, HttpStatus.OK);
        } else {
            throw new RuntimeException("No se encontro material proveedor");
        }

    }
}
