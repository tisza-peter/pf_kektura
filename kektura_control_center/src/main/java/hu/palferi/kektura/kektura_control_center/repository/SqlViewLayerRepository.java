package hu.palferi.kektura.kektura_control_center.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.palferi.kektura.kektura_control_center.entity.SqlViewLayer;

public interface SqlViewLayerRepository extends JpaRepository<SqlViewLayer, Long> {
}
