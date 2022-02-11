package com.folcamp.hechopornosotros.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "informacion_file")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformacionFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private FileEntity fileEntity;
    @ManyToOne
    @JoinColumn(name = "informacion_id")
    private InformacionEntity informacionEntity;
    private String type;
}
