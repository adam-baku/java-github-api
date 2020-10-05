package com.adambaku.githubapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

@Configuration
public class HttpClientConfig
{
    @Autowired
    private Environment env;

    @Bean
    public HttpClient httpClient()
    {
        return HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(Long.parseLong(env.getProperty("http.connection.timeout", "10"))))
            .build();
    }

    @Bean
    public HttpRequest.Builder httpRequestBuilder()
    {
        return HttpRequest.newBuilder();
    }
}
