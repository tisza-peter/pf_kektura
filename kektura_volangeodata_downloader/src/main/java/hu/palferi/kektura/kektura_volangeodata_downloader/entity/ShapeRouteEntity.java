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
@Table(name = "shape_route", schema = "geom_staging")
public class ShapeRouteEntity {
    @Id
    private String shapeId;

    @Column(columnDefinition = "geometry")
    private String shapeGeometry;

    // getter/setter stb.
}
