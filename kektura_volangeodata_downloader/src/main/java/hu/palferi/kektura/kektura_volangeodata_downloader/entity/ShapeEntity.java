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
@Table(name = "shapes", schema = "gtfs_staging")
public class ShapeEntity extends BaseEntity {
    private String shapeId;
    private Integer shapePtSequence;
    private Float shapePtLat;
    private Float shapePtLon;
    private Float shapeDistTraveled;
}
