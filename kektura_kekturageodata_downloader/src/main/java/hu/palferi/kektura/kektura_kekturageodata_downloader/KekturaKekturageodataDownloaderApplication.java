package hu.palferi.kektura.kektura_kekturageodata_downloader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import hu.palferi.kektura.kektura_kekturageodata_downloader.config.UrlProperties;

@SpringBootApplication
@EnableConfigurationProperties(UrlProperties.class)
public class KekturaKekturageodataDownloaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(KekturaKekturageodataDownloaderApplication.class, args);
	}

    @Bean
    public WebMvcConfigurer webMvcConfigurer(@Value("${file.storage.path}") String storageLocation) {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/files/**")
                        .addResourceLocations("file:" + storageLocation + "/");
            }
        };
    }

}
