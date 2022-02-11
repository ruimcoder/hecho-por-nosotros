package com.folcamp.hechopornosotros.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorNewDTO {
    private Long emprendimiento_id;
    private String nombre;
    private String direccion;
    private String pais;
    private String ciudad;
    private Long cp;
    private List<Long> materiales;
    private String especificar;
//    private String materiales;
}
