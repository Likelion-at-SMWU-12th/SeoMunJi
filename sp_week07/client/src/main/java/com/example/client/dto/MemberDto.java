package com.example.client.dto;

import java.lang.reflect.Member;

public class MemberDto {
    private String name;
    private String email;
    private String organization;


    // 기본 생성자
    public MemberDto() {}

    // 세 개의 필드 초기화 생성자
    public MemberDto(String name, String email, String organization){
        this.name=name;
        this.email=email;
        this.organization=organization;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getOrganization() { return organization; }
    public void setOrganization(String organization) { this.organization = organization; }
    @Override
    public String toString() {
        return "MemberDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }
}