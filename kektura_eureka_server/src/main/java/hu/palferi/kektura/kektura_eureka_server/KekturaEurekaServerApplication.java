package hu.palferi.kektura.kektura_eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class KekturaEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(KekturaEurekaServerApplication.class, args);
    }
}
