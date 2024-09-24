package study.querydsl.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.querydsl.entity.Board;
import study.querydsl.entity.User;
import study.querydsl.entity.Comment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void saveAndFindBoardWithCommentsTest() {
        // 작성자 생성
        User user = new User("지");
        userRepository.save(user);

        // 게시글 생성 및 저장
        Board board1 = new Board("제목 1", "내용 1", user);
        Board board2 = new Board("제목 2", "내용 2", user);
        boardRepository.save(board1);
        boardRepository.save(board2);

        // 댓글 생성 및 저장
        Comment comment1 = new Comment("댓글 추가", board1);
        Comment comment2 = new Comment("멋사 스프링 5주차 과제중", board1);
        Comment comment3 = new Comment("잘 실행되고 있는거니?! 앙?!", board2);
        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);

        // 저장된 게시글 조회
        List<Board> boards = boardRepository.findAll();
        assertNotNull(boards);
        assertFalse(boards.isEmpty());

        // 출력: 조회한 게시글 정보 및 댓글 정보 출력
        boards.forEach(board -> {
            System.out.println("제목: " + board.getTitle() + " - 작성자: " + board.getWriter().getName());

            // 게시글에 달린 댓글 출력
            List<Comment> comments = commentRepository.findByBoard(board);
            comments.forEach(comment ->
                    System.out.println("   댓글: " + comment.getContent()));
        });
    }
}


