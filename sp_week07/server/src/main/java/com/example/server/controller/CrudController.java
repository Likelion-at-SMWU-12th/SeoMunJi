package com.example.server.controller;

import com.example.server.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/crud-api")
public class CrudController {

    // GET '/api/v1/crud-api'
    // 아무 파라미터가 없는 경우 "Judy" 라는 문자열 반환
    @GetMapping
    public String getName() {
        return "Judy";
    }

    // GET '/api/v1/crud-api/{variable}'
    // 경로에 포함된 변수 값을 받아서 그대로 그 값을 반환
    @GetMapping("/{variable}")
    public String getVariable(@PathVariable String variable) {
        return variable;
    }

    // GET '/api/v1/crud-api/param'
    // RequestParam 사용하는 경우 - 쿼리 파라미터로 전달된 이름을 문자열에 껴넣어서 반환
    @GetMapping("/param")
    public String getNameWithParam(@RequestParam String name) {
        return "Hello. " + name + "!";
    }

    // POST 'api/v1/crud-api'
    // 요청 파라미터와 요청 바디를 함께 받는 경우
    // 요청 본문에서 MemberDto 데이터를 받고, 쿼리 파라미터로 전달된 name, email, organization을 새 MemberDto 객체에 설정한 후 반환
    @PostMapping
    public ResponseEntity<MemberDto> getMember(
            @RequestBody MemberDto request,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization
    ) {
        MemberDto memberDto = new MemberDto();
        memberDto.setName(name);
        memberDto.setEmail(email);
        memberDto.setOrganization(organization);
        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

    @PostMapping("/add-header")
    public ResponseEntity<MemberDto> addHeader(@RequestHeader("my-header") String header,
                                               @RequestBody MemberDto memberDto) {
        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }
}