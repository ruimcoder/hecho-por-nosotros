package com.folcamp.hechopornosotros.models.mapper;

import com.folcamp.hechopornosotros.models.dto.*;
import com.folcamp.hechopornosotros.models.entity.MaterialesEntity;
import com.folcamp.hechopornosotros.models.entity.ProveedorEntity;
import com.folcamp.hechopornosotros.models.entity.ProveedorMaterialEntity;
import com.folcamp.hechopornosotros.models.entity.PuntoDeVentaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProveedorMapper {

    @Autowired
    private MaterialesMapper materialesMapper;

    public ProveedorDTO mapProveedorEntityToProveedorDTO(ProveedorEntity proveedorEntity) {
        return new ProveedorDTO(
                proveedorEntity.getId(),
                proveedorEntity.getNombre(),
                proveedorEntity.getDireccion(),
                proveedorEntity.getPais(),
                proveedorEntity.getCiudad(),
                proveedorEntity.getCp()
        );
    }

    public ProveedorEntity mapProveedorNewDtoToProveedorEntity(ProveedorNewDTO proveedorNewDTO) {
        ProveedorEntity proveedorEntity = new ProveedorEntity();

        proveedorEntity.setNombre(proveedorNewDTO.getNombre());
        proveedorEntity.setDireccion(proveedorNewDTO.getDireccion());
        proveedorEntity.setPais(proveedorNewDTO.getPais());
        proveedorEntity.setCiudad(proveedorNewDTO.getCiudad());
        proveedorEntity.setCp(proveedorNewDTO.getCp());
//        proveedorEntity.setMateriales(proveedorNewDTO.getMateriales());


        return proveedorEntity;
    }

    public void mapProveedorUpdateDtoToProveedorEntity(ProveedorEntity proveedorEntity, ProveedorNewDTO proveedorNewDTO) {
        proveedorEntity.setNombre(proveedorNewDTO.getNombre());
        proveedorEntity.setDireccion(proveedorNewDTO.getDireccion());
        proveedorEntity.setPais(proveedorNewDTO.getPais());
        proveedorEntity.setCiudad(proveedorNewDTO.getCiudad());
        proveedorEntity.setCp(proveedorNewDTO.getCp());
//        proveedorEntity.setMateriales(proveedorNewDTO.getMateriales());
    }

    public ProveedorDetailDTO mapProveedorEntityToProveedorDetailDTO(ProveedorEntity proveedorEntity) {
        ProveedorDetailDTO proveedorDetailDTO = new ProveedorDetailDTO();

        proveedorDetailDTO.setId(proveedorEntity.getId());
        proveedorDetailDTO.setNombre(proveedorEntity.getNombre());
        proveedorDetailDTO.setDireccion(proveedorEntity.getDireccion());
        proveedorDetailDTO.setPais(proveedorEntity.getPais());
        proveedorDetailDTO.setCiudad(proveedorEntity.getCiudad());
        proveedorDetailDTO.setCp(proveedorEntity.getCp());
        proveedorDetailDTO.setMateriales(getMateriales(proveedorEntity));
        proveedorDetailDTO.setEspecificar(getEspecificar(proveedorEntity));

        return proveedorDetailDTO;
    }

    private List<MaterialesDTO> getMateriales(ProveedorEntity proveedorEntity) {

        List<ProveedorMaterialEntity> proveedorMaterialEntities = proveedorEntity.getProveedorMaterialEntities();
        List<MaterialesDTO> materialesDTOS = new ArrayList<>();

        if (proveedorMaterialEntities.isEmpty()) {
            return null;
        }

        for (ProveedorMaterialEntity proveedorMaterialEntity : proveedorMaterialEntities) {
            materialesDTOS.add(materialesMapper.mapMaterialesEntityToMaterialesDTO(proveedorMaterialEntity.getMaterial()));
        }

        return materialesDTOS;
    }

    private String getEspecificar(ProveedorEntity proveedorEntity) {

        List<ProveedorMaterialEntity> proveedorMaterialEntities = proveedorEntity.getProveedorMaterialEntities();

        for (ProveedorMaterialEntity proveedorMaterialEntity : proveedorMaterialEntities) {
            if (proveedorMaterialEntity.getEspecificar() != null) {
                return proveedorMaterialEntity.getEspecificar();
            }
        }

        return null;
    }
}
