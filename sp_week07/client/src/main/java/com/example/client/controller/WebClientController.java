package com.example.client.controller;

import com.example.client.dto.MemberDto;
import com.example.client.dto.Tweet;
import com.example.client.service.WebClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/web-controller")
public class WebClientController {
    private static final Logger log = LoggerFactory.getLogger(WebClientController.class);

    @Autowired
    private WebClientService webClientService;

    @GetMapping("/path-variable")
    public Mono<String> getPathVariable() {
        return webClientService.getNameWithPathVariable();
    }

    @GetMapping("/parameter")
    public Mono<MemberDto> getParameter() {
        return webClientService.postWithParamAndBody();
    }

    @GetMapping("/header")
    public Mono<MemberDto> getHeader() {
        return webClientService.postWithHeader();
    }


    @GetMapping(value = "/tweets-non-blocking", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tweet> getTweetsNonBlocking() {
        log.info("Starting NON-BLOCKING Controller!");
        Flux<Tweet> tweetFlux = WebClient.create("http://localhost:9090")
                .get()
                .uri("/api/v1/slow")
                .retrieve()
                .bodyToFlux(Tweet.class);

        tweetFlux.subscribe(tweet -> log.info(tweet.toString()));
        log.info("Exiting NON-BLOCKING Controller!");
        return tweetFlux;
    }
}