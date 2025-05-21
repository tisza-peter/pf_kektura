package hu.palferi.kektura.kektura_volangeodata_downloader.dtoMapper;

import hu.palferi.kektura.kektura_volangeodata_downloader.businessObject.ShapeBO;
import hu.palferi.kektura.kektura_volangeodata_downloader.dto.ShapeDTO;
import hu.palferi.kektura.kektura_volangeodata_downloader.utils.MapperUtil;

public class ShapeDTOMapper {

    public static ShapeBO fromDTO(ShapeDTO dto) {
        return ShapeBO.builder()
                .shapeId(dto.getShapeId())
                .shapePtSequence(MapperUtil.parseIntegerOrDefault(dto.getShapePtSequence(),null))
                .shapePtLat(MapperUtil.parseFloatOrDefault(dto.getShapePtLat()))
                .shapePtLon(MapperUtil.parseFloatOrDefault(dto.getShapePtLon()))
                .shapeDistTraveled(MapperUtil.parseFloatOrDefault(dto.getShapeDistTraveled()))
                .build();
    }
}
