package org.explorer.crew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "org.explorer.commandcenter.feign.api")
@EntityScan("org.explorer.commandcenter.model")
public class CrewApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrewApplication.class, args);
    }

}
