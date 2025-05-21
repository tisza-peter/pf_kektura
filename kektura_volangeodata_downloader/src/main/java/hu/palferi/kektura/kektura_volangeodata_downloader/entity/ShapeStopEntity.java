package hu.palferi.kektura.kektura_volangeodata_downloader.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "shape_stop", schema = "geom_staging")
public class ShapeStopEntity {
    @Id
    private String stopId;

    @Column(columnDefinition = "geometry")
    private String stopGeometry;

    // getter/setter stb.
}
