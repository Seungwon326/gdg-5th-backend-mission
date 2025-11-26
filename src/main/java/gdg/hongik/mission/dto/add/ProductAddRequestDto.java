package gdg.hongik.mission.dto.add;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductAddRequestDto {
    private String name;
    private int count;
}
