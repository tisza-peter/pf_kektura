package hu.palferi.kektura.kektura_volangeodata_downloader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import hu.palferi.kektura.kektura_volangeodata_downloader.entity.ShapeRouteEntity;

public interface ShapeRouteRepository extends JpaRepository<ShapeRouteEntity, String> {

    @Procedure(procedureName = "geom_staging.update_shape_geometries")
    void updateShapeGeometries();
}
