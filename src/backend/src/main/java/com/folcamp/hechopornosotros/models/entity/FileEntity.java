package com.folcamp.hechopornosotros.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "files")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String name;

    @OneToMany(mappedBy = "fileEntity", cascade = CascadeType.ALL)
    private List<EmprendimientoFileEntity> emprendimientoFileEntity;

    @OneToMany(mappedBy = "fileEntity", cascade = CascadeType.ALL)
    private List<InformacionFileEntity> fileInformacionEntities;

}