package gdg.hongik.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductAddRequestDto {
    private String name;
    private int count;
}
