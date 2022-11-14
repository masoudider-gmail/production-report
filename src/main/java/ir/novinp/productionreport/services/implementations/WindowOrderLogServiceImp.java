package ir.novinp.productionreport.services.implementations;

import ir.novinp.productionreport.model.WindowOrderLog;
import ir.novinp.productionreport.model.repositories.WindowOrderLogRepository;
import ir.novinp.productionreport.services.WindowOrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class WindowOrderLogServiceImp implements WindowOrderLogService {

    @Autowired
    private WindowOrderLogRepository repository;

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public WindowOrderLog save(WindowOrderLog orderLog) {
        return repository.save(orderLog);
    }

}
