package com.folcamp.hechopornosotros.models.repositories;

import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmprendimientoFileRepository extends JpaRepository<EmprendimientoFileEntity, Long> {
    List<EmprendimientoFileEntity> findByEmprendimientoEntityAndType(EmprendimientoEntity emprendimientoEntity, String logo);
}
