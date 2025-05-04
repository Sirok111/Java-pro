package sirok15.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sirok14.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

