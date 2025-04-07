package com.leonardo.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// isso e configuracao do rest, lembrando que pode-se usar tanto o feing
// como tambem o rest, os dois funcionam para chamar outro microservice
@Configuration
public class RestTemplateConfig {


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
