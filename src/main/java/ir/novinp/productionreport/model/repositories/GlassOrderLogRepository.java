package ir.novinp.productionreport.model.repositories;

import ir.novinp.productionreport.model.GlassOrderLog;

import java.util.List;

public interface GlassOrderLogRepository extends OrderLogRepository<GlassOrderLog, Long> {
    List<GlassOrderLog> findByOrder_OrderId(Long orderId);
}
