
package hu.palferi.kektura.kektura_volangeodata_downloader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@AllArgsConstructor
@Builder
@Getter
public class TripDTO {
    private String routeId;
    private String tripId;
    private String serviceId;
    private String tripShortName;
    private String tripHeadsign;
    private String directionId;
    private String shapeId;
    private String wheelchairAccessible;
}
