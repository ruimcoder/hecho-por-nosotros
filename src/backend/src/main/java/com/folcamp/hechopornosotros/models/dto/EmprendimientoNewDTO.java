package com.folcamp.hechopornosotros.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.folcamp.hechopornosotros.util.UploadBeanImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprendimientoNewDTO {
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
    @JsonProperty("logo")
    private UploadBeanImage uploadBeanImage;
    @JsonProperty("portada")
    private UploadBeanImage portada;

    private boolean asia;
    private boolean africa;
    private boolean norteamerica;
    private boolean sudamerica;
    private boolean antartida;
    private boolean europa;
    private boolean oceania;
}
