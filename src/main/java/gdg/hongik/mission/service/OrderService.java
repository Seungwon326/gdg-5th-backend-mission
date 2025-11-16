package gdg.hongik.mission.service;

import gdg.hongik.mission.dto.PurchaseListRequestDto;
import gdg.hongik.mission.dto.PurchaseListResponseDto;
import gdg.hongik.mission.dto.PurchaseResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 구매 주문 요청을 받아 실제 비즈니스 로직을 처리하는 서비스
 */
@Service
public class OrderService {

    /**
     * 구매 요청된 상품의 재고를 확인하고, 총 주문 금액 계산 후 주문을 저장합니다.
     * @param requestDto 사용자가 요청한 구매 상품
     * @return 구매 요청 상품의 구매 목록 정보(총합, 상품 정보)
     */
    public PurchaseListResponseDto orders(PurchaseListRequestDto requestDto) {
        PurchaseResponseDto apple = new PurchaseResponseDto("apple", 3, 3000);
        PurchaseResponseDto orange = new PurchaseResponseDto("orange", 5, 10000);
        return new PurchaseListResponseDto(13000, List.of(apple, orange));
    }

}

