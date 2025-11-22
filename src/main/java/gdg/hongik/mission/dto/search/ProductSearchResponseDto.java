package gdg.hongik.mission.dto.search;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductSearchResponseDto {
    private Long id;
    private String name;
    private int price;
    private int stock;
}
