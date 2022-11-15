package ir.novinp.productionreport.services;

import ir.novinp.productionreport.model.WindowOrderLog;

import java.util.List;
import java.util.Optional;

public interface WindowOrderLogService {

    WindowOrderLog save(WindowOrderLog orderLog);

    Optional<WindowOrderLog> findById(Long id);

    List<WindowOrderLog> loadAllByOrderId(Long orderId);
}
