package study.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 추가적으로 커스텀 메서드가 필요하다면 여기에 작성
}

