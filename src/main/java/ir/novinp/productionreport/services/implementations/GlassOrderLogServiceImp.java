package ir.novinp.productionreport.services.implementations;

import ir.novinp.productionreport.model.GlassOrderLog;
import ir.novinp.productionreport.model.repositories.GlassOrderLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("glassOrderLog")
public class GlassOrderLogServiceImp extends OrderLogServiceImp<GlassOrderLog> {

    @Autowired
    private GlassOrderLogRepository repository;

    @Override
    public GlassOrderLogRepository repository() {
        return repository;
    }
}
