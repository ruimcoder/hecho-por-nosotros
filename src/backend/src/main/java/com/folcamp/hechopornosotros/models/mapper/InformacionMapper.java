package com.folcamp.hechopornosotros.models.mapper;

import com.folcamp.hechopornosotros.models.dto.InformacionResponseDTO;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoFileEntity;
import com.folcamp.hechopornosotros.models.entity.InformacionEntity;
import com.folcamp.hechopornosotros.models.entity.InformacionFileEntity;
import com.folcamp.hechopornosotros.models.repositories.InformacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InformacionMapper {

    @Autowired
    private InformacionRepository informacionRepository;

    public InformacionResponseDTO mapInformacionEntityToInformacionDTO(InformacionEntity informacionEntity) {

        return new InformacionResponseDTO(
                informacionEntity.getId(),
                informacionEntity.getTexto(),
                informacionEntity.getUrlVideo(),
                informacionEntity.getTipo(),
                getImagenesInformacion(informacionEntity)
        );

    }

    private List<String> getImagenesInformacion(InformacionEntity informacionEntity) {
        List<String> imagenes = new ArrayList<>();
        for (InformacionFileEntity informacionFileEntity : informacionEntity.getFileInformacionEntities()) {
            imagenes.add(informacionFileEntity.getFileEntity().getUrl());
        }

        return imagenes;
    }

}
