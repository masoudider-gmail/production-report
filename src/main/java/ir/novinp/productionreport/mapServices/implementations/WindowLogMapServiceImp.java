package ir.novinp.productionreport.mapServices.implementations;

import ir.novinp.productionreport.api.requestModel.WindowLogRequest;
import ir.novinp.productionreport.api.responseModel.WindowLogResponse;
import ir.novinp.productionreport.mapServices.WindowLogMapService;
import ir.novinp.productionreport.model.WindowOrderLog;
import ir.novinp.productionreport.services.OrderService;
import ir.novinp.productionreport.services.WindowOrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WindowLogMapServiceImp implements WindowLogMapService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WindowOrderLogService logService;

    @Override
    public WindowLogResponse startNextStep(WindowLogRequest request) {

        int status = orderService
                .findById(request.getOrderId())
                .orElseThrow(RuntimeException::new)
                .getWindowProductionStatus();

        Optional<WindowOrderLog> byOrderIdAndStatus = logService.findByOrderIdAndStatus(request.getOrderId(), status);

        return null;
    }

    @Override
    public WindowLogResponse endLastStep(Long id, WindowLogRequest request) {
        return null;
    }
}
