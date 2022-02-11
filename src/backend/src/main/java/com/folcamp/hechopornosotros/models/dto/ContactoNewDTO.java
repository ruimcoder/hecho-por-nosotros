package com.folcamp.hechopornosotros.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactoNewDTO {
    @JsonProperty("emprendimiento_id")
    private Long emprendimientoId;
    private String direccion;
    private String pais;
    private String telefono;
    private String email;
    private String instagram;
    private String facebook;
    private double longitud;
    private double latitud;
}
