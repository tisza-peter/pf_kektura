package hu.palferi.kektura.kektura_control_center.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.palferi.kektura.kektura_control_center.entity.SqlViewLayerEntity;

public interface SqlViewLayerRepository extends JpaRepository<SqlViewLayerEntity, Long> {
}
