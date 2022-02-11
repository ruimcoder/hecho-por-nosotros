package com.folcamp.hechopornosotros.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorDetailDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String pais;
    private String ciudad;
    private Long cp;
    private List<MaterialesDTO> materiales;
    private String especificar;
}
