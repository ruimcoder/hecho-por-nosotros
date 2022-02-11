package com.folcamp.hechopornosotros.models.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "contactos")
public class ContactoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String direccion;
    private String pais;
    private String telefono;
    private String email;
    private String instagram;
    private String facebook;
    private double longitud;
    private double latitud;
    @OneToOne
    private EmprendimientoEntity emprendimiento;
}
