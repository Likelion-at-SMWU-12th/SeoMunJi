package com.example.server.controller;

import com.example.server.dto.Tweet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class SlowServiceController {

    @GetMapping("/api/v1/slow")
    public ResponseEntity<List<Tweet>> slowResponse() {
        // Tweet 객체 생성 후 JSON 형식으로 반환
        List<Tweet> tweets = Arrays.asList(
                new Tweet("1", "Hello World", "Author1"),
                new Tweet("2", "Spring WebFlux", "Author2")
        );

        return ResponseEntity.ok(tweets);
    }
}