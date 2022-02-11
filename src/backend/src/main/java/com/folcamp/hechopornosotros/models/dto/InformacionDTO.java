package com.folcamp.hechopornosotros.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.folcamp.hechopornosotros.util.UploadBeanImage;
import lombok.Data;

import java.util.List;

@Data
public class InformacionDTO {
    @JsonProperty("emprendimiento_id")
    private Long emprendimientoId;
    private String texto;
    @JsonProperty("url_video")
    private String urlVideo;
    private String tipo;
    @JsonProperty("imagenes")
    private List<UploadBeanImage> uploadBeanImages;
}
