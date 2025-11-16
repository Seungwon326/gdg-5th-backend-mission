package gdg.hongik.mission.dto;

import lombok.Getter;

@Getter
public class ProductAddResponseDto {
    private String name;
    private int stock;

    public ProductAddResponseDto(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }
}
