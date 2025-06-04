package hu.palferi.kektura.kektura_control_center.dto;

import hu.palferi.kektura.kektura_control_center.enums.MapServiceType;
import lombok.Data;

@Data
public class OpenLayersLayerDefinitionDTO {
    private String workspaceName;
    private MapServiceType serviceType;
    private String layerName;
    private Double opacity;
    private Integer zIndex;
    private Integer minZoom;
    private Integer maxZoom;
    private String sldStyleName;
    private String olStyleJson;
}
