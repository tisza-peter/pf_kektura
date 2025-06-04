package hu.palferi.kektura.kektura_control_center.businessObject;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GeoserverLayerDefinitionBO {
    private Long id;
    private String workspaceName;
    private String storeName;
    private String layerName;
    private String sqlStatement;
    private LocalDateTime lastUpdate;
    private LocalDateTime lastExecution;
    private String sldStyleName;
    private String geometryType;
    private Integer geometrySrid;
}
