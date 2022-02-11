package com.folcamp.hechopornosotros.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorDetailCardsDTO {
    @JsonProperty("emprendimiento_id")
    private Long emprendimientoId;
    @JsonProperty("proveedores")
    private List<ProveedorDetailDTO> proveedores;
}
