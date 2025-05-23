package hu.palferi.kektura.kektura_control_center.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VolanGeodataRefreshService {

    private static final Logger logger = LoggerFactory.getLogger(VolanGeodataRefreshService.class);

    private final RestTemplate restTemplate;

    @Value("${volan.api.base.url}")
    private String VOLAN_API_BASE_URL;

    public VolanGeodataRefreshService() {
        this.restTemplate = new RestTemplate();
    }


    @Scheduled(cron = "0 0 3 * * *")
    public void scheduledRefresh() {
        logger.info("Időzített frissítés elindul (scheduler)");
        refreshAll();
    }

    public void refreshAll() {

        String BASE_GTFS_URL = VOLAN_API_BASE_URL + "/gtfs-loader";
        String BASE_SHAPES_URL = VOLAN_API_BASE_URL + "/shapes";
        String BASE_SCHEMA_URL = VOLAN_API_BASE_URL + "/schema-switch";

        try {
            post(BASE_GTFS_URL + "/download", "ZIP letöltés");
            post(BASE_GTFS_URL + "/unzip", "Kitömörítés");
            get(BASE_GTFS_URL + "/check-csv-headers", "CSV fejléc ellenőrzés");
            post(BASE_GTFS_URL + "/split", "Darabolás");
            post(BASE_GTFS_URL + "/load-all", "Összes adat betöltése");
            post(BASE_SHAPES_URL + "/update-route-geometries", "Útvonal geometria frissítés");
            post(BASE_SHAPES_URL + "/update-stop-geometries", "Megálló geometria frissítés");
            post(BASE_SCHEMA_URL + "/all-data-publish", "Séma váltás");

            logger.info("Minden lépés sikeresen lefutott.");
        } catch (Exception e) {
            logger.error("Hiba történt az adatok frissítése során", e);
            throw new RuntimeException("Hiba a frissítés során: " + e.getMessage(), e);
        }
    }

    private void post(String url, String taskDescription) {
        logger.info("{} indítása: POST {}", taskDescription, url);
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        logger.info("{} válasz: {}", taskDescription, response.getBody());
    }

    private void get(String url, String taskDescription) {
        logger.info("{} indítása: GET {}", taskDescription, url);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        logger.info("{} válasz: {}", taskDescription, response.getBody());
    }
}
