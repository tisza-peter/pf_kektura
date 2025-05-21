package hu.palferi.kektura.kektura_volangeodata_downloader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import hu.palferi.kektura.kektura_volangeodata_downloader.entity.ShapeStopEntity;

public interface ShapeStopRepository extends JpaRepository<ShapeStopEntity, String> {

    @Procedure(procedureName = "geom_staging.update_stop_geometries")
    void updateStopGeometries();
}
