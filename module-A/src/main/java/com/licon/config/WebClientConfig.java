package com.licon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

/**
 * @author Licon
 * @description: TODO
 * @date 2023/10/24 18:41
 */
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(){
        // 创建一个连接提供者，指定连接池的名字和最大连接数
        ConnectionProvider connectionProvider = ConnectionProvider.builder("ccb-webClient-pool")
                .maxConnections(100)
                .maxLifeTime(Duration.ofMinutes(60))
                .maxIdleTime(Duration.ofMinutes(10))
                .build();
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create(connectionProvider)))
                .build();
    }
}
