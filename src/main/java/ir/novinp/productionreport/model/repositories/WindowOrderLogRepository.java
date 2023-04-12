package ir.novinp.productionreport.model.repositories;

import ir.novinp.productionreport.model.WindowOrderLog;

import java.util.List;

public interface WindowOrderLogRepository extends OrderLogRepository<WindowOrderLog, Long> {

    @Override
    List<WindowOrderLog> findByOrder_OrderId(Long orderId);

}
