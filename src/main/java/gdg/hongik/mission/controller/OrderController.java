package gdg.hongik.mission.controller;

import gdg.hongik.mission.dto.PurchaseListRequestDto;
import gdg.hongik.mission.dto.PurchaseListResponseDto;
import gdg.hongik.mission.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 상품 구매 요청을 처리하는 컨트롤러
 */
@RestController // REST API를 처리하는 Controller임을 선언
@RequiredArgsConstructor // final 키워드가 붙은 필드(orderService)를 인자로 받는 생성자를 자동으로 만들어줌(생성자 주입)
public class OrderController {

    private final OrderService orderService; // 이 컨트롤러가 사용할 OrderService 객체를 담을 필드(멤버 변수)를 선언

    /**
     * 신규 구매 요청을 처리합니다.
     * @param requestDto 구매 할 상품 목록(이름, 가격)
     * @return 주문 처리 결과(총액 및 상품 정보)
     */
    @PostMapping("/orders")
    public PurchaseListResponseDto orders(@RequestBody PurchaseListRequestDto requestDto) { // 요청을 처리 할 함수부분
        return orderService.orders(requestDto);
    }
    // @RequestBody:클라이언트가 보낸 HTTP 요청의 본문(Body)에 담긴 JSON 데이터를 자바 객체로 자동 변환해서
    //              뒤에 오는 매개변수(requestDto)에 넣어달라고 Spring에게 요청하는 어노테이션
}