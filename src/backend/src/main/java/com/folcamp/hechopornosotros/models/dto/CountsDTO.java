package com.folcamp.hechopornosotros.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CountsDTO {
    private Long creados;
    private Long publicados;
    @JsonProperty("no_publicados")
    private Long noPublicados;
}
