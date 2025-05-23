package hu.palferi.kektura.kektura_control_center.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sql_view_layer", schema = "geoserver")
public class SqlViewLayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "layer_name", nullable = false)
    private String layerName;

    @Column(name = "sql_statement", nullable = false)
    private String sqlStatement;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @Column(name = "last_execution", nullable = true)
    private LocalDateTime lastExecution;

    @Column(name = "opacity", nullable = false)
    private Double opacity;

    @Column(name = "z_index", nullable = false)
    private Integer zIndex;

    @Column(name = "min_zoom", nullable = false)
    private Integer minZoom;

    @Column(name = "max_zoom", nullable = false)
    private Integer maxZoom;


}
