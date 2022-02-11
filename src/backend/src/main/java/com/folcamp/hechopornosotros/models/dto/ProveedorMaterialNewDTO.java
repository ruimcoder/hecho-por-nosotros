package com.folcamp.hechopornosotros.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorMaterialNewDTO {
    private Long id_material;
    private Long id_proveedor;
    private String especificar;
}
