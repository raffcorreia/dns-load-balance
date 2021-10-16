package com.dnsloadbalance.interactionapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfiguration {

    @Value("${http-factory.connect-timeout}")
    private int connectTimeout;

    @Value("${http-factory.connection-request-timeout}")
    private int connectionRequestTimeout;


    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        clientHttpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);

        return new RestTemplateBuilder()
                .requestFactory(() -> clientHttpRequestFactory)
                .build();
    }

    @Bean(name = "restTemplateSimpleConnection")
    public RestTemplate restTemplateSimpleConnection() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);

        return new RestTemplateBuilder()
                .requestFactory(() -> clientHttpRequestFactory)
                .build();
    }
}
