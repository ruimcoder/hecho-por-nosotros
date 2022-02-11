package com.folcamp.hechopornosotros.models.mapper;

import com.folcamp.hechopornosotros.models.dto.MetaDTO;
import com.folcamp.hechopornosotros.models.dto.MetaNewDTO;
import com.folcamp.hechopornosotros.models.dto.ProveedorNewDTO;
import com.folcamp.hechopornosotros.models.entity.MetaEntity;
import com.folcamp.hechopornosotros.models.entity.ProveedorEntity;
import org.springframework.stereotype.Component;

@Component
public class MetaMapper {
    public MetaDTO mapMetaEntityToMetaDTO(MetaEntity metaEntity){
        return new MetaDTO(
                metaEntity.getId(),
                metaEntity.getNombre()
        );
    }

    public MetaEntity mapMetaNewDtoToMetaEntity(MetaNewDTO metaNewDTO) {
        MetaEntity metaEntity = new MetaEntity();

        metaEntity.setNombre(metaNewDTO.getNombre());

        return metaEntity;
    }

    public void mapMetaUpdateDtoToMetaEntity(MetaEntity metaEntity, MetaNewDTO metaNewDTO) {
        metaEntity.setNombre(metaNewDTO.getNombre());
    }
}
