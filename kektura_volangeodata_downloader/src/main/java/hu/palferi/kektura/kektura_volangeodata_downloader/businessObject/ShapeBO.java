package hu.palferi.kektura.kektura_volangeodata_downloader.businessObject;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShapeBO {
    private String shapeId;
    private Integer shapePtSequence;
    private Float shapePtLat;
    private Float shapePtLon;
    private Float shapeDistTraveled;
}
