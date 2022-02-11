package com.folcamp.hechopornosotros.models.mapper;

import com.folcamp.hechopornosotros.models.dto.EmprendimientoFileDTO;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoFileEntity;
import org.springframework.stereotype.Component;

@Component
public class EmprendimientoFileMapper {

    public EmprendimientoFileDTO mapEmprendimientoFileEntityToEmprendimientoFileDTO(EmprendimientoFileEntity emprendimientoFileEntity){
        return new EmprendimientoFileDTO(
                emprendimientoFileEntity.getFileEntity().getId(),
                emprendimientoFileEntity.getEmprendimientoEntity().getId(),
                emprendimientoFileEntity.getType()
        );
    }
}
