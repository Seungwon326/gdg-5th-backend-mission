package gdg.hongik.mission.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class PurchaseListResponseDto {
    private int totalPrice;
    private List<PurchaseResponseDto> items;
}