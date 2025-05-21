package hu.palferi.kektura.kektura_volangeodata_downloader.businessObject;

import java.time.Duration;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StopTimeBO {
    private String tripId;
    private String stopId;
    private Duration arrivalTime;
    private Duration departureTime;
    private Integer stopSequence;
    private Integer pickupType;
    private Integer dropOffType;
    private Float shapeDistTraveled;
}
