package com.folcamp.hechopornosotros.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.folcamp.hechopornosotros.util.UploadBeanImage;
import lombok.Data;

import java.util.List;

@Data
public class CertificadosNewDTO {
    @JsonProperty("emprendimiento_id")
    private Long emprendimientoId;
    @JsonProperty("certificados")
    private List<UploadBeanImage> uploadBeanImages;
}
