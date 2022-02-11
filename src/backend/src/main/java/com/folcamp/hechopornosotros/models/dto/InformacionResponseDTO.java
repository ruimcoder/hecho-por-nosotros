package com.folcamp.hechopornosotros.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformacionResponseDTO {

    private long id;
    private String texto;
    private String urlVideo;
    private String tipo;
    private List<String> imagenes;
}
