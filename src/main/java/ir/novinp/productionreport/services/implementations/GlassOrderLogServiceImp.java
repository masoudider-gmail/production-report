package ir.novinp.productionreport.services.implementations;

import ir.novinp.productionreport.model.GlassOrderLog;
import ir.novinp.productionreport.model.repositories.GlassOrderLogRepository;
import ir.novinp.productionreport.services.GlassOrderLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GlassOrderLogServiceImp implements GlassOrderLogService {

    @Autowired
    private GlassOrderLogRepository repository;

    @Override
    public GlassOrderLog save(GlassOrderLog orderLog) {
        return repository.save(orderLog);
    }

    @Override
    public Optional<GlassOrderLog> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<GlassOrderLog> loadAllByOrderId(Long orderId) {
        return repository.findByOrder_OrderId(orderId);
    }
}
