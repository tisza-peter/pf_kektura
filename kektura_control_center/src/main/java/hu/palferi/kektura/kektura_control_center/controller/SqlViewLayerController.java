package hu.palferi.kektura.kektura_control_center.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.palferi.kektura.kektura_control_center.service.SqlViewLayerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
public class SqlViewLayerController {

    private final SqlViewLayerService service;

    @PostMapping("/publishAllSqlViewLayers")
    public ResponseEntity<String> publishAll() {
        service.publishAllSqlViewLayers();
        return ResponseEntity.ok("Minden SQL View Layer publikálása elindult.");
    }
}
