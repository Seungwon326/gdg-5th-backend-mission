package gdg.hongik.mission.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PurchaseResponseDto {
    private String name;
    private int count;
    private int price;
}
