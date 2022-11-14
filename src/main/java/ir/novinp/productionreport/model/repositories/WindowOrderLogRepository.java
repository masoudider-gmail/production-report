package ir.novinp.productionreport.model.repositories;

import ir.novinp.productionreport.model.WindowOrderLog;

import java.util.Optional;


public interface WindowOrderLogRepository extends OrderLogRepository<WindowOrderLog, Long> {

    Optional<WindowOrderLog> findByOrderOrderIdAndStatus(Long id, Integer status);
}
