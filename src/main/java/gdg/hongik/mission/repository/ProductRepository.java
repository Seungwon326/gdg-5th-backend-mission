package gdg.hongik.mission.repository;

import gdg.hongik.mission.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>{ // 기본적인 CRUD 기능을 자동으로 사용
    /**
     * 상품 이름으로 상품을 조회합니다
     * @param name 조회 할 상품 이름
     * @return 조회된 상품 리스트
     */
    Optional<Product> findByName(String name); // 상품 이름으로 상품 검색

    boolean existsByName(String name); // 똑같은 이름의 상품 있으면 True 반환
}
