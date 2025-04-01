package com.chatting.member.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .filter(logRequest())
                .filter(logResponse())
                .build();
    }

    ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(
                clientRequest -> {
                    log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
                    return Mono.just(clientRequest);
                }
        );
    }

    ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(
                clientResponse -> {
                    log.info("Response: {}", clientResponse.statusCode());
                    return Mono.just(clientResponse);
                }
        );
    }
}
