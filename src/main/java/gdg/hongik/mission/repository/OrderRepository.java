package gdg.hongik.mission.repository;

import gdg.hongik.mission.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
