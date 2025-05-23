package hu.palferi.kektura.kektura_control_center.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.palferi.kektura.kektura_control_center.service.VolanGeodataRefreshService;

@RestController
@RequestMapping("/api/volan")
public class VolanGeodataRefreshController {

    private final VolanGeodataRefreshService volanRefreshService;

    public VolanGeodataRefreshController(VolanGeodataRefreshService refreshService) {
        this.volanRefreshService = refreshService;
    }

    @PostMapping("/geodata-refresh")
    public ResponseEntity<String> runRefresh() {
        volanRefreshService.refreshAll();
        return ResponseEntity.ok("Adatok frissítése sikeresen elindítva és lefutott.");
    }
}
