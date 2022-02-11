package com.folcamp.hechopornosotros.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name = "puntos")
@SQLDelete(sql = "UPDATE puntos SET deleted_at = true WHERE id=?")
@Where(clause = "deleted_at = false")
public class PuntoDeVentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Direccion_Secundaria")
    private String direccionSec;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Pais")
    private String pais;
    @Column(name = "Ciudad")
    private String ciudad;
    @Column(name = "CP")
    private long cp;
    @Column(name = "Tipo")
    private String tipo;
    @Column(name = "Contacto")
    private String contacto;
    @Column(name = "Sitio_Web")
    private String web;
    @Column(name = "deleted_at")
    private boolean deletedAt = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emprendimiento_id")
    private EmprendimientoEntity emprendimiento;

}
