package ir.novinp.productionreport.services;

import ir.novinp.productionreport.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order save(Order order);

    Optional<Order> findById(Long id);

    List<Order> getAll();

    void delete(Long id);

}
