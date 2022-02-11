package com.folcamp.hechopornosotros.models.repositories;

import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprendimientoRepository extends JpaRepository<EmprendimientoEntity,Long>{
    Long countByPublicado(boolean b);
}
