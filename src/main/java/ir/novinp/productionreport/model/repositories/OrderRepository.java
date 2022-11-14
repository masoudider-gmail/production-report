package ir.novinp.productionreport.model.repositories;

import ir.novinp.productionreport.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
