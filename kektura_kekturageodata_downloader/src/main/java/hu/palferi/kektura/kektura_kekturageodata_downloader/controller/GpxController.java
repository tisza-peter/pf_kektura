package hu.palferi.kektura.kektura_kekturageodata_downloader.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.palferi.kektura.kektura_kekturageodata_downloader.service.GpxFileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/gpx")
@RequiredArgsConstructor
public class GpxController {

    private final GpxFileService gpxFileService;

    @Operation(summary = "Download GPX files from external URLs and save them to filesystem")
    @PostMapping("/download")
    public List<String> downloadAndSaveGpxFiles() {
        return gpxFileService.downloadAndSaveAllGpxFiles();
    }
}
