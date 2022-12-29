package ir.novinp.productionreport.services.implementations;

import ir.novinp.productionreport.model.OrderLog;
import ir.novinp.productionreport.model.repositories.OrderLogRepository;
import ir.novinp.productionreport.services.OrderLogService;

import java.util.List;
import java.util.Optional;

public abstract class OrderLogServiceImp<LOG_TYPE extends OrderLog> implements OrderLogService<LOG_TYPE> {

    @Override
    public LOG_TYPE save(LOG_TYPE orderLog) {
        return (LOG_TYPE) repository().save(orderLog);
    }

    @Override
    public Optional<LOG_TYPE> findById(Long id) {
        return repository().findById(id);
    }

    @Override
    public List<LOG_TYPE> loadAllByOrderId(Long orderId) {
        return (List<LOG_TYPE>) repository().findByOrder_OrderId(orderId);
    }

    public abstract <LOG extends OrderLogRepository> LOG repository();
}
