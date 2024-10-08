package study.querydsl.entity;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    // Comment(댓글)와 Board(게시글)는 다대일 관계
    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    public Comment() {}

    public Comment(String content, Board board) {
        this.content = content;
        this.board = board;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}

