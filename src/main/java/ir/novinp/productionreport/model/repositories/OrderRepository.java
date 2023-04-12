package ir.novinp.productionreport.model.repositories;

import ir.novinp.productionreport.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCompleteDateIsNullOrderByCreationDate();
}
