package com.example.client.service;


import com.example.client.dto.MemberDto;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    // getName() 메서드
    // http://localhost:9090/api/v1/crud-api에 GET 요청을 보내고, 서버에서 받은 응답의 본문을 반환함
    // GET 요청을 보내고, 응답의 본문을 가져오기 위해 RestTemplate 메서드인 getForEntity()가 사용되었음
    public String getName() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/v1/crud-api")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return responseEntity.getBody();
    }

    // getNameWithPathVariable() 메서드
    // 경로 변수({name})가 포함된 http://localhost:9090/api/v1/crud-api/wonhee 에 GET 요청을 보내고, 서버에서 받은 응답의 본문을 반환함
    // 경로 변수를 포함한 GET 요청을 보내기 위해 RestTemplate 메서드인 getForEntity()가 사용되었음
    public String getNameWithPathVariable() {
        URI uri = UriComponentsBuilder // 여러 파라미터를 연결하여 URI 형식으로 만드는 기능을 수행함
                .fromUriString("http://localhost:9090") // 호출할 API URL 입력
                .path("/api/v1/crud-api/{name}") // 세부 경로 입력 - 여기에 사용된 변수의 값은 expand에서 지정
                .encode()   // 인코딩 문자셋 설정 (디폴트 값은 UTF-8)
                .build()
                .expand("wonhee") // expand() 안에는 {변수}에 넣을 값을 차례로 입력한다.
                // 복수의 값을 넣어야 할 경우 ,를 추가하여 구분한다!
                .toUri(); // URI 타입으로 리턴 -> uri에 저장 -> 외부 API 요청하는 데에 사용됨

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class); // getForEntity(URI, 응답받는 타입)
        return responseEntity.getBody();
    }

    // getNameWithParameter() 메서드
    // http://localhost:9090/api/v1/crud-api/param?name=Flature에 GET 요청을 보내고, 응답 본문을 반환
    // 쿼리 파라미터를 포함한 GET 요청을 보내기 위해 RestTemplate 메서드인 getForEntity()가 사용되었음
    public String getNameWithParameter() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/v1/crud-api/param")
                .queryParam("name", "wonhee") // (키, 값) 형식으로 파라미터를 추가할 수 있는 메서드
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return responseEntity.getBody();
    }

    // postWithParamAndBody() 메서드
    // http://localhost:9090/api/v1/crud-api로 이름, 이메일, 조직 정보를 쿼리 파라미터로 POST 요청을 보냄
    // 본문에 MemberDto 데이터를 담아 POST 요청을 보내기 위해 RestTemplate 메서드인 postForEntity()가 사용되었음
    public ResponseEntity<MemberDto> postWithParamAndBody() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/v1/crud-api")
                .queryParam("name", "wonhee")
                .queryParam("email", "wonhee@smwu.ac.kr")
                .queryParam("organization", "likelion")
                .encode()
                .build()
                .toUri();

        MemberDto memberDto = new MemberDto();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDto> responseEntity = restTemplate.postForEntity(
                uri, memberDto, MemberDto.class
        );

        return responseEntity;
    }

    // postWithHeader() 메서드
    // http://localhost:9090/api/v1/crud-api/add-header로 요청 헤더(my-header)와 함께 MemberDto 객체를 요청 본문으로 하는 POST 요청을 보냄
    public ResponseEntity<MemberDto> postWithHeader(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/v1/crud-api/add-header")
                .encode()
                .build()
                .toUri();

        MemberDto memberDto = new MemberDto();
        memberDto.setName("wonhee");
        memberDto.setEmail("wonhee@smwu.ac.kr");
        memberDto.setOrganization("likelion");

        RequestEntity<MemberDto> requestEntity = RequestEntity
                .post(uri)
                .header("my-header", "headertext") // API 토큰 키 같은 걸 여기에 넣어주면 됨
                .body(memberDto);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDto> responseEntity = restTemplate.exchange(
                requestEntity, MemberDto.class
        );

        return responseEntity;
    }
}

