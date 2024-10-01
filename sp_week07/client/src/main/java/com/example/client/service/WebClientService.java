package com.example.client.service;

import com.example.client.dto.MemberDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {

    // 비동기 GET 요청 - Path Variable 사용
    public Mono<String> getNameWithPathVariable() {
        WebClient webClient = WebClient.create("http://localhost:9090"); // WebClient 인스턴스 생성
        return webClient.get()
                .uri("/api/v1/crud-api/{name}", "lion")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }

    // 비동기 POST 요청 - Body와 Query Parameter 함께 사용
    public Mono<MemberDto> postWithParamAndBody() {
        WebClient webClient = WebClient.create("http://localhost:9090"); // WebClient 인스턴스 생성
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/crud-api")
                        .queryParam("name", "lion")
                        .queryParam("email", "lion@gmail.com")
                        .queryParam("organization", "likelion")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new MemberDto("lion", "lion@gmail.com", "likelion")), MemberDto.class)
                .retrieve()
                .bodyToMono(MemberDto.class);
    }

    // 비동기 POST 요청 - Header 추가
    public Mono<MemberDto> postWithHeader() {
        WebClient webClient = WebClient.create("http://localhost:9090"); // WebClient 인스턴스 생성
        return webClient.post()
                .uri("/api/v1/crud-api/add-header")
                .header("my-header", "WebClientheader")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new MemberDto("lion", "likelion@gmail.com", "likelion")), MemberDto.class)
                .retrieve()
                .bodyToMono(MemberDto.class);
    }
}