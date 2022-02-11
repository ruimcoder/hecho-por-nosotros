package com.folcamp.hechopornosotros.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    @JsonProperty("id_emprendimiento")
    private long id;
    private String name;
    private String tipo;
    private String email;
    private String pais;
    private String logo;
    private String portada;

}
