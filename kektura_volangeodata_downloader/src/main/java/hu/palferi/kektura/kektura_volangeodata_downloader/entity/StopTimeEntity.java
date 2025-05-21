package hu.palferi.kektura.kektura_volangeodata_downloader.entity;

import java.time.Duration;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stop_times", schema = "gtfs_staging")
public class StopTimeEntity extends BaseEntity {
    private String tripId;
    private String stopId;
    private Long arrivalTime;
    private Long departureTime;
    @Transient
    private Duration arrivalTimeInterval; // Számított mező
    @Transient
    private Duration departureTimeInterval;
    private Integer stopSequence;
    private Integer pickupType;
    private Integer dropOffType;
    private Float shapeDistTraveled;
}
