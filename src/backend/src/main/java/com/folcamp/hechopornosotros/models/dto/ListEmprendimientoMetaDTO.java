package com.folcamp.hechopornosotros.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListEmprendimientoMetaDTO {
    @JsonProperty("lista")
    private List<EmprendimientoMetaNewDTO> emprendimientoMetaNewDTOList;
}
