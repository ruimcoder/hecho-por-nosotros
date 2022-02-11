package com.folcamp.hechopornosotros.models.dto;

import com.folcamp.hechopornosotros.models.entity.ContactoEntity;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoFileEntity;
import com.folcamp.hechopornosotros.models.entity.InformacionEntity;
import com.folcamp.hechopornosotros.models.entity.PuntoDeVentaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprendimientoDetailDTO {
    private Long id;
    private String nombre;
    private Boolean publicado;
    private String slogan;
    private String genero;
    private long registro;
    private String tipo;
    private String pertenece;
    private String email;
    private String ubicacion;
    private Boolean exporta;
    private long count_empleados;
    private long count_mujeres;
    private long count_jovenes;
    private long count_colaboracion;
    private long count_mujeres_colaboracion;
    private long count_jovenes_colaboracion;
    private String comentario_meta;
    private boolean asia;
    private boolean africa;
    private boolean norteamerica;
    private boolean sudamerica;
    private boolean antartida;
    private boolean europa;
    private boolean oceania;

    private ContactoDTO contacto;
    private String logo;
    private String portada;
    private List<String> certificados;
    private InformacionResponseDTO historia;
    private InformacionResponseDTO tecnica;

    private List<PuntoDeVentaDTO> puntosDeVenta;
    private List<ProveedorDetailDTO> proveedores;
    private List<MetaDTO> metas;
}
