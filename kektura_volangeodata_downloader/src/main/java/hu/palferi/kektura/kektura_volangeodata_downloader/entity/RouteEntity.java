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
@Table(name = "routes", schema = "gtfs_staging")
public class RouteEntity extends BaseEntity {
    private String agencyId;
    private String routeId;
    private String routeShortName;
    private String routeLongName;
    private Integer routeType;
    private String routeUrl;
    private String routeColor;
    private String routeTextColor;
    private String routeNetwork;
}
