package gdg.hongik.mission.dto.create;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductCreateRequestDto {
    private String name;
    private int price;
    private int stock;
}
