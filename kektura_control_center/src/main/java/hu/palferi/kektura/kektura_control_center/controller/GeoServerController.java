package hu.palferi.kektura.kektura_control_center.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.palferi.kektura.kektura_control_center.service.GeoServerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/geoserver")
@RequiredArgsConstructor
public class GeoServerController {

    private final GeoServerService geoServerService;

    @PostMapping("/publishAllSqlViewLayers")
    public ResponseEntity<String> publishAll() {
        geoServerService.publishAllSqlViewLayers();
        return ResponseEntity.ok("Minden SQL View Layer publikálása elindult.");
    }



}
