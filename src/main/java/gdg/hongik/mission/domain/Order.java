package gdg.hongik.mission.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String name;
    private int count;
    private int totalPrice;

    public Order(String name, int totalPrice, int count) {
        this.name = name;
        this.count = count;
        this.totalPrice = totalPrice;
    }
}
