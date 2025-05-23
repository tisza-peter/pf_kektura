package hu.palferi.kektura.kektura_control_center.entityMapper;

import org.springframework.stereotype.Component;

import hu.palferi.kektura.kektura_control_center.businessObject.OpenLayersLayerDefinitionBO;
import hu.palferi.kektura.kektura_control_center.entity.SqlViewLayerEntity;
import hu.palferi.kektura.kektura_control_center.enums.MapServiceType;

@Component
public class OpenLayersLayerDefinitionEntityMapper {

    public OpenLayersLayerDefinitionBO fromEntity(SqlViewLayerEntity entity, String workspaceName, MapServiceType serviceType) {
        OpenLayersLayerDefinitionBO bo = new OpenLayersLayerDefinitionBO();
        bo.setId(entity.getId());
        bo.setWorkspaceName(workspaceName);
        bo.setServiceType(serviceType);
        bo.setStoreName(entity.getStoreName());
        bo.setLayerName(entity.getLayerName());
        bo.setOpacity(entity.getOpacity());
        bo.setZIndex(entity.getZIndex());
        bo.setMinZoom(entity.getMinZoom());
        bo.setMaxZoom(entity.getMaxZoom());
        return bo;
    }
}
