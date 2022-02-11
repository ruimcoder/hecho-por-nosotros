package com.folcamp.hechopornosotros.models.mapper;

import com.folcamp.hechopornosotros.models.dto.ProveedorMaterialDTO;
import com.folcamp.hechopornosotros.models.dto.ProveedorMaterialNewDTO;
import com.folcamp.hechopornosotros.models.dto.PuntoDeVentaNewDTO;
import com.folcamp.hechopornosotros.models.entity.MaterialesEntity;
import com.folcamp.hechopornosotros.models.entity.ProveedorEntity;
import com.folcamp.hechopornosotros.models.entity.ProveedorMaterialEntity;
import com.folcamp.hechopornosotros.models.entity.PuntoDeVentaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class ProveedorMaterialMapper {
    public ProveedorMaterialDTO mapProveedorMaterialEntityToProveedorMaterialDTO(ProveedorMaterialEntity proveedorMaterialEntity){
        return new ProveedorMaterialDTO(
                proveedorMaterialEntity.getId(),
                proveedorMaterialEntity.getMaterial().getId(),
                proveedorMaterialEntity.getProveedor().getId(),
                proveedorMaterialEntity.getEspecificar()
        );
    }

//    public ProveedorMaterialEntity mapProveedorMaterialNewDtoToProveedorMaterialEntity(ProveedorMaterialNewDTO proveedorMaterialNewDTO) {
//        ProveedorMaterialEntity proveedorMaterialEntity = new ProveedorMaterialEntity();
//
//        return proveedorMaterialEntity;
//    }

    public void mapProveedorMaterialUpdateDtoToProveedorMaterialEntity(ProveedorMaterialEntity proveedorMaterialEntity, ProveedorMaterialNewDTO proveedorMaterialNewDTO) {
        proveedorMaterialEntity.setMaterial(proveedorMaterialEntity.getMaterial());
        proveedorMaterialEntity.setProveedor(proveedorMaterialEntity.getProveedor());
        proveedorMaterialEntity.setEspecificar(proveedorMaterialEntity.getEspecificar());

    }
}
