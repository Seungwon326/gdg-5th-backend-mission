package gdg.hongik.mission.controller;

import gdg.hongik.mission.dto.add.ProductAddRequestDto;
import gdg.hongik.mission.dto.add.ProductAddResponseDto;
import gdg.hongik.mission.dto.create.ProductCreateRequestDto;
import gdg.hongik.mission.dto.delete.ProductDeleteResponseDto;
import gdg.hongik.mission.dto.search.ProductSearchResponseDto;
import gdg.hongik.mission.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 상품을 관리하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 입력받은 상품의 이름으로 상품을 검색합니다.
     * @param name 조회할 상품의 이름
     * @return 조회한 상품의 정보
     */
    @GetMapping("/products")
    public ProductSearchResponseDto searchProducts(@RequestParam String name) {
        return productService.searchProducts(name);
    }

    /**
     * 새로운 삼품을 등록합니다.
     * @param createDto 상품 등록 시 필요한 정보들
     */
    @PostMapping("/products")
    public void createProducts(@RequestBody ProductCreateRequestDto createDto) {
        productService.createProducts(createDto);
    }

    /**
     * 기존의 상품의 개수를 추가합니다.
     * @param addDto 추가 할 상품의 이름과 개수
     * @return 재고 추가가 완료된 상품의 정보(이름, 개수)
     */
    @PatchMapping ("/products")
    public ProductAddResponseDto addStock(@RequestBody ProductAddRequestDto addDto) {
        return productService.addStock(addDto);
    }

    /**
     * 입력으로 주어진 특정 상품을 삭제합니다.
     * @param name 조회할 상품의 이름
     * @return 현재 남아있는 물품의 정보(이름, 개수)
     */
    @DeleteMapping ("/products")
    public List<ProductDeleteResponseDto> deleteProducts(@RequestParam String name) {
        return productService.deleteProducts(name);
    }

}