package com.folcamp.hechopornosotros.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "materiales")
public class MaterialesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @OneToMany(mappedBy = "material",cascade = CascadeType.ALL)
    private List<ProveedorMaterialEntity> materialProveedorEntity;

}
