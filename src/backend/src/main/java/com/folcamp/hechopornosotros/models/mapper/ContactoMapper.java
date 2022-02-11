package com.folcamp.hechopornosotros.models.mapper;

import com.folcamp.hechopornosotros.models.dto.ContactoDTO;
import com.folcamp.hechopornosotros.models.dto.ContactoNewDTO;
import com.folcamp.hechopornosotros.models.dto.EmprendimientoNewDTO;
import com.folcamp.hechopornosotros.models.entity.ContactoEntity;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import org.springframework.stereotype.Component;

@Component
public class ContactoMapper {
    public ContactoEntity mapContactoNewDtoToContactoEntity(ContactoNewDTO contactoNewDTO) {
        ContactoEntity contactoEntity = new ContactoEntity();

        contactoEntity.setDireccion(contactoNewDTO.getDireccion());
        contactoEntity.setPais(contactoNewDTO.getPais());
        contactoEntity.setTelefono(contactoNewDTO.getTelefono());
        contactoEntity.setEmail(contactoNewDTO.getEmail());
        contactoEntity.setInstagram(contactoNewDTO.getInstagram());
        contactoEntity.setFacebook(contactoNewDTO.getFacebook());
        contactoEntity.setLatitud(contactoNewDTO.getLatitud());
        contactoEntity.setLongitud(contactoNewDTO.getLongitud());

        return contactoEntity;
    }

    public ContactoDTO mapContactoEntityToContactoDTO(ContactoEntity contactoEntity) {
        return new ContactoDTO(
                contactoEntity.getId(),
                contactoEntity.getDireccion(),
                contactoEntity.getPais(),
                contactoEntity.getTelefono(),
                contactoEntity.getEmail(),
                contactoEntity.getInstagram(),
                contactoEntity.getFacebook(),
                contactoEntity.getLongitud(),
                contactoEntity.getLatitud()
        );
    }

    public ContactoEntity mapContactoUpdateDTOContactoEntity(ContactoEntity contactoEntity, ContactoNewDTO contactoNewDTO) {
        contactoEntity.setDireccion(contactoNewDTO.getDireccion());
        contactoEntity.setPais(contactoNewDTO.getPais());
        contactoEntity.setTelefono(contactoNewDTO.getTelefono());
        contactoEntity.setEmail(contactoNewDTO.getEmail());
        contactoEntity.setInstagram(contactoNewDTO.getInstagram());
        contactoEntity.setFacebook(contactoEntity.getFacebook());
        contactoEntity.setLatitud(contactoNewDTO.getLatitud());
        contactoEntity.setLongitud(contactoNewDTO.getLongitud());
        return contactoEntity;
    }
}
