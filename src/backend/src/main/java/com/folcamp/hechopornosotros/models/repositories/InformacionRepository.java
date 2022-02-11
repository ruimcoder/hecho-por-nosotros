package com.folcamp.hechopornosotros.models.repositories;

import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import com.folcamp.hechopornosotros.models.entity.InformacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InformacionRepository extends JpaRepository<InformacionEntity, Long> {
    List<InformacionEntity> findByEmprendimientoEntityAndTipo(EmprendimientoEntity emprendimientoEntity, String tipo);
}
