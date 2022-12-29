package ir.novinp.productionreport.mapServices.implementations;

import ir.novinp.productionreport.api.requestModel.LogRequest;
import ir.novinp.productionreport.api.responseModel.LogResponse;
import ir.novinp.productionreport.api.responseModel.OrderResponse;
import ir.novinp.productionreport.mapServices.OrderLogMapService;
import ir.novinp.productionreport.mapServices.OrderMapService;
import ir.novinp.productionreport.model.AppUser;
import ir.novinp.productionreport.model.Order;
import ir.novinp.productionreport.model.OrderLog;
import ir.novinp.productionreport.model.WindowOrderLog;

import ir.novinp.productionreport.model.status.Status;
import ir.novinp.productionreport.services.AppUserService;
import ir.novinp.productionreport.services.OrderLogService;
import ir.novinp.productionreport.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("windowLogMapService")
public class WindowLogMapServiceImp implements OrderLogMapService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapService orderMapService;

    @Autowired
    private OrderLogService windowLogService;

    @Autowired
    private AppUserService userService;

    @Override
    public OrderResponse continueTask(LogRequest request) throws Throwable {

        Order order = getOrderById(request.getOrderId());

        Optional<? extends OrderLog> optionalLog = getOrderLogByOrder(order);

        if (!optionalLog.isPresent()) {
            //next Step
            return startNextStep(order);
        } else {
            //end last step
            return endLastStep(order, optionalLog.get().getId());
        }
    }

    private OrderResponse startNextStep(Order order) {

        int winProductStatus = order.getWindowProductionStatus() + 1;

        order.setWindowProductionStatus(winProductStatus);
        order.setWindowProductionStepDone(false);


        if (order.getGlassProductionStatus() == Status.glassMovedOutStatusId &&
                winProductStatus == Status.winMovedOutStatusId) {
            order.setOutDate(LocalDateTime.now());
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = userService.findByUserName(authentication.getName());

        WindowOrderLog windowOrderLog = WindowOrderLog
                .builder()
                .appUser(appUser)
                .order(order)
                .description(null)
                .status(winProductStatus)
                .build();

        Order savedOrder = orderService.save(order);
        windowLogService.save(windowOrderLog);
        return orderMapService.mapToResponse(savedOrder);
    }

    private OrderResponse endLastStep(Order order, Long logId) throws Throwable {

        order.setWindowProductionStepDone(true);

        if (order.getGlassProductionStatus() == Status.glassCompleteStatusId &&
                order.getWindowProductionStatus() == Status.winCompleteStatusId) {
            order.setCompleteDate(LocalDateTime.now());
        }

        WindowOrderLog windowOrderLog = (WindowOrderLog) windowLogService.findById(logId).orElseThrow(RuntimeException::new);
        windowOrderLog.setCompleteDate(LocalDateTime.now());

        Order savedOrder = orderService.save(order);
        windowLogService.save(windowOrderLog);
        return orderMapService.mapToResponse(savedOrder);
    }

    @Override
    public List<LogResponse> loadAll(Long orderId) throws Exception {
        List<WindowOrderLog> list = windowLogService.loadAllByOrderId(orderId);

        return list.stream()
                .map(this::mapToWindowLogResponse)
                .collect(Collectors.toList());
    }

    private LogResponse mapToWindowLogResponse(WindowOrderLog log) {
        return LogResponse
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

    private Optional<WindowOrderLog> getOrderLogByOrder(Order order) {
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
