package com.folcamp.hechopornosotros.models.mapper;

import com.folcamp.hechopornosotros.models.dto.EmprendimientoMetaDTO;
import com.folcamp.hechopornosotros.models.dto.EmprendimientoMetaNewDTO;
import com.folcamp.hechopornosotros.models.dto.MetaDTO;
import com.folcamp.hechopornosotros.models.dto.MetaNewDTO;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoMetaEntity;
import com.folcamp.hechopornosotros.models.entity.MetaEntity;
import org.springframework.stereotype.Component;

@Component
public class EmprendimientoMetaMapper {
    public EmprendimientoMetaDTO mapEmprendimientoMetaDtoToEmprendimientoMetaEntity(EmprendimientoMetaEntity emprendimientoMetaEntity){
        return new EmprendimientoMetaDTO(
                emprendimientoMetaEntity.getId(),
                emprendimientoMetaEntity.getMeta().getId(),
                emprendimientoMetaEntity.getEmprendimiento().getId()
        );
    }

    public EmprendimientoMetaEntity mapEmprendimientoMetaNewDtoToEmprendimientoMetaEntity(EmprendimientoMetaNewDTO emprendimientoMetaNewDTO) {
        EmprendimientoMetaEntity emprendimientoMetaEntity = new EmprendimientoMetaEntity();

        emprendimientoMetaEntity.setMeta(emprendimientoMetaEntity.getMeta());
        emprendimientoMetaEntity.setEmprendimiento(emprendimientoMetaEntity.getEmprendimiento());


        return emprendimientoMetaEntity;
    }

    public EmprendimientoMetaDTO mapEmprendimientoMetaEntityToEmprendimientoMetaDTO(EmprendimientoMetaEntity emprendimientoMetaEntity){
        return new EmprendimientoMetaDTO(
                emprendimientoMetaEntity.getId(),
                emprendimientoMetaEntity.getMeta().getId(),
                emprendimientoMetaEntity.getEmprendimiento().getId()

        );
    }

    public void mapEmprendimientoMetaUpdateDtoToEmprendimientoMetaEntity(EmprendimientoMetaEntity emprendimientoMetaEntity, EmprendimientoMetaNewDTO emprendimientoMetaNewDTO) {

        emprendimientoMetaEntity.setMeta(emprendimientoMetaEntity.getMeta());
        emprendimientoMetaEntity.setEmprendimiento(emprendimientoMetaEntity.getEmprendimiento());
    }
}
