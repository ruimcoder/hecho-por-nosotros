package com.folcamp.hechopornosotros.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListProveedorMaterialDTO {
    @JsonProperty("lista")
    private List<ProveedorMaterialNewDTO> proveedorMaterialNewDTOList;
}
