package ir.novinp.productionreport.services;

import ir.novinp.productionreport.model.GlassOrderLog;

import java.util.List;
import java.util.Optional;

public interface GlassOrderLogService {


    GlassOrderLog save(GlassOrderLog orderLog);

    Optional<GlassOrderLog> findById(Long id);

    List<GlassOrderLog> loadAllByOrderId(Long orderId);

}
