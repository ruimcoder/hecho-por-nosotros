package com.folcamp.hechopornosotros.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import com.folcamp.hechopornosotros.models.entity.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprendimientoFileDTO {
    private long fileId;
    private long emprendimientoId;
    private String type;
}
