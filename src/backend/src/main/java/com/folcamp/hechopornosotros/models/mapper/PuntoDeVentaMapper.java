package com.folcamp.hechopornosotros.models.mapper;

import com.folcamp.hechopornosotros.models.dto.ContactoNewDTO;
import com.folcamp.hechopornosotros.models.dto.EmprendimientoNewDTO;
import com.folcamp.hechopornosotros.models.dto.PuntoDeVentaDTO;
import com.folcamp.hechopornosotros.models.dto.PuntoDeVentaNewDTO;
import com.folcamp.hechopornosotros.models.entity.ContactoEntity;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import com.folcamp.hechopornosotros.models.entity.PuntoDeVentaEntity;
import org.springframework.stereotype.Component;

@Component
public class PuntoDeVentaMapper {
    public PuntoDeVentaDTO mapPuntoDeVentaEntityToPuntoDeVentaDTO(PuntoDeVentaEntity puntoDeVentaEntity){
        return  new PuntoDeVentaDTO(
                puntoDeVentaEntity.getId(),
                puntoDeVentaEntity.getDireccion(),
                puntoDeVentaEntity.getDireccionSec(),
                puntoDeVentaEntity.getNombre(),
                puntoDeVentaEntity.getPais(),
                puntoDeVentaEntity.getCiudad(),
                puntoDeVentaEntity.getCp(),
                puntoDeVentaEntity.getTipo(),
                puntoDeVentaEntity.getContacto(),
                puntoDeVentaEntity.getWeb()
        );
    }
    public PuntoDeVentaEntity mapPuntoDeVentaDTOToPuntoDeVentaEntity(PuntoDeVentaDTO puntoDeVentaDTO){
     PuntoDeVentaEntity puntoDeVentaEntity = new PuntoDeVentaEntity();
     puntoDeVentaEntity.setId(puntoDeVentaDTO.getId());
     puntoDeVentaEntity.setDireccion(puntoDeVentaDTO.getDireccion());
     puntoDeVentaEntity.setDireccionSec(puntoDeVentaDTO.getDireccionSec());
     puntoDeVentaEntity.setNombre(puntoDeVentaDTO.getNombre());
     puntoDeVentaEntity.setPais(puntoDeVentaDTO.getPais());
     puntoDeVentaEntity.setCiudad(puntoDeVentaDTO.getCiudad());
     puntoDeVentaEntity.setCp(puntoDeVentaDTO.getCp());
     puntoDeVentaEntity.setTipo(puntoDeVentaDTO.getTipo());
     puntoDeVentaEntity.setContacto(puntoDeVentaDTO.getContacto());
     puntoDeVentaEntity.setWeb(puntoDeVentaDTO.getWeb());

     return puntoDeVentaEntity;
    }
    public PuntoDeVentaEntity mapPuntoDeVentaNewDtoToPuntoDeVentaEntity(PuntoDeVentaNewDTO puntoDeVentaNewDTO) {
        PuntoDeVentaEntity puntoDeVentaEntity = new PuntoDeVentaEntity();

        puntoDeVentaEntity.setDireccion(puntoDeVentaNewDTO.getDireccion());
        puntoDeVentaEntity.setDireccionSec(puntoDeVentaNewDTO.getDireccionSec());
        puntoDeVentaEntity.setNombre(puntoDeVentaNewDTO.getNombre());
        puntoDeVentaEntity.setPais(puntoDeVentaNewDTO.getPais());
        puntoDeVentaEntity.setCiudad(puntoDeVentaNewDTO.getCiudad());
        puntoDeVentaEntity.setCp(puntoDeVentaNewDTO.getCp());
        puntoDeVentaEntity.setTipo(puntoDeVentaNewDTO.getTipo());
        puntoDeVentaEntity.setContacto(puntoDeVentaNewDTO.getContacto());
        puntoDeVentaEntity.setWeb(puntoDeVentaNewDTO.getWeb());


        return puntoDeVentaEntity;
    }

    public void mapPuntoDeVentaUpdateDtoToPuntoDeVentaEntity(PuntoDeVentaEntity puntoDeVentaEntity, PuntoDeVentaNewDTO puntoDeVentaNewDTO) {
        puntoDeVentaEntity.setDireccion(puntoDeVentaNewDTO.getDireccion());
        puntoDeVentaEntity.setDireccionSec(puntoDeVentaNewDTO.getDireccionSec());
        puntoDeVentaEntity.setNombre(puntoDeVentaNewDTO.getNombre());
        puntoDeVentaEntity.setPais(puntoDeVentaNewDTO.getPais());
        puntoDeVentaEntity.setCiudad(puntoDeVentaNewDTO.getCiudad());
        puntoDeVentaEntity.setCp(puntoDeVentaNewDTO.getCp());
        puntoDeVentaEntity.setTipo(puntoDeVentaNewDTO.getTipo());
        puntoDeVentaEntity.setContacto(puntoDeVentaNewDTO.getContacto());
        puntoDeVentaEntity.setWeb(puntoDeVentaNewDTO.getWeb());
    }
}
