package hu.palferi.kektura.kektura_control_center.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import hu.palferi.kektura.kektura_control_center.entity.SqlViewLayer;
import hu.palferi.kektura.kektura_control_center.repository.SqlViewLayerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SqlViewLayerService {

    private final SqlViewLayerRepository repository;
    private final RestTemplate restTemplate;

    @Value("${geoserver-configurator.host}")
    private String geoserverConfiguratorHost;

    @Value("${geoserver-configurator.port}")
    private String geoserverConfiguratorPort;


    public void publishAllSqlViewLayers() {
        List<SqlViewLayer> layers = repository.findAll();
        URI Uri = URI.create("http://" + geoserverConfiguratorHost + ":" + geoserverConfiguratorPort + "/geoserver/createOrRefreshSqlViewLayer");
        for (SqlViewLayer layer : layers) {
            try {
                SqlViewRequest request = new SqlViewRequest(layer.getLayerName(), layer.getSqlStatement());
                HttpEntity<SqlViewRequest> entity = new HttpEntity<>(request);
                ResponseEntity<String> response = restTemplate.postForEntity(Uri, entity, String.class);
                System.out.println("Siker: " + response.getBody());
            } catch (Exception e) {
                System.err.println("Hiba a " + layer.getLayerName() + " feldolgozása során: " + e.getMessage());
            }
        }
    }

    public record SqlViewRequest(String layerName, String sqlStatement) {}
}
