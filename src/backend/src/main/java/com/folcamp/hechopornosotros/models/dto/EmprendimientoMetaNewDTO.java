package com.folcamp.hechopornosotros.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprendimientoMetaNewDTO {
    @JsonProperty("id_emprendimiento")
    private Long idEmprendimiento;
    private List<Long> metas;
    @JsonProperty("comentario_meta")
    private String comentarioMeta;
}

