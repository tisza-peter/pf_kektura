
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
@Table(name = "stops", schema = "gtfs_staging")
public class StopEntity extends BaseEntity {
    private String stopId;
    private String stopName;
    private Float stopLat;
    private Float stopLon;
    private Integer locationType;
    private String parentStation;
    private Integer platformCode;
}
