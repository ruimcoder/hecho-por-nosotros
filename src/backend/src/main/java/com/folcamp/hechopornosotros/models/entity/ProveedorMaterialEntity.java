package com.folcamp.hechopornosotros.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "proveedores_materiales")
public class ProveedorMaterialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "materiales_id")
    @JsonIgnore
    private MaterialesEntity material;

    @ManyToOne
    @JoinColumn(name = "proveedores_id")
    @JsonIgnore
    private ProveedorEntity proveedor;

    @Column(name = "especificar")
    @JsonIgnore
    private String especificar;


}
