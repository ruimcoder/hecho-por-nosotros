package com.folcamp.hechopornosotros.models.repositories;


import com.folcamp.hechopornosotros.models.entity.EmprendimientoEntity;
import com.folcamp.hechopornosotros.models.entity.UserEmprendimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEmprendimientoRepository extends JpaRepository <UserEmprendimientoEntity,Long> {
    boolean existsByUid (String uid);

    List<UserEmprendimientoEntity> findAllByUid(String uid);

    Optional<UserEmprendimientoEntity> findByEmprendimiento(EmprendimientoEntity emprendimientoEntity);
}
