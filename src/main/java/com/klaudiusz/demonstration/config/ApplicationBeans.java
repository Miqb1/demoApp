package com.klaudiusz.demonstration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class ApplicationBeans {
    @Bean
    ObjectMapper createObjectMapper(){
        return new ObjectMapper();
    }
    @Bean
    HttpClient client(){
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }
}
