package com.folcamp.hechopornosotros.models.repositories;

import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import com.folcamp.hechopornosotros.models.entity.EmprendimientoMetaEntity;
import com.folcamp.hechopornosotros.models.entity.MetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmprendimientoMetaRepository extends JpaRepository<EmprendimientoMetaEntity, Long> {
    Optional<EmprendimientoMetaEntity> findByEmprendimientoAndMeta(EmprendimientoEntity byId, MetaEntity byId1);
}
