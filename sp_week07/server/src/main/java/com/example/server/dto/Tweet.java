package com.example.server.dto;

public class Tweet {
    private String id;
    private String content;
    private String author;

    // 기본 생성자
    public Tweet() {
    }

    // 모든 필드를 초기화하는 생성자
    public Tweet(String id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }


    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}