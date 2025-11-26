package gdg.hongik.mission.service;

import gdg.hongik.mission.domain.Product;
import gdg.hongik.mission.dto.add.ProductAddRequestDto;
import gdg.hongik.mission.dto.add.ProductAddResponseDto;
import gdg.hongik.mission.dto.create.ProductCreateRequestDto;
import gdg.hongik.mission.dto.delete.ProductDeleteResponseDto;
import gdg.hongik.mission.dto.search.ProductSearchResponseDto;
import gdg.hongik.mission.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    @Transactional
    public ProductSearchResponseDto searchProducts(String name) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품은 존재하지 않습니다."));

        return new ProductSearchResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }

    /**
     * 새로운 상품을 등록합니다.
     * @param createDto 등록할 신규 상품의 정보(이름, 개수, 가격)
     */
    @Transactional
    public void createProducts(ProductCreateRequestDto createDto) {
        if (productRepository.existsByName(createDto.getName())) { // 똑같은 이름의 상품이 이미 존재하면(True) 조건문 실행
            throw new IllegalArgumentException("이미 존재하는 상품입니다."); // 예외 처리
        }

        Product product = new Product(
                createDto.getName(),
                createDto.getPrice(),
                createDto.getStock()
        );
        productRepository.save(product);
    }

    /**
     * 기존 상품의 개수를 추가합니다.
     * @param addDto 추가 할 상품의 이름과 개수
     * @return 재고 추가가 완료된 상품의 정보(이름, 개수)
     */
    @Transactional
    public ProductAddResponseDto addStock(ProductAddRequestDto addDto) {
        Product product = productRepository.findByName(addDto.getName()).
                orElseThrow(() -> new IllegalArgumentException("재고를 추가 할 상품이 존재하지 않습니다.")); // 추가 할 상품 가져오기

        product.addStock(addDto.getCount()); // 상품 개수 추가
        return new ProductAddResponseDto(product.getName(), product.getStock()); // 추가한 상품 정보 출력(이름, 개수)
    }

    /**
     * 입력으로 주어진 특정 상품을 삭제합니다.
     * @param name 삭제할 상품의 이름
     * @return 상품 삭제 후 현재 남아있는 물품의 정보(이름, 개수)
     */
    @Transactional
    public List<ProductDeleteResponseDto> deleteProducts(String name) {
        Product product = productRepository.findByName(name).
                orElseThrow(() -> new IllegalArgumentException("삭제할 상품이 존재하지 않습니다.")); // 삭제 할 상품 검색하기

        productRepository.delete(product); // 상품 삭제
        List<Product> allProducts = productRepository.findAll(); // 남아있는 모든 상품 조회

        List<ProductDeleteResponseDto> responseList = new ArrayList<>();
       for(Product p : allProducts) {
           ProductDeleteResponseDto dto = new ProductDeleteResponseDto(
                   p.getName(),
                   p.getStock()
           );
           responseList.add(dto);
       }

       return responseList;
    }

}

