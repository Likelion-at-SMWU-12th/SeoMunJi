package study.querydsl.entity;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    // Board(게시글)와 User(작성자)는 다대일 관계
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User writer;  // 이 부분이 User 엔티티의 boards 필드와 연결되는 속성입니다.

    public Board() {
    }

    public Board(String title, String content, User writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getWriter() {
        return writer;
    }

}
