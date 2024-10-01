package com.example.client.controller;

import com.example.client.dto.MemberDto;
import com.example.client.dto.Tweet;
import com.example.client.service.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

import java.util.List;

@RestController
@RequestMapping("/rest-template")
public class RestTemplateController {
    private static final Logger log = LoggerFactory.getLogger(WebClientController.class);

    private final RestTemplateService restTemplateService;

    public RestTemplateController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping
    public String getName() {
        return restTemplateService.getName();
    }

    @GetMapping("/path-variable")
    public String getNameWithPathVariable() {
        return restTemplateService.getNameWithPathVariable();
    }

    @GetMapping("/parameter")
    public String getNameWithParameter() {
        return restTemplateService.getNameWithParameter();
    }

    @PostMapping
    public ResponseEntity<MemberDto> postDto() {
        return restTemplateService.postWithParamAndBody();
    }

    @PostMapping("/header")
    public ResponseEntity<MemberDto> postWithHeader() {
        return restTemplateService.postWithHeader();
    }

    @GetMapping("/tweets-blocking")
    public List<Tweet> getTweetsBlocking() {
        log.info("Starting BLOCKING Controller!");
        final String uri = "http://localhost:9090/api/v1/slow";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Tweet>> response = restTemplate.exchange(
                uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Tweet>>(){});

        List<Tweet> result = response.getBody();
        result.forEach(tweet -> log.info(tweet.toString()));
        log.info("Exiting BLOCKING Controller!");
        return result;
    }
}