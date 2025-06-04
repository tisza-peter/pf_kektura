package hu.palferi.kektura.kektura_geoserver_configurator.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.palferi.kektura.kektura_geoserver_configurator.dto.SqlViewDTO;
import hu.palferi.kektura.kektura_geoserver_configurator.service.GeoServerService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/geoserver")
public class GeoServerController {

    private final GeoServerService geoServerService;

    public GeoServerController(GeoServerService geoServerService) {
        this.geoServerService = geoServerService;
    }

    @GetMapping("/layers")
    public List<String> getGeoServerLayers() {
        return geoServerService.getLayerNames();
    }

    @PostMapping("/initVolanStore")
    public Boolean InitGeoServerToVolanDb() {
        return geoServerService.initGeoServerToVolanDb();
    }

    @Operation(
        summary = "Publikus SQL nézet törlése",
        description = "A megadott névvel rendelkező SQL nézet törlése a GeoServerből."
    )
    @DeleteMapping("/dropIfExistsSqlViewLayer")
    public ResponseEntity<String> dropIfExistsSqlViewLayer(@RequestParam String layerName) {

        try {
            geoServerService.dropIfExistsSqlViewLayer(layerName);
            return ResponseEntity.ok("Layer törölve: " + layerName);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Hiba: " + e.getMessage());
        }
    }


    @Operation(
        summary = "Publikus SQL nézet létrehozása vagy frissítése",
        description = "Az SQL lekérdezésnek kötelező tartalmaznia `id` és `geom` mezőket. "
                    + "A nézet a megadott néven kerül publikálásra."
    )
    @PostMapping("/CreateOrRefreshAndPublishSqlViewLayer")
    public ResponseEntity<String> CreateOrRefreshAndPublishSqlViewLayer(
            @RequestBody SqlViewDTO request) {

        try {
            geoServerService.CreateOrRefreshAndPublishSqlViewLayer(
                request.getStoreName(),
                request.getLayerName(),
                request.getSqlStatement(),
                request.getSldStyleName(),
                request.getGeometryType(),
                request.getGeometrySrid()

            );
            return ResponseEntity.ok("Layer létrehozva/frissítve: " + request.getLayerName());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Hiba: " + e.getMessage());
        }
    }

}
