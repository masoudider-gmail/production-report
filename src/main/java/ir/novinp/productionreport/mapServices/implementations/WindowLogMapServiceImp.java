package ir.novinp.productionreport.mapServices.implementations;

import ir.novinp.productionreport.api.requestModel.WindowLogRequest;
import ir.novinp.productionreport.api.responseModel.WindowLogResponse;
import ir.novinp.productionreport.mapServices.WindowLogMapService;
import ir.novinp.productionreport.model.AppUser;
import ir.novinp.productionreport.model.Order;
import ir.novinp.productionreport.model.WindowOrderLog;
import ir.novinp.productionreport.model.status.Status;
import ir.novinp.productionreport.services.AppUserService;
import ir.novinp.productionreport.services.OrderService;
import ir.novinp.productionreport.services.WindowOrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class WindowLogMapServiceImp implements WindowLogMapService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WindowOrderLogService logService;

    @Autowired
    private AppUserService userService;

    @Override
    public WindowLogResponse startNextStep(WindowLogRequest request) throws Exception {

        Order order = getOrderById(request.getOrderId());

        Optional<WindowOrderLog> optionalLog = getWindowOrderLogByOrder(order);

        if (optionalLog.isPresent())
            throw new Exception("Last Step Is Not Done Yet, You Can Not Start The Next Step!");


        int winProductStatus = order.getWindowProductionStatus() + 1;

        order.setWindowProductionStatus(winProductStatus);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = userService.findByUserName(authentication.getName());

        WindowOrderLog windowOrderLog = WindowOrderLog
                .builder()
                .appUser(appUser)
                .order(order)
                .description(request.getDescription())
                .status(winProductStatus)
                .build();

        orderService.save(order);
        return mapToWindowLogResponse(logService.save(windowOrderLog));
    }

    @Override
    public WindowLogResponse endLastStep(WindowLogRequest request) throws Exception {
        Order order = getOrderById(request.getOrderId());

        Optional<WindowOrderLog> optionalLog = getWindowOrderLogByOrder(order);

        if (!optionalLog.isPresent()) {
            throw new Exception("Last Step Is Done, You Must Start The Next Step");
        }else {

        WindowOrderLog windowOrderLog = logService.findById(optionalLog.get().getId()).orElseThrow(RuntimeException::new);
        windowOrderLog.setCompleteDate(LocalDateTime.now());

        return mapToWindowLogResponse(logService.save(windowOrderLog));
        }
    }

    private WindowLogResponse mapToWindowLogResponse(WindowOrderLog log) {
        return WindowLogResponse
                .builder()
                .id(log.getId())
                .creationDate(log.getCreationDate())
                .completeDate(log.getCompleteDate())
                .appUser(log.getAppUser().getName())
                .status(Status.windowProduction.get(log.getStatus()))
                .description(log.getDescription())
                .orderId(log.getOrder().getOrderId())
                .build();
    }

    private Optional<WindowOrderLog> getWindowOrderLogByOrder(Order order) {

        return order
                .getWinLogList()
                .stream()
                .filter(orderLog -> orderLog.getCompleteDate() == null)
                .findFirst();
    }

    private Order getOrderById(Long orderId) {
        return orderService.findById(orderId)
                .orElseThrow(RuntimeException::new);
    }


}
