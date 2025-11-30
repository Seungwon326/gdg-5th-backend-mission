package gdg.hongik.mission.service;

import gdg.hongik.mission.domain.Order;
import gdg.hongik.mission.domain.Product;
import gdg.hongik.mission.dto.order.PurchaseListRequestDto;
import gdg.hongik.mission.dto.order.PurchaseListResponseDto;
import gdg.hongik.mission.dto.order.PurchaseRequestDto;
import gdg.hongik.mission.dto.order.PurchaseResponseDto;
import gdg.hongik.mission.repository.OrderRepository;
import gdg.hongik.mission.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * 구매 주문 요청을 받아 실제 비즈니스 로직을 처리하는 서비스
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    /**
     * 구매 요청된 상품의 재고를 확인하고, 재고 감소 후 총 주문 금액 계산 및 주문을 저장합니다.
     * @param requestDto 사용자가 요청한 구매 상품
     * @return 구매 요청 상품의 구매 목록 정보(총합, 상품 정보)
     */
    @Transactional
    public PurchaseListResponseDto orders(PurchaseListRequestDto requestDto) {
        int totalPrice = 0;
        List<PurchaseResponseDto> purchaseList = new ArrayList<>(); // 구매 목록을 담을 리스트
        for (PurchaseRequestDto requestItem : requestDto.getItemsToPurchase()) {
            if (requestItem.getCount() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "주문 수량은 1개 이상이어야 합니다.");
            }
            
            Product product = productRepository.findByName(requestItem.getName())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 상품은 존재하지 않습니다."));

            if (product.getStock() < requestItem.getCount()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "재고가 부족합니다: " + product.getName());
            }

            product.removeStock(requestItem.getCount()); // 재고 감소

            int itemPrice = product.getPrice() * requestItem.getCount(); // 총합 계산
            totalPrice += itemPrice;

            Order order = new Order(
                    product.getName(),
                    requestItem.getCount(),
                    itemPrice
            );
            orderRepository.save(order);

            PurchaseResponseDto responseItem = new PurchaseResponseDto(
                    product.getName(),
                    requestItem.getCount(),
                    itemPrice
            );

            purchaseList.add(responseItem); // 구매 할 상품의 정보를 purchaseList에 추가
        }


        return new PurchaseListResponseDto(totalPrice, purchaseList);
    }
}