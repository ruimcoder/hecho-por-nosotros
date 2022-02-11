package com.folcamp.hechopornosotros.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PuntoDeVentaNewDTO {
    @JsonProperty("emprendimiento_id")
    private Long emprendimientoId;
    private String direccion;
    private String direccionSec;
    private String nombre;
    private String pais;
    private String ciudad;
    private Long cp;
    private String tipo;
    private String contacto;
    private String web;
}
