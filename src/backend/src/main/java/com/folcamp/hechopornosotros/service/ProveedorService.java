package com.folcamp.hechopornosotros.service;

import com.folcamp.hechopornosotros.config.exceptions.BadRequestException;
import com.folcamp.hechopornosotros.config.exceptions.NotFoundException;
import com.folcamp.hechopornosotros.models.dto.*;
import com.folcamp.hechopornosotros.models.entity.*;
import com.folcamp.hechopornosotros.models.mapper.EmprendimientoMapper;
import com.folcamp.hechopornosotros.models.mapper.ProveedorMapper;
import com.folcamp.hechopornosotros.models.repositories.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ProveedorMapper proveedorMapper;
    @Autowired
    private EmprendimientoRepository emprendimientoRepository;
    @Autowired
    private EmprendimientoMapper emprendimientoMapper;
    @Autowired
    private MaterialesRepository materialesRepository;
    @Autowired
    private ProveedorMaterialRepository proveedorMaterialRepository;
    @Autowired
    private UserEmprendimientoRepository userEmprendimientoRepository;

    public List<ProveedorDTO> getProveedor() {
        List<ProveedorEntity> proveedorEntities = proveedorRepository.findAll();
        List<ProveedorDTO> proveedorDTOS = new ArrayList<>();

        for (ProveedorEntity proveedorEntity : proveedorEntities) {
            proveedorDTOS.add(proveedorMapper.mapProveedorEntityToProveedorDTO(proveedorEntity));
        }
        return proveedorDTOS;
    }

    public ProveedorDTO getProveedor(Long id) {
        Optional<ProveedorEntity> optionalProveedorEntity = proveedorRepository.findById(id);
        if (optionalProveedorEntity.isPresent()) {
            ProveedorEntity proveedorEntity = optionalProveedorEntity.get();
            ProveedorDTO proveedorDTO = proveedorMapper.mapProveedorEntityToProveedorDTO(proveedorEntity);

            return proveedorDTO;
        } else {
            throw new RuntimeException("No se encontro proveedor");
        }
    }

    @Transactional
    public ResponseEntity<ProveedorNewDTO> createProveedor(ProveedorNewDTO proveedorNewDTO) {
        Optional<EmprendimientoEntity> optionalEmprendimientoEntity = emprendimientoRepository.findById(proveedorNewDTO.getEmprendimiento_id());
        if (!optionalEmprendimientoEntity.isPresent()) {
            throw new NotFoundException("No se encontro emprendimiento");
        }
        ProveedorEntity proveedorEntity = proveedorMapper.mapProveedorNewDtoToProveedorEntity(proveedorNewDTO);
        EmprendimientoEntity emprendimientoEntity = optionalEmprendimientoEntity.get();
        proveedorEntity.setEmprendimiento(emprendimientoEntity);

        proveedorEntity = proveedorRepository.save(proveedorEntity);

        if (!proveedorNewDTO.getMateriales().isEmpty()) {
            for (Long materialId : proveedorNewDTO.getMateriales()) {
                Optional<MaterialesEntity> materialesEntityOptional = materialesRepository.findById(materialId);
                if (!materialesEntityOptional.isPresent()) {
                    throw new NotFoundException("No se encontró material");
                }

                MaterialesEntity materialesEntity = materialesEntityOptional.get();
                ProveedorMaterialEntity proveedorMaterialEntity = new ProveedorMaterialEntity();
                proveedorMaterialEntity.setProveedor(proveedorEntity);
                proveedorMaterialEntity.setMaterial(materialesEntity);
                if(proveedorNewDTO.getEspecificar() == null || proveedorNewDTO.getEspecificar().isEmpty()){
                    proveedorMaterialEntity.setEspecificar(null);
                }else if (materialesEntityOptional.get().getNombre().equals("Otros")){
                    proveedorMaterialEntity.setEspecificar(proveedorNewDTO.getEspecificar());
                }
                proveedorMaterialRepository.save(proveedorMaterialEntity);
            }
        }

        return new ResponseEntity<>(proveedorNewDTO, HttpStatus.OK);
    }

    public ResponseEntity<ProveedorDTO> deleteProveedor(Long id) {
        Optional<ProveedorEntity> optionalProveedorEntity = proveedorRepository.findById(id);

        if (optionalProveedorEntity.isPresent()) {
            ProveedorEntity proveedorEntity = optionalProveedorEntity.get();
            ProveedorDTO proveedorDTO = proveedorMapper.mapProveedorEntityToProveedorDTO(proveedorEntity);
            proveedorRepository.deleteById(id);
            return new ResponseEntity<>(proveedorDTO, HttpStatus.OK);
        } else {
            throw new RuntimeException("No se encontro proveedor");
        }
    }

    @Transactional
    public ResponseEntity<ProveedorNewDTO> editProveedor(Long id, ProveedorNewDTO proveedorNewDTO) {
        Optional<ProveedorEntity> optionalProveedorEntity = proveedorRepository.findById(id);

        if (optionalProveedorEntity.isPresent()) {
            ProveedorEntity proveedorEntity = optionalProveedorEntity.get();
            proveedorMapper.mapProveedorUpdateDtoToProveedorEntity(proveedorEntity, proveedorNewDTO);

            List<ProveedorMaterialEntity> proveedorMaterialEntities = proveedorEntity.getProveedorMaterialEntities();

            for(Long materialId : proveedorNewDTO.getMateriales()){
                Optional<MaterialesEntity> materialesEntityOptional = materialesRepository.findById(materialId);
                if (!materialesEntityOptional.isPresent()) {
                    throw new NotFoundException("No se encontró material");
                }
            }

            proveedorEntity.setProveedorMaterialEntities(null);
            proveedorMaterialRepository.deleteAll(proveedorMaterialEntities);

            if (!proveedorNewDTO.getMateriales().isEmpty()) {
                for (Long materialId : proveedorNewDTO.getMateriales()) {


                    MaterialesEntity materialesEntity = materialesRepository.findById(materialId).get();
                    ProveedorMaterialEntity proveedorMaterialEntity = new ProveedorMaterialEntity();
                    proveedorMaterialEntity.setProveedor(proveedorEntity);
                    proveedorMaterialEntity.setMaterial(materialesEntity);
                    if(proveedorNewDTO.getEspecificar() == null || proveedorNewDTO.getEspecificar().isEmpty()){
                        proveedorMaterialEntity.setEspecificar(null);
                    }else if (materialesEntity.getNombre().equals("Otros")){
                        proveedorMaterialEntity.setEspecificar(proveedorNewDTO.getEspecificar());
                    }
                    proveedorMaterialRepository.save(proveedorMaterialEntity);
                }
            }


            proveedorRepository.save(proveedorEntity);
            return new ResponseEntity<>(proveedorNewDTO, HttpStatus.OK);
        } else {
            throw new RuntimeException("No se encontro proveedor");
        }
    }

    public ResponseEntity<List<ProveedorDetailCardsDTO>> findProveedorByEmprendimiento(String token) throws FirebaseAuthException {

        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token.substring(7));
        String uid = decodedToken.getUid();

        List<UserEmprendimientoEntity> userEmprendimientoEntities = userEmprendimientoRepository.findAllByUid(uid);

        List<ProveedorDetailCardsDTO> proveedorDetailCardsDTOS = new ArrayList<>();


        for (UserEmprendimientoEntity userEmprendimientoEntity:userEmprendimientoEntities){
            EmprendimientoEntity emprendimientoEntity = userEmprendimientoEntity.getEmprendimiento();

            List<ProveedorEntity> proveedorEntityList= emprendimientoEntity.getProveedorEntities();
            List<ProveedorDetailDTO> puntoDeVentaDTOList = new ArrayList<>();

            for (ProveedorEntity proveedorEntity: proveedorEntityList){
                puntoDeVentaDTOList.add(proveedorMapper.mapProveedorEntityToProveedorDetailDTO(proveedorEntity));
            }
            ProveedorDetailCardsDTO proveedorDetailCardsDTO = new ProveedorDetailCardsDTO(emprendimientoEntity.getId(), puntoDeVentaDTOList);
            proveedorDetailCardsDTOS.add(proveedorDetailCardsDTO);
        }


        return new ResponseEntity<>(proveedorDetailCardsDTOS, HttpStatus.OK);

    }
}
