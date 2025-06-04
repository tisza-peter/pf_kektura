package hu.palferi.kektura.kektura_control_center.service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import hu.palferi.kektura.kektura_control_center.businessObject.GeoserverLayerDefinitionBO;
import hu.palferi.kektura.kektura_control_center.dto.GeoserverLayerDefinitionDTO;
import hu.palferi.kektura.kektura_control_center.dtoMapper.GeoserverLayerDefinitionDTOMapper;
import hu.palferi.kektura.kektura_control_center.entity.SqlViewLayerEntity;
import hu.palferi.kektura.kektura_control_center.entityMapper.GeoserverLayerDefinitionEntityMapper;
import hu.palferi.kektura.kektura_control_center.repository.SqlViewLayerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeoServerService {

    private final SqlViewLayerRepository repository;
    private final RestTemplate restTemplate;

    @Value("${geoserver-configurator.host}")
    private String geoserverConfiguratorHost;

    @Value("${geoserver-configurator.port}")
    private String geoserverConfiguratorPort;

    @Value("${geoserver.workspace}")
    private String workspaceName;


    public void publishAllSqlViewLayers() {
        List<SqlViewLayerEntity> layerEntities = repository.findAll();
        URI Uri = URI.create("http://" + geoserverConfiguratorHost + ":" + geoserverConfiguratorPort + "/geoserver/CreateOrRefreshAndPublishSqlViewLayer");
                                                                                                                      
        for (SqlViewLayerEntity layerEntity : layerEntities) {
            try {
                GeoserverLayerDefinitionBO geoserverLayerDefinitionBO = GeoserverLayerDefinitionEntityMapper.fromEntity(layerEntity, workspaceName);
                GeoserverLayerDefinitionDTO geoserverLayerDefinitionDTO = GeoserverLayerDefinitionDTOMapper.toDTO(geoserverLayerDefinitionBO);
                HttpEntity<GeoserverLayerDefinitionDTO> httpEntity = new HttpEntity<>(geoserverLayerDefinitionDTO);
                ResponseEntity<String> response = restTemplate.postForEntity(Uri, httpEntity, String.class);
                layerEntity.setLastExecution(LocalDateTime.now());
                repository.save(layerEntity);
                System.out.println("Siker: " + response.getBody());
            } catch (Exception e) {
                System.err.println("Hiba a " + layerEntity.getLayerName() + " feldolgozása során: " + e.getMessage());
            }
        }
    }
}
