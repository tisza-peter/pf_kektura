package hu.palferi.kektura.kektura_volangeodata_downloader.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "trips", schema = "gtfs_staging")
public class TripEntity extends BaseEntity {
    private String tripId;
    private String routeId;
    private String serviceId;
    private String tripShortName;
    private String tripHeadsign;
    private Integer directionId;
    private String shapeId;
    private Integer wheelchairAccessible;
}
