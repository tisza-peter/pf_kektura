package hu.palferi.kektura.kektura_volangeodata_downloader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@AllArgsConstructor
@Builder
@Getter
public class ShapeDTO {
    private String shapeId;
    private String shapePtSequence;
    private String shapePtLat;
    private String shapePtLon;
    private String shapeDistTraveled;
}
