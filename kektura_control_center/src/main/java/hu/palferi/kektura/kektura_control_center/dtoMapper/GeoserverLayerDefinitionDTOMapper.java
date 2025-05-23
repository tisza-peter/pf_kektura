package hu.palferi.kektura.kektura_control_center.dtoMapper;

import org.springframework.stereotype.Component;

import hu.palferi.kektura.kektura_control_center.businessObject.GeoserverLayerDefinitionBO;
import hu.palferi.kektura.kektura_control_center.dto.GeoserverLayerDefinitionDTO;

@Component
public class GeoserverLayerDefinitionDTOMapper {

    public static GeoserverLayerDefinitionDTO toDTO(GeoserverLayerDefinitionBO bo) {
        GeoserverLayerDefinitionDTO dto = new GeoserverLayerDefinitionDTO();
        dto.setStoreName(bo.getStoreName());
        dto.setLayerName(bo.getLayerName());
        dto.setSqlStatement(bo.getSqlStatement());
        return dto;
    }

    public static GeoserverLayerDefinitionBO toBO(GeoserverLayerDefinitionDTO dto) {
        GeoserverLayerDefinitionBO bo = new GeoserverLayerDefinitionBO();
        bo.setStoreName(dto.getStoreName());
        bo.setLayerName(dto.getLayerName());
        bo.setSqlStatement(dto.getSqlStatement());
        return bo;
    }
}
