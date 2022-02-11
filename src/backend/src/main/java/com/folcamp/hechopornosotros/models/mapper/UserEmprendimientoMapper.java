package com.folcamp.hechopornosotros.models.mapper;

import com.folcamp.hechopornosotros.models.dto.UserDTO;
import com.folcamp.hechopornosotros.models.dto.UserEmprendedorDTO;
import com.folcamp.hechopornosotros.models.entity.UserEmprendimientoEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEmprendimientoMapper {
    public UserEmprendedorDTO mapUserEmprendedorEntityToUserEmprendedorDTO(UserEmprendimientoEntity userEmprendimientoEntity, UserDTO userDTO){
        return new UserEmprendedorDTO(
                userEmprendimientoEntity.getId(),
                userEmprendimientoEntity.getUid(),
                userDTO
        );
    }

}
