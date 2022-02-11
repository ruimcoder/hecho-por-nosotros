package com.folcamp.hechopornosotros.models.mapper;

import com.folcamp.hechopornosotros.models.dto.InformacionFileDTO;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoFileEntity;
import com.folcamp.hechopornosotros.models.entity.InformacionEntity;
import com.folcamp.hechopornosotros.models.entity.InformacionFileEntity;
import com.folcamp.hechopornosotros.models.repositories.InformacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InformacionFileMapper {

    public InformacionFileDTO mapInformacionFileEntityToInformacionFileDTO(InformacionFileEntity informacionFileEntity) {
        return new InformacionFileDTO(
                informacionFileEntity.getFileEntity().getId(),
                informacionFileEntity.getInformacionEntity().getId(),
                informacionFileEntity.getType()
        );
    }

}
