package hu.palferi.kektura.kektura_kekturageodata_downloader.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.palferi.kektura.kektura_kekturageodata_downloader.service.GpxLinksService;
import hu.palferi.kektura.kektura_kekturageodata_downloader.service.GpxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/gpx-links")
@Tag(name = "Gpx kereső", description = "Weboldalról kinyerhető .gpx linkek")
public class GpxLinksController {

    private final GpxLinksService gpxLinksService;
    private GpxService gpxService;

    public GpxLinksController(GpxLinksService gpxLinksService, GpxService gpxService) {
        this.gpxService = gpxService;
        this.gpxLinksService = gpxLinksService;
    }

    @GetMapping("/get-links")
    @Operation(summary = "GPX linkek lekérése", description = "Megadott URL alapján visszaadja az oldalon található .gpx linkeket")
    public List<String> getGpxLinks(@RequestParam String url) {
        return gpxService.getGpxLinks(url);
    }

    @PostMapping("/load-links")
    @Operation(summary = "GPX linkek listájának frissítése", description = "Az eltárolt URL-ek alapján frissíti az oldalon található .gpx linkekkel a gpx file listát")
    public String refreshGpxLinks() {
        gpxLinksService.refreshGpxLinks();
        return "A gpx file lista frissítése sikeresen megtörtént.";
    }

}
