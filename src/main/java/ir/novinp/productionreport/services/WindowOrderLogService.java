package ir.novinp.productionreport.services;

import ir.novinp.productionreport.model.WindowOrderLog;

import java.util.Optional;

public interface WindowOrderLogService {

    WindowOrderLog save(WindowOrderLog orderLog);

    Optional<WindowOrderLog> findByOrderIdAndStatus(Long orderId, Integer status);

}
