package com.example.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestConfiguration {

    @Value("${mock_api.username:a}")
    private String username;

    @Value("${mock_api.password:b}")
    private String password;

    @Bean
    @Qualifier("mockApiRest")
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        RestTemplate restTemplate = restTemplateBuilder
                .basicAuthentication(username, password)
                .build();

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(List.of(MediaType.ALL));
        restTemplate.setMessageConverters(List.of(converter));

        return restTemplate;
    }

}
