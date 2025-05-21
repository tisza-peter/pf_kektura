package hu.palferi.kektura.kektura_kekturageodata_downloader.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Configuration
@ConfigurationProperties(prefix = "kektura.hu.urls")
public class UrlProperties {
    private List<String> url;

    public void setUrl(List<String> url) {
        this.url = url;
    }
}
