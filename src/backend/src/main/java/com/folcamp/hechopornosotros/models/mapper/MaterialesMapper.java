package com.folcamp.hechopornosotros.models.mapper;

import com.folcamp.hechopornosotros.models.dto.MaterialesDTO;
import com.folcamp.hechopornosotros.models.dto.MaterialesNewDTO;
import com.folcamp.hechopornosotros.models.entity.MaterialesEntity;
import org.springframework.stereotype.Component;

@Component
public class MaterialesMapper {
    public MaterialesDTO mapMaterialesEntityToMaterialesDTO(MaterialesEntity materialesEntity){
        return new MaterialesDTO(
                materialesEntity.getId(),
                materialesEntity.getNombre()
        );
    }

    public MaterialesEntity mapMaterialesNewDtoToMaterialesEntity(MaterialesNewDTO materialesNewDTO) {
        MaterialesEntity materialesEntity = new MaterialesEntity();

        materialesEntity.setNombre(materialesNewDTO.getTipo());

        return materialesEntity;
    }

    public void mapMaterialesUpdateDtoToMaterialesEntity(MaterialesEntity materialesEntity, MaterialesNewDTO materialesNewDTO) {
        materialesEntity.setNombre(materialesNewDTO.getTipo());
    }
}
