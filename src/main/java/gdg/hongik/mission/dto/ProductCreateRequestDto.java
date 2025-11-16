package gdg.hongik.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductCreateRequestDto {
    private String name;
    private int count;
    private int price;
}
