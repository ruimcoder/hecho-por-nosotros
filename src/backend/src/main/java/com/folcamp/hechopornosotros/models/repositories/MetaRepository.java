package com.folcamp.hechopornosotros.models.repositories;

import com.folcamp.hechopornosotros.models.entity.MetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaRepository extends JpaRepository<MetaEntity, Long> {
}
