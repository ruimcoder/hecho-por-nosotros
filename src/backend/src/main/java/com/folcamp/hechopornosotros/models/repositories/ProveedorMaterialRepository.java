package com.folcamp.hechopornosotros.models.repositories;

import com.folcamp.hechopornosotros.models.entity.MaterialesEntity;
import com.folcamp.hechopornosotros.models.entity.ProveedorEntity;
import com.folcamp.hechopornosotros.models.entity.ProveedorMaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProveedorMaterialRepository extends JpaRepository<ProveedorMaterialEntity, Long>{
    Optional<ProveedorMaterialEntity> findByMaterialAndProveedor(MaterialesEntity byId, ProveedorEntity byId1);
}
