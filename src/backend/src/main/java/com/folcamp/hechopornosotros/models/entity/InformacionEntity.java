package com.folcamp.hechopornosotros.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "informacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 2500)
    private String texto;

    private String urlVideo;
    private String tipo;

    @OneToMany(mappedBy = "informacionEntity", cascade = CascadeType.ALL)
    private List<InformacionFileEntity> fileInformacionEntities;

    @ManyToOne
    @JoinColumn(name = "emprendimiento_id")
    private EmprendimientoEntity emprendimientoEntity;

}
