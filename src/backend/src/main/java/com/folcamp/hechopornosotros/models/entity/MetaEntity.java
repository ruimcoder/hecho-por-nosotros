package com.folcamp.hechopornosotros.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "metas")
public class MetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Nombre")
    private String nombre;

    @OneToMany(mappedBy = "meta", cascade = CascadeType.ALL)
    private List<EmprendimientoMetaEntity> metaEmprendimientoEntities;
}
