package com.folcamp.hechopornosotros.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PuntoDeVentaDTO {
    private Long id;
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
