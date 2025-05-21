package hu.palferi.kektura.kektura_volangeodata_downloader.entityMapper;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.ShapeBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.entity.ShapeEntity;

public class ShapeEntityMapper {

    public static ShapeEntity fromBO(ShapeBO bo) {
        return ShapeEntity.builder()
                .shapeId(bo.getShapeId())
                .shapePtSequence(bo.getShapePtSequence())
                .shapePtLat(bo.getShapePtLat())
                .shapePtLon(bo.getShapePtLon())
                .shapeDistTraveled(bo.getShapeDistTraveled())
                .build();
    }
}
