package hu.palferi.kektura.kektura_control_center.entityMapper;

import org.springframework.stereotype.Component;

import hu.palferi.kektura.kektura_control_center.businessObject.GeoserverLayerDefinitionBO;
import hu.palferi.kektura.kektura_control_center.entity.SqlViewLayerEntity;

@Component
public class GeoserverLayerDefinitionEntityMapper {

    public static GeoserverLayerDefinitionBO fromEntity(SqlViewLayerEntity entity, String workspaceName) {
        GeoserverLayerDefinitionBO bo = new GeoserverLayerDefinitionBO();
        bo.setId(entity.getId());
        bo.setWorkspaceName(workspaceName);
        bo.setStoreName(entity.getStoreName());
        bo.setLayerName(entity.getLayerName());
        bo.setSqlStatement(entity.getSqlStatement());
        bo.setLastUpdate(entity.getLastUpdate());
        bo.setLastExecution(entity.getLastExecution());
        bo.setSldStyleName(entity.getSldStyleName());
        bo.setGeometryType(entity.getGeometryType());
        bo.setGeometrySrid(entity.getGeometrySrid());
        return bo;
    }
}
