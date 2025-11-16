package gdg.hongik.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDeleteResponseDto {
    private String name;
    private int stock;
}
