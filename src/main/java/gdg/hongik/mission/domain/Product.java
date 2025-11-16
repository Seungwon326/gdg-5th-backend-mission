package gdg.hongik.mission.domain;


import gdg.hongik.mission.dto.ProductCreateRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // 상품 이름
    private int price; // 가격
    private int stock; // 재고

    public Product(String name, int stock, int price) { //  생성자
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public void addStock(int count) {
        if(count <= 0) {
            throw new IllegalArgumentException("추가할 재고 수량은 0보다 커야 합니다.");
        }
        this.stock += count;
    }
}
