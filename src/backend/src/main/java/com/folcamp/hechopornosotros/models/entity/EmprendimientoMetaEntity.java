package com.folcamp.hechopornosotros.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "emprendimiento_meta")
public class EmprendimientoMetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    @ManyToOne
    @JoinColumn(name = "meta_id")
    private MetaEntity meta;

    @ManyToOne
    @JoinColumn(name = "emprendimiento_id")
    EmprendimientoEntity emprendimiento;
}
