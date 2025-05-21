package hu.palferi.kektura.kektura_volangeodata_downloader.businessObject;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TripBO {
    private String tripId;
    private String routeId;
    private String serviceId;
    private String tripShortName;
    private String tripHeadsign;
    private Integer directionId;
    private String shapeId;
    private Integer wheelchairAccessible;
}
