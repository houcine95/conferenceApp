package com.fennec.gestionconference;

import com.fennec.gestionconference.services.RequestResponseLoggingInterceptor;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableScheduling
public class GestionconferenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionconferenceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.setInterceptors( Collections.singletonList(new RequestResponseLoggingInterceptor()) );

        return restTemplate;
    }
}
