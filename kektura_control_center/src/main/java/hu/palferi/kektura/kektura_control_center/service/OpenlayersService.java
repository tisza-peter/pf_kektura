package hu.palferi.kektura.kektura_control_center.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.palferi.kektura.kektura_control_center.businessObject.OpenLayersLayerDefinitionBO;
import hu.palferi.kektura.kektura_control_center.dto.OpenLayersLayerDefinitionDTO;
import hu.palferi.kektura.kektura_control_center.dtoMapper.OpenLayersLayerDefinitionDTOMapper;
import hu.palferi.kektura.kektura_control_center.entity.SqlViewLayerEntity;
import hu.palferi.kektura.kektura_control_center.entityMapper.OpenLayersLayerDefinitionEntityMapper;
import hu.palferi.kektura.kektura_control_center.enums.MapServiceType;
import hu.palferi.kektura.kektura_control_center.repository.SqlViewLayerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpenlayersService {

    private final SqlViewLayerRepository sqlViewLayerRepository;
    private final OpenLayersLayerDefinitionEntityMapper entityMapper;
    private final OpenLayersLayerDefinitionDTOMapper dtoMapper;
    @Value("${geoserver.workspace}")
    private String workspaceName;

public List<OpenLayersLayerDefinitionDTO> getAllLayers() {
    List<SqlViewLayerEntity> entities = sqlViewLayerRepository.findAll();
    List<OpenLayersLayerDefinitionDTO> result = new ArrayList<>();

    for (SqlViewLayerEntity entity : entities) {
        OpenLayersLayerDefinitionBO bo = entityMapper.fromEntity(entity, workspaceName, MapServiceType.WFS);
        OpenLayersLayerDefinitionDTO dto = dtoMapper.toDTO(bo);
        result.add(dto);
    }

    return result;
}

}
