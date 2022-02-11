package com.folcamp.hechopornosotros.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String pais;
    private String ciudad;
    private Long cp;
}
