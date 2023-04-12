package ir.novinp.productionreport.services.implementations;

import ir.novinp.productionreport.model.WindowOrderLog;
import ir.novinp.productionreport.model.repositories.WindowOrderLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("windowLogService")
public class WindowOrderLogServiceImp extends OrderLogServiceImp<WindowOrderLog> {

    @Autowired
    private WindowOrderLogRepository repository;

    @Override
    public WindowOrderLogRepository repository() {
        return repository;
    }
}
