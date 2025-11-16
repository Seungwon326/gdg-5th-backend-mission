package gdg.hongik.mission.service;

import gdg.hongik.mission.domain.Product;
import gdg.hongik.mission.dto.*;
import gdg.hongik.mission.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 상품 관련 요청을 받아 해당 요청의 실제 비즈니스 로직을 처리하는 서비스
 */
@Service
@RequiredArgsConstructor // ProductRepository 주입
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * 사용자가 요청한 상품을 조회합니다.
     * @param name 조회 상품의 이름
     * @return 조회한 상품의 정보
     */
    public List<ProductSearchResponseDto> searchProducts(String name) {
        Optional<Product> productOptional = productRepository.findByName(name);
        Product product = productOptional.get();

        ProductSearchResponseDto dto = new ProductSearchResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
        return List.of(dto);
    }

    /**
     * 새로운 상품을 등록합니다.
     * @param createDto 등록할 신규 상품의 정보(이름, 개수, 가격)
     */
    public void createProducts(ProductCreateRequestDto createDto) {
        Product product = new Product(
                createDto.getName(),
                createDto.getCount(),
                createDto.getPrice()
        );
        productRepository.save(product);
    }

    /**
     * 기존 상품의 개수를 추가합니다.
     * @param addDto 추가 할 상품의 이름과 개수
     * @return 재고 추가가 완료된 상품의 정보(이름, 개수)
     */
    public ProductAddResponseDto addStock(ProductAddRequestDto addDto) {
        Product product = productRepository.findByName(addDto.getName()).get(); // 추가 할 상품 가져오기

        product.addStock(addDto.getCount()); // 상품 개수 추가
        return new ProductAddResponseDto(product.getName(), product.getStock()); // 추가한 상품 정보 출력(이름, 개수)
    }

    /**
     * 입력으로 주어진 특정 상품을 삭제합니다.
     * @param deleteDto 삭제 할 상품
     * @return 상품 삭제 후 현재 남아있는 물품의 정보(이름, 개수)
     */
    public ProductDeleteResponseDto deleteProducts(ProductDeleteRequestDto deleteDto) {
        return new ProductDeleteResponseDto("orange", 20); // 남아있는 물품 출력(수정 필요)
    }

}

