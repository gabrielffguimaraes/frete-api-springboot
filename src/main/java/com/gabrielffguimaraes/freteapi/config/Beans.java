package com.gabrielffguimaraes.freteapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Beans {
    @Bean
    public ModelMapper modelMapperInit() {
        return new ModelMapper();
    };
    @Bean
    public RestTemplate restTemplateInit() {
        return new RestTemplate();
    };
}
