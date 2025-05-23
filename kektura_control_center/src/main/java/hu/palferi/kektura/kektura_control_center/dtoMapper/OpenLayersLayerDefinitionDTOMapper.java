package hu.palferi.kektura.kektura_control_center.dtoMapper;

import org.springframework.stereotype.Component;

import hu.palferi.kektura.kektura_control_center.businessObject.OpenLayersLayerDefinitionBO;
import hu.palferi.kektura.kektura_control_center.dto.OpenLayersLayerDefinitionDTO;

@Component
public class OpenLayersLayerDefinitionDTOMapper {

    public OpenLayersLayerDefinitionDTO toDTO(OpenLayersLayerDefinitionBO bo) {
        OpenLayersLayerDefinitionDTO dto = new OpenLayersLayerDefinitionDTO();
        dto.setWorkspaceName(bo.getWorkspaceName());
        dto.setServiceType(bo.getServiceType());
        dto.setLayerName(bo.getLayerName());
        dto.setOpacity(bo.getOpacity());
        dto.setZIndex(bo.getZIndex());
        dto.setMinZoom(bo.getMinZoom());
        dto.setMaxZoom(bo.getMaxZoom());
        return dto;
    }

    public OpenLayersLayerDefinitionBO toBO(OpenLayersLayerDefinitionDTO dto) {
        OpenLayersLayerDefinitionBO bo = new OpenLayersLayerDefinitionBO();
        bo.setWorkspaceName(dto.getWorkspaceName());
        bo.setServiceType(dto.getServiceType());
        bo.setLayerName(dto.getLayerName());
        bo.setOpacity(dto.getOpacity());
        bo.setZIndex(dto.getZIndex());
        bo.setMinZoom(dto.getMinZoom());
        bo.setMaxZoom(dto.getMaxZoom());
        return bo;
    }
}
