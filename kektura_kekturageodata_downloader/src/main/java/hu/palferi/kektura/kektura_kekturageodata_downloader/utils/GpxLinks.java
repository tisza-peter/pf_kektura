package hu.palferi.kektura.kektura_kekturageodata_downloader.utils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GpxLinks {
    
    @Value("${webscraper.url}")
    private String webScraperUrl;

    public List<String> getGpxLinksFromWebpage(String url) {
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    Map<String, String> requestBody = new HashMap<>();
    requestBody.put("url", url);
    requestBody.put("selector", "a[href$='.gpx']");

    HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

    try {
        ResponseEntity<JsonNode> response = restTemplate.postForEntity(
            webScraperUrl,
            request,
            JsonNode.class
        );
        List<String> eredmenyek = new ArrayList<>();
        JsonNode body = response.getBody();
        if (body != null) {
            JsonNode results = body.get("results");

            if (results != null && results.isArray()) {
                for (JsonNode elem : results) {
                    eredmenyek.add(elem.asText());
                }
            }
        }

            return eredmenyek;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
