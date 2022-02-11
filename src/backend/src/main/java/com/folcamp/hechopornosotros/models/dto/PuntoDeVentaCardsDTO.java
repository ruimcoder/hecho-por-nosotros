package com.folcamp.hechopornosotros.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PuntoDeVentaCardsDTO {
    @JsonProperty("emprendimiento_id")
    private Long emprendimientoId;
    @JsonProperty("puntos_de_venta")
    private List<PuntoDeVentaDTO> puntosDeVenta;
}
