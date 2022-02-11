package com.folcamp.hechopornosotros.models.mapper;

import com.folcamp.hechopornosotros.models.dto.*;
import com.folcamp.hechopornosotros.models.entity.*;
import com.folcamp.hechopornosotros.models.repositories.EmprendimientoFileRepository;
import com.folcamp.hechopornosotros.models.repositories.InformacionRepository;
import com.folcamp.hechopornosotros.models.repositories.PuntoDeVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmprendimientoMapper {

    @Autowired
    private ContactoMapper contactoMapper;
    @Autowired
    private EmprendimientoFileRepository emprendimientoFileRepository;
    @Autowired
    private InformacionMapper informacionMapper;
    @Autowired
    private InformacionRepository informacionRepository;
    @Autowired
    private PuntoDeVentaRepository puntoDeVentaRepository;
    @Autowired
    private PuntoDeVentaMapper puntoDeVentaMapper;
    @Autowired
    private MetaMapper metaMapper;
    @Autowired
    private ProveedorMapper proveedorMapper;

    public EmprendimientoEntity mapEmprendimientoNewDtoToEmprendimientoEntity(EmprendimientoNewDTO emprendimientoNewDto) {
        EmprendimientoEntity emprendimientoEntity = new EmprendimientoEntity();
        emprendimientoEntity.setNombre(emprendimientoNewDto.getNombre());
        emprendimientoEntity.setPublicado(emprendimientoNewDto.getPublicado());
        emprendimientoEntity.setSlogan(emprendimientoNewDto.getSlogan());
        emprendimientoEntity.setGenero(emprendimientoNewDto.getGenero());
        emprendimientoEntity.setRegistro(emprendimientoNewDto.getRegistro());
        emprendimientoEntity.setTipo(emprendimientoNewDto.getTipo());
        emprendimientoEntity.setPertenece(emprendimientoNewDto.getPertenece());
        emprendimientoEntity.setExporta(emprendimientoNewDto.getExporta());
        emprendimientoEntity.setCount_empleados(emprendimientoNewDto.getCount_empleados());
        emprendimientoEntity.setCount_mujeres(emprendimientoNewDto.getCount_mujeres());
        emprendimientoEntity.setCount_jovenes(emprendimientoNewDto.getCount_jovenes());
        emprendimientoEntity.setCount_colaboracion(emprendimientoNewDto.getCount_colaboracion());
        emprendimientoEntity.setCount_mujeres_colaboracion(emprendimientoNewDto.getCount_mujeres_colaboracion());
        emprendimientoEntity.setCount_jovenes_colaboracion(emprendimientoNewDto.getCount_jovenes_colaboracion());
        emprendimientoEntity.setComentario_meta(emprendimientoNewDto.getComentario_meta());
        emprendimientoEntity.setAsia(emprendimientoNewDto.isAsia());
        emprendimientoEntity.setAfrica(emprendimientoNewDto.isAfrica());
        emprendimientoEntity.setNorteamerica(emprendimientoNewDto.isNorteamerica());
        emprendimientoEntity.setSudamerica(emprendimientoNewDto.isSudamerica());
        emprendimientoEntity.setAntartida(emprendimientoNewDto.isAntartida());
        emprendimientoEntity.setEuropa(emprendimientoNewDto.isEuropa());
        emprendimientoEntity.setOceania(emprendimientoNewDto.isOceania());

        return emprendimientoEntity;
    }

    public EmprendimientoDTO mapEmprendimientoDtoToEmprendimientoEntity(EmprendimientoEntity emprendimientoEntity) {
        return new EmprendimientoDTO(
                emprendimientoEntity.getId(),
                emprendimientoEntity.getNombre(),
                emprendimientoEntity.getPublicado(),
                emprendimientoEntity.getSlogan(),
                emprendimientoEntity.getGenero(),
                emprendimientoEntity.getRegistro(),
                emprendimientoEntity.getTipo(),
                emprendimientoEntity.getPertenece(),
                emprendimientoEntity.getExporta(),
                emprendimientoEntity.getCount_empleados(),
                emprendimientoEntity.getCount_mujeres(),
                emprendimientoEntity.getCount_jovenes(),
                emprendimientoEntity.getCount_colaboracion(),
                emprendimientoEntity.getCount_mujeres_colaboracion(),
                emprendimientoEntity.getCount_jovenes_colaboracion(),
                emprendimientoEntity.getComentario_meta()
        );
    }

    public EmprendimientoDTO mapEmprendimientoEntityToEmprendimientoDto(EmprendimientoEntity emprendimientoEntity) {
        return new EmprendimientoDTO(
                emprendimientoEntity.getId(),
                emprendimientoEntity.getNombre(),
                emprendimientoEntity.getPublicado(),
                emprendimientoEntity.getSlogan(),
                emprendimientoEntity.getGenero(),
                emprendimientoEntity.getRegistro(),
                emprendimientoEntity.getTipo(),
                emprendimientoEntity.getPertenece(),
                emprendimientoEntity.getExporta(),
                emprendimientoEntity.getCount_empleados(),
                emprendimientoEntity.getCount_mujeres(),
                emprendimientoEntity.getCount_jovenes(),
                emprendimientoEntity.getCount_colaboracion(),
                emprendimientoEntity.getCount_mujeres_colaboracion(),
                emprendimientoEntity.getCount_jovenes_colaboracion(),
                emprendimientoEntity.getComentario_meta()
        );
    }

    public void mapEmprendimientoUpdateDtoToEmprendimientoEntity(EmprendimientoEntity emprendimientoEntity, EmprendimientoNewDTO emprendimientoNewDto) {
        emprendimientoEntity.setNombre(emprendimientoNewDto.getNombre());
        emprendimientoEntity.setPublicado(emprendimientoNewDto.getPublicado());
        emprendimientoEntity.setSlogan(emprendimientoNewDto.getSlogan());
        emprendimientoEntity.setGenero(emprendimientoNewDto.getGenero());
        emprendimientoEntity.setRegistro(emprendimientoNewDto.getRegistro());
        emprendimientoEntity.setTipo(emprendimientoNewDto.getTipo());
        emprendimientoEntity.setPertenece(emprendimientoNewDto.getPertenece());
        emprendimientoEntity.setExporta(emprendimientoNewDto.getExporta());
        emprendimientoEntity.setCount_empleados(emprendimientoNewDto.getCount_empleados());
        emprendimientoEntity.setCount_mujeres(emprendimientoNewDto.getCount_mujeres());
        emprendimientoEntity.setCount_jovenes(emprendimientoNewDto.getCount_jovenes());
        emprendimientoEntity.setCount_colaboracion(emprendimientoNewDto.getCount_colaboracion());
        emprendimientoEntity.setCount_mujeres_colaboracion(emprendimientoNewDto.getCount_mujeres_colaboracion());
        emprendimientoEntity.setCount_jovenes_colaboracion(emprendimientoNewDto.getCount_jovenes_colaboracion());
        emprendimientoEntity.setComentario_meta(emprendimientoNewDto.getComentario_meta());
        emprendimientoEntity.setAsia(emprendimientoNewDto.isAsia());
        emprendimientoEntity.setAfrica(emprendimientoNewDto.isAfrica());
        emprendimientoEntity.setNorteamerica(emprendimientoNewDto.isNorteamerica());
        emprendimientoEntity.setSudamerica(emprendimientoNewDto.isSudamerica());
        emprendimientoEntity.setAntartida(emprendimientoNewDto.isAntartida());
        emprendimientoEntity.setEuropa(emprendimientoNewDto.isEuropa());
        emprendimientoEntity.setOceania(emprendimientoNewDto.isOceania());
    }

    public CardDTO mapEmprendimientoDtoToCardDTO(EmprendimientoEntity emprendimientoEntity, String urlLogo, String urlPortada) {

        return new CardDTO(
                emprendimientoEntity.getId(),
                emprendimientoEntity.getNombre(),
                emprendimientoEntity.getTipo(),
                contactoEmail(emprendimientoEntity),
                contactoPais(emprendimientoEntity),
                urlLogo,
                urlPortada
        );
    }

    private String contactoEmail(EmprendimientoEntity emprendimientoEntity) {
        ContactoEntity contactoEntity = emprendimientoEntity.getContacto();
        if (contactoEntity == null) {
            return null;
        }
        String email = contactoEntity.getEmail();
        if (email == null) {
            return null;
        }
        return email;
    }

    private String contactoPais(EmprendimientoEntity emprendimientoEntity) {
        ContactoEntity contactoEntity = emprendimientoEntity.getContacto();
        if (contactoEntity == null) {
            return null;
        }
        String pais = contactoEntity.getPais();
        if (pais == null) {
            return null;
        }
        return pais;
    }

    public EmprendimientoDetailDTO mapEmprendimientoEntityToEmprendimientoDetailDTO(EmprendimientoEntity emprendimientoEntity) {

        EmprendimientoDetailDTO emprendimientoDetailDTO = new EmprendimientoDetailDTO();
        emprendimientoDetailDTO.setId(emprendimientoEntity.getId());
        emprendimientoDetailDTO.setNombre(emprendimientoEntity.getNombre());
        emprendimientoDetailDTO.setPublicado(emprendimientoEntity.getPublicado());
        emprendimientoDetailDTO.setSlogan(emprendimientoEntity.getSlogan());
        emprendimientoDetailDTO.setGenero(emprendimientoEntity.getGenero());
        emprendimientoDetailDTO.setRegistro(emprendimientoEntity.getRegistro());
        emprendimientoDetailDTO.setTipo(emprendimientoEntity.getTipo());
        emprendimientoDetailDTO.setPertenece(emprendimientoEntity.getPertenece());
        emprendimientoDetailDTO.setExporta(emprendimientoEntity.getExporta());
        emprendimientoDetailDTO.setCount_empleados(emprendimientoEntity.getCount_empleados());
        emprendimientoDetailDTO.setCount_mujeres(emprendimientoEntity.getCount_mujeres());
        emprendimientoDetailDTO.setCount_jovenes(emprendimientoEntity.getCount_jovenes());
        emprendimientoDetailDTO.setCount_colaboracion(emprendimientoEntity.getCount_colaboracion());
        emprendimientoDetailDTO.setCount_mujeres_colaboracion(emprendimientoEntity.getCount_mujeres_colaboracion());
        emprendimientoDetailDTO.setCount_jovenes_colaboracion(emprendimientoEntity.getCount_jovenes_colaboracion());
        emprendimientoDetailDTO.setComentario_meta(emprendimientoEntity.getComentario_meta());
        emprendimientoDetailDTO.setAsia(emprendimientoEntity.isAsia());
        emprendimientoDetailDTO.setAfrica(emprendimientoEntity.isAfrica());
        emprendimientoDetailDTO.setNorteamerica(emprendimientoEntity.isNorteamerica());
        emprendimientoDetailDTO.setSudamerica(emprendimientoEntity.isSudamerica());
        emprendimientoDetailDTO.setAntartida(emprendimientoEntity.isAntartida());
        emprendimientoDetailDTO.setEuropa(emprendimientoEntity.isEuropa());
        emprendimientoDetailDTO.setOceania(emprendimientoEntity.isOceania());
        emprendimientoDetailDTO.setContacto(getContacto(emprendimientoEntity));
        emprendimientoDetailDTO.setLogo(getLogo(emprendimientoEntity));
        emprendimientoDetailDTO.setPortada(getPortada(emprendimientoEntity));
        emprendimientoDetailDTO.setCertificados(getCertificados(emprendimientoEntity));
        emprendimientoDetailDTO.setHistoria(getHistoria(emprendimientoEntity));
        emprendimientoDetailDTO.setTecnica(getTecnica(emprendimientoEntity));
        emprendimientoDetailDTO.setPuntosDeVenta(getPuntoDeVenta(emprendimientoEntity));
        emprendimientoDetailDTO.setMetas(getMetas(emprendimientoEntity));
        emprendimientoDetailDTO.setProveedores(getProveedores(emprendimientoEntity));

        return emprendimientoDetailDTO;
    }

    private ContactoDTO getContacto(EmprendimientoEntity emprendimientoEntity) {
        ContactoDTO contactoDTO = null;

        if (emprendimientoEntity.getContacto() != null) {
            contactoDTO = contactoMapper.mapContactoEntityToContactoDTO(emprendimientoEntity.getContacto());
        }
        return contactoDTO;
    }

    private String getLogo(EmprendimientoEntity emprendimientoEntity) {
        String urlLogo = null;
        List<EmprendimientoFileEntity> emprendimientoFileEntities =
                emprendimientoFileRepository.findByEmprendimientoEntityAndType(emprendimientoEntity, "logo");

        if (!emprendimientoFileEntities.isEmpty()) {
            urlLogo = emprendimientoFileEntities.get(0).getFileEntity().getUrl();
        }
        return urlLogo;
    }

    private String getPortada(EmprendimientoEntity emprendimientoEntity) {
        String urlPortada = null;
        List<EmprendimientoFileEntity> emprendimientoFileEntities =
                emprendimientoFileRepository.findByEmprendimientoEntityAndType(emprendimientoEntity, "portada");

        if (!emprendimientoFileEntities.isEmpty()) {
            urlPortada = emprendimientoFileEntities.get(0).getFileEntity().getUrl();
        }
        return urlPortada;
    }

    private List<String> getCertificados(EmprendimientoEntity emprendimientoEntity) {
        List<String> certificados = new ArrayList<>();

        List<EmprendimientoFileEntity> emprendimientoFileEntities =
                emprendimientoFileRepository.findByEmprendimientoEntityAndType(emprendimientoEntity, "certificado");

        if (emprendimientoFileEntities.isEmpty()){
            return null;
        }

        for (EmprendimientoFileEntity emprendimientoFileEntity : emprendimientoFileEntities) {
            certificados.add(emprendimientoFileEntity.getFileEntity().getUrl());
        }

        return certificados;
    }


    private InformacionResponseDTO getHistoria(EmprendimientoEntity emprendimientoEntity) {

        List<InformacionEntity> informacionEntities =
                informacionRepository.findByEmprendimientoEntityAndTipo(emprendimientoEntity, "historia");

        if (informacionEntities.isEmpty()) {
            return null;
        }

        InformacionResponseDTO informacionDTO = informacionMapper.mapInformacionEntityToInformacionDTO(informacionEntities.get(0));

        return informacionDTO;
    }

    private InformacionResponseDTO getTecnica(EmprendimientoEntity emprendimientoEntity) {

        List<InformacionEntity> informacionEntities =
                informacionRepository.findByEmprendimientoEntityAndTipo(emprendimientoEntity, "tecnica");

        if (informacionEntities.isEmpty()) {
            return null;
        }

        InformacionResponseDTO informacionDTO = informacionMapper.mapInformacionEntityToInformacionDTO(informacionEntities.get(0));

        return informacionDTO;
    }

    private List<PuntoDeVentaDTO> getPuntoDeVenta(EmprendimientoEntity emprendimientoEntity) {

        List<PuntoDeVentaEntity> puntoDeVentaEntities = puntoDeVentaRepository.findByEmprendimiento(emprendimientoEntity);
        List<PuntoDeVentaDTO> puntoDeVentaDTOList = new ArrayList<>();

        if (puntoDeVentaEntities.isEmpty()) {
            return null;
        }

        for (PuntoDeVentaEntity puntoDeVentaEntity : puntoDeVentaEntities) {
            PuntoDeVentaDTO puntoDeVentaDTO = puntoDeVentaMapper.mapPuntoDeVentaEntityToPuntoDeVentaDTO(puntoDeVentaEntity);
            puntoDeVentaDTOList.add(puntoDeVentaDTO);
        }

        return puntoDeVentaDTOList;
    }

    private List<MetaDTO> getMetas(EmprendimientoEntity emprendimientoEntity) {

        List<EmprendimientoMetaEntity>  emprendimientoMetaEntities= emprendimientoEntity.getEmprendimientoMetaEntities();
        List<MetaDTO> metaDTOS = new ArrayList<>();

        if(emprendimientoMetaEntities.isEmpty()){
            return null;
        }

        for (int i = 0; i < emprendimientoMetaEntities.size(); i++) {
            metaDTOS.add(metaMapper.mapMetaEntityToMetaDTO(emprendimientoEntity.getEmprendimientoMetaEntities().get(i).getMeta()));
        }
        return metaDTOS;
    }

    public List<ProveedorDetailDTO> getProveedores(EmprendimientoEntity emprendimientoEntity) {

        List<ProveedorEntity> proveedorEntities = emprendimientoEntity.getProveedorEntities();
        List<ProveedorDetailDTO> proveedorDetailDTOS = new ArrayList<>();

        if(proveedorEntities.isEmpty()){
            return null;
        }

        for (ProveedorEntity proveedorEntity: proveedorEntities){
            proveedorDetailDTOS.add(proveedorMapper.mapProveedorEntityToProveedorDetailDTO(proveedorEntity));
        }

        return proveedorDetailDTOS;

    }
}
