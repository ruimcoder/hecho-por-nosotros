package com.folcamp.hechopornosotros.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "proveedores")
@SQLDelete(sql = "UPDATE proveedores SET deleted_at = true WHERE id=?")
@Where(clause = "deleted_at = false")
public class ProveedorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Pais")
    private String pais;
    @Column(name = "Ciudad")
    private String ciudad;
    @Column(name = "CP")
    private long cp;
    @Column(name = "deleted_at")
    private boolean deletedAt = false;


    @OneToMany(mappedBy = "proveedor",cascade = CascadeType.ALL)
    private List<ProveedorMaterialEntity> proveedorMaterialEntities;

    @ManyToOne
    @JoinColumn(name = "emprendimiento_id")
    EmprendimientoEntity emprendimiento;

}
