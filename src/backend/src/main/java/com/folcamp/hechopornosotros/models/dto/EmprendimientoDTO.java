package com.folcamp.hechopornosotros.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprendimientoDTO {
    private Long id;
    private String nombre;
    private Boolean publicado;
    private String slogan;
    private String genero;
    private long registro;
    private String tipo;
    private String pertenece;
    private Boolean exporta;
    private long count_empleados;
    private long count_mujeres;
    private long count_jovenes;
    private long count_colaboracion;
    private long count_mujeres_colaboracion;
    private long count_jovenes_colaboracion;
    private String comentario_meta;
}
