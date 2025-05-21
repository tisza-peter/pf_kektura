package hu.palferi.kektura.kektura_kekturageodata_downloader.service;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.palferi.kektura.kektura_kekturageodata_downloader.entity.GpxLinkEntity;
import hu.palferi.kektura.kektura_kekturageodata_downloader.repository.GpxLinkRepository;

@Service
public class GpxFileService {

    @Value("${file.storage.path}")
    private String storagePath;

    private final GpxLinkRepository repository;

    public GpxFileService(GpxLinkRepository repository) {
        this.repository = repository;
    }

    public List<String> downloadAndSaveAllGpxFiles() {
        List<GpxLinkEntity> links = repository.findAll();
        List<String> localLinks = new ArrayList<>();

        for (GpxLinkEntity link : links) {
            try {
                // Fájl neve az URL utolsó szegmense
                String fileName = Paths.get(new URI(link.getLinkPath()).getPath()).getFileName().toString();
                Path targetPath = Paths.get(storagePath, fileName);
                Files.createDirectories(targetPath.getParent());

                try (InputStream in = new URL(link.getLinkPath()).openStream()) {
                    Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }
                localLinks.add(targetPath.toString());

            } catch (Exception e) {
                System.err.println("Hiba a letöltésnél: " + link.getLinkPath());
                e.printStackTrace();
            }
        }

        return localLinks;
    }
}
