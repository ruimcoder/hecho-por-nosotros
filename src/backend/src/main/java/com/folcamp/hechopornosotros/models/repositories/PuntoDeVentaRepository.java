package com.folcamp.hechopornosotros.models.repositories;

import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import com.folcamp.hechopornosotros.models.entity.PuntoDeVentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuntoDeVentaRepository extends JpaRepository <PuntoDeVentaEntity,Long>{
    List<PuntoDeVentaEntity> findByEmprendimiento(EmprendimientoEntity emprendimientoEntity);
}
