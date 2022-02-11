package com.folcamp.hechopornosotros.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactoDTO {
    private Long id;
    private String direccion;
    private String pais;
    private String telefono;
    private String email;
    private String instagram;
    private String facebook;
    private double longitud;
    private double latitud;
}
