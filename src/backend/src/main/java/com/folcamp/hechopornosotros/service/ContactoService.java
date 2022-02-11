package com.folcamp.hechopornosotros.service;

import com.folcamp.hechopornosotros.models.dto.ContactoDTO;
import com.folcamp.hechopornosotros.models.dto.ContactoNewDTO;
import com.folcamp.hechopornosotros.models.dto.EmprendimientoDTO;
import com.folcamp.hechopornosotros.models.dto.EmprendimientoNewDTO;
import com.folcamp.hechopornosotros.models.entity.ContactoEntity;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import com.folcamp.hechopornosotros.models.mapper.ContactoMapper;
import com.folcamp.hechopornosotros.models.repositories.ContactoRepository;
import com.folcamp.hechopornosotros.models.repositories.EmprendimientoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactoService {
    @Autowired
    private ContactoMapper contactoMapper;
    @Autowired
    private ContactoRepository contactoRepository;
    @Autowired
    private EmprendimientoRepository emprendimientoRepository;

    public List<ContactoDTO> getContactos() {
        List<ContactoEntity> contactoEntities = contactoRepository.findAll();
        List<ContactoDTO> contactoDTOS = new ArrayList<>();

        for (ContactoEntity contactoEntity : contactoEntities) {
            contactoDTOS.add(contactoMapper.mapContactoEntityToContactoDTO(contactoEntity));
        }

        return contactoDTOS;
    }

    public ContactoDTO getContacto(Long id) {
        Optional<ContactoEntity> optionalContactoEntity = contactoRepository.findById(id);
        if (optionalContactoEntity.isPresent()) {
            ContactoEntity contactoEntity = optionalContactoEntity.get();
            ContactoDTO contactoDTO = contactoMapper.mapContactoEntityToContactoDTO(contactoEntity);
            return contactoDTO;
        } else {
            throw new RuntimeException("No se encontro contacto");
        }
    }

    public ResponseEntity<ContactoNewDTO> createContacto(ContactoNewDTO contactoNewDTO) {

        if(!emprendimientoRepository.existsById(contactoNewDTO.getEmprendimientoId())){
            throw new RuntimeException("No se encontro emprendimiento");
        }
        EmprendimientoEntity emprendimientoEntity = emprendimientoRepository.findById(contactoNewDTO.getEmprendimientoId()).get();
        ContactoEntity contactoEntity = contactoMapper.mapContactoNewDtoToContactoEntity(contactoNewDTO);

        if(emprendimientoEntity.getContacto() != null){
            Long idContacto = emprendimientoEntity.getContacto().getId();
            emprendimientoEntity.setContacto(null);
            contactoRepository.deleteById(idContacto);
        }

        contactoEntity.setEmprendimiento(emprendimientoEntity);
        emprendimientoEntity.setContacto(contactoEntity);
        emprendimientoRepository.save(emprendimientoEntity);

        return new ResponseEntity<>(contactoNewDTO, HttpStatus.OK);

    }

    public ResponseEntity<ContactoDTO> deleteContacto(Long id) {
        Optional<ContactoEntity> optionalContactoEntity = contactoRepository.findById(id);

        if (optionalContactoEntity.isPresent()) {
            ContactoEntity contactoEntity = optionalContactoEntity.get();
            ContactoDTO contactoDTO = contactoMapper.mapContactoEntityToContactoDTO(contactoEntity);
            EmprendimientoEntity emprendimientoEntity = contactoEntity.getEmprendimiento();
            emprendimientoEntity.setContacto(null);
            emprendimientoRepository.save(emprendimientoEntity);
            contactoRepository.delete(contactoEntity);
            return new ResponseEntity<>(contactoDTO, HttpStatus.OK);
        } else {
            throw new RuntimeException("No se encontro contacto");
        }
    }

    public ResponseEntity<ContactoNewDTO> editContacto(Long id, ContactoNewDTO contactoNewDTO) {
        Optional<ContactoEntity> optionalContactoEntity = contactoRepository.findById(id);

        if (optionalContactoEntity.isPresent()) {
            ContactoEntity contactoEntity = optionalContactoEntity.get();
            contactoMapper.mapContactoUpdateDTOContactoEntity(contactoEntity, contactoNewDTO);
            contactoRepository.save(contactoEntity);
            return new ResponseEntity<>(contactoNewDTO, HttpStatus.OK);
        } else {
            throw new RuntimeException("No se encontro contacto");
        }
    }
}
