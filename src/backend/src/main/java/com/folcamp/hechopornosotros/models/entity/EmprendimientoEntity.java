package com.folcamp.hechopornosotros.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;



@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "emprendimientos")
@SQLDelete(sql = "UPDATE emprendimientos SET deleted_at = true WHERE id=?")
@Where(clause = "deleted_at = false")
public class EmprendimientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "publicado")
    private Boolean publicado;
    @Column(name = "slogan")
    private String slogan;
    @Column(name = "genero")
    private String genero;
    @Column(name = "registro")
    private long registro;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "pertenece")
    private String pertenece;
    @Column(name = "exporta")
    private Boolean exporta;
    @Column(name = "count_empleados")
    private long count_empleados;
    @Column(name = "count_mujeres")
    private long count_mujeres;
    @Column(name = "count_jovenes")
    private long count_jovenes;
    @Column(name = "count_colaboracion")
    private long count_colaboracion;
    @Column(name = "count_mujeres_colaboracion")
    private long count_mujeres_colaboracion;
    @Column(name = "count_jovenes_colaboracion")
    private long count_jovenes_colaboracion;
    @Column(name = "comentario_meta")
    private String comentario_meta;
    @Column(name = "deleted_at")
    private boolean deletedAt = false;

    private boolean asia = false;
    private boolean africa = false;
    private boolean norteamerica = false;
    private boolean sudamerica = false;
    private boolean antartida = false;
    private boolean europa = false;
    private boolean oceania = false;

    @OneToOne(cascade = CascadeType.ALL)
    private ContactoEntity contacto;


    @OneToMany(mappedBy = "emprendimiento", cascade = CascadeType.ALL)
    private List<PuntoDeVentaEntity> puntosDeVenta;

    @OneToMany(mappedBy = "emprendimientoEntity", cascade = CascadeType.ALL)
    private List<EmprendimientoFileEntity> emprendimientoFileEntity;

    @OneToMany(mappedBy = "emprendimientoEntity", cascade = CascadeType.ALL)
    private List<InformacionEntity> informacionEntities;

    @OneToMany(mappedBy = "emprendimiento", cascade = CascadeType.ALL)
    private List<EmprendimientoMetaEntity> emprendimientoMetaEntities;

    @OneToMany(mappedBy = "emprendimiento", cascade = CascadeType.ALL)
    private List<ProveedorEntity> proveedorEntities;



}
