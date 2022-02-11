package com.folcamp.hechopornosotros.service;

import com.folcamp.hechopornosotros.models.dto.UserDTO;
//import com.folcamp.hechopornosotros.models.dto.UserEmprendedorDTO;
import com.folcamp.hechopornosotros.models.dto.UserEmprendedorDTO;
import com.folcamp.hechopornosotros.models.entity.UserEmprendimientoEntity;
import com.folcamp.hechopornosotros.models.mapper.UserEmprendimientoMapper;
import com.folcamp.hechopornosotros.models.repositories.UserEmprendimientoRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserEmprendedorService {
    @Autowired
    private UserEmprendimientoRepository userEmprendimientoRepository;
    @Autowired
    private UserEmprendimientoMapper uEMapper;

    public ResponseEntity<?> store(UserEmprendedorDTO userEmprendedorDTO) throws FirebaseAuthException {
        try{
            if (userEmprendimientoRepository.existsByUid(userEmprendedorDTO.getUid()))
                return new ResponseEntity<>("Usuario ya existente",HttpStatus.OK);
            UserEmprendimientoEntity emprendedor = new UserEmprendimientoEntity();
            emprendedor.setUid(userEmprendedorDTO.getUid());
            userEmprendimientoRepository.save(emprendedor);
            UserDTO userDTO = new UserDTO(FirebaseAuth.getInstance().getUser(userEmprendedorDTO.getUid()));
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> index() throws FirebaseAuthException {
        try{
            List<UserEmprendimientoEntity> usuarios = userEmprendimientoRepository.findAll();
            List<UserEmprendedorDTO> userEmprendedorDTOS = new ArrayList<>();
            for (UserEmprendimientoEntity usuario : usuarios) {
                UserDTO userDTO = new UserDTO(FirebaseAuth.getInstance().getUser(usuario.getUid()));
                userEmprendedorDTOS.add(uEMapper.mapUserEmprendedorEntityToUserEmprendedorDTO(usuario,userDTO));
            }
            return new ResponseEntity<>(userEmprendedorDTOS, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> show(Long id) throws FirebaseAuthException {
        if (!userEmprendimientoRepository.existsById(id))
            return new ResponseEntity<>("No existe", HttpStatus.NOT_FOUND);
        UserEmprendimientoEntity user = userEmprendimientoRepository.getById(id);
        UserDTO userDTO = new  UserDTO(FirebaseAuth.getInstance().getUser(user.getUid()));
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Long id) throws FirebaseAuthException {
        if (!userEmprendimientoRepository.existsById(id))
            return new ResponseEntity<>("No existe", HttpStatus.NOT_FOUND);
        UserEmprendimientoEntity user = userEmprendimientoRepository.getById(id);
        UserDTO userDTO = new  UserDTO(FirebaseAuth.getInstance().getUser(user.getUid()));
        userEmprendimientoRepository.deleteById(id);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
