package study.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.querydsl.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 인기있는 상품 Top 10
    @Query("SELECT p FROM Product p ORDER BY p.popularity DESC")
    List<Product> findTop10ByPopularity();

    // 최근 등록된 상품 Top 10
    @Query("SELECT p FROM Product p ORDER BY p.id DESC")
    List<Product> findTop10ByRecent();
}
