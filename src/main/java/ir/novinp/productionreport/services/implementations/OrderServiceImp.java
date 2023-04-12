package ir.novinp.productionreport.services.implementations;

import ir.novinp.productionreport.model.Order;
import ir.novinp.productionreport.model.repositories.OrderRepository;
import ir.novinp.productionreport.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public List<Order> getAll() {
        return repository.findByCompleteDateIsNullOrderByCreationDate();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }
}
