package ir.novinp.productionreport.services;


import ir.novinp.productionreport.model.OrderLog;

import java.util.List;
import java.util.Optional;

public interface OrderLogService<LOG_TYPE extends OrderLog> {
    LOG_TYPE save(LOG_TYPE orderLog);

    Optional<LOG_TYPE> findById(Long id);

    List<LOG_TYPE> loadAllByOrderId(Long orderId);
}
