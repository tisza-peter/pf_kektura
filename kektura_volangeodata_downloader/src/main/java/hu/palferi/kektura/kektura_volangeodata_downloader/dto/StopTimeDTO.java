
package hu.palferi.kektura.kektura_volangeodata_downloader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@AllArgsConstructor
@Builder
@Getter
public class StopTimeDTO {
    private String tripId;
    private String stopId;
    private String arrivalTime;
    private String departureTime;
    private String stopSequence;
    private String pickupType;
    private String dropOffType;
    private String shapeDistTraveled;
}
