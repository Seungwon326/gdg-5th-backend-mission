package gdg.hongik.mission.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Id 자동 생성
    private Long id;
    @Column(unique = true)
    private String name; // 상품 이름
    private int price; // 가격
    private int stock; // 재고

    public Product(String name, int price, int stock) { // 생성자
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void addStock(int count) {
        if(count <= 0) {
            throw new IllegalArgumentException("추가할 재고 수량은 0보다 커야 합니다.");
        }
        this.stock += count;
    }

    /**
     * 구매한 개수 만큼 재고를 줄임
     * @param count 구매 개수
     */
    public void removeStock(int count) {
        int restStock = this.stock - count;
        if(restStock <= 0) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        this.stock = restStock;
    }
}
