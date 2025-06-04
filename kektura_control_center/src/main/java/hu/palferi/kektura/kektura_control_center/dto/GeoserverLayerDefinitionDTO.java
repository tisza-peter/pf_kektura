package hu.palferi.kektura.kektura_control_center.dto;

import lombok.Data;

@Data
public class GeoserverLayerDefinitionDTO {
    private String storeName;
    private String layerName;
    private String sqlStatement;
    private String sldStyleName;
    private String geometryType;
    private Integer geometrySrid;
}
