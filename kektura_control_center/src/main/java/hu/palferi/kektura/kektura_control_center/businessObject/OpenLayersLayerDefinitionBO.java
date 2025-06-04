package hu.palferi.kektura.kektura_control_center.businessObject;

import hu.palferi.kektura.kektura_control_center.enums.MapServiceType;
import lombok.Data;

@Data
public class OpenLayersLayerDefinitionBO {
    private Long id;
    private String workspaceName;
    private MapServiceType serviceType;
    private String storeName;
    private String layerName;
    private Double opacity;
    private Integer zIndex;
    private Integer minZoom;
    private Integer maxZoom;
    private String sldStyleName;
    private String olStyleJson;
}
