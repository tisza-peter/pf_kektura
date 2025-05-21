package hu.palferi.kektura.kektura_kekturageodata_downloader.service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.palferi.kektura.kektura_kekturageodata_downloader.config.UrlProperties;
import hu.palferi.kektura.kektura_kekturageodata_downloader.utils.GpxLinks;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GpxService {

    private final UrlProperties urlProperties;

    @Autowired
    private GpxLinks gpxLinks;

    public Set<String> fetchGpxLinks() {
        List<String> webpageUrls = urlProperties.getUrl();
        Set<String> allGpxFileLinks = new HashSet<>();
        for (String webpageUrl : webpageUrls) {
            log.info("Fetching GPX links from: {}", webpageUrl);
            List<String> gpxLinks = getGpxLinks(webpageUrl);
            allGpxFileLinks.addAll(gpxLinks);
            log.info("Found {} GPX links from: {}", gpxLinks.size(), webpageUrl);   
        }
        return allGpxFileLinks;
    }


    public List<String> getGpxLinks(String url) {
        
        // Itt helyezkedik el az általad megírt Playwright / RestTemplate logika
        
        return gpxLinks.getGpxLinksFromWebpage(url);
    }
    
}
