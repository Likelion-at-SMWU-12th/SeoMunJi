package study.querydsl.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // User(작성자)와 Board(게시글)는 일대다 관계
    @OneToMany(mappedBy = "writer")
    private List<Board> boards = new ArrayList<>();

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void addBoard(Board board) {
        boards.add(board);
    }
}
