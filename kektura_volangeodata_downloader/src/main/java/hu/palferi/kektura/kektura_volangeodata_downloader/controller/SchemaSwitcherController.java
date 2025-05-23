package hu.palferi.kektura.kektura_volangeodata_downloader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.palferi.kektura.kektura_volangeodata_downloader.service.SchemaSwitcherService;

@RestController
@RequestMapping("/api/schema-switch")
public class SchemaSwitcherController {

    @Autowired
    private SchemaSwitcherService schemaSwitcherService;

    @PostMapping("/all-data-publish")
    public ResponseEntity<String> switchMultiple() {
        Object tableNames = schemaSwitcherService.switchAllTables();
        return ResponseEntity.ok("Sikeres schema csere: " + tableNames);
    }
}
