package com.folcamp.hechopornosotros.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "emprendimiento_file")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprendimientoFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "file_id")
    @JsonIgnore
    private FileEntity fileEntity;
    @ManyToOne
    @JoinColumn(name = "emprendimiento_id")
    @JsonIgnore
    private EmprendimientoEntity emprendimientoEntity;

    private String type;
}
