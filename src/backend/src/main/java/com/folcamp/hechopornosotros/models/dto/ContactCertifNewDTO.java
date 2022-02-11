package com.folcamp.hechopornosotros.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.folcamp.hechopornosotros.util.UploadBeanImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactCertifNewDTO {
    @JsonProperty("emprendimiento_id")
    private long emprendimientoId;
    @JsonProperty("contacto")
    private ContactoNewDTO contactoNewDTO;
    @JsonProperty("certificados")
    private List<UploadBeanImage> uploadBeanImages;
}
