package gdg.hongik.mission.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PurchaseRequestDto {
    private String name;
    private int count;
}
