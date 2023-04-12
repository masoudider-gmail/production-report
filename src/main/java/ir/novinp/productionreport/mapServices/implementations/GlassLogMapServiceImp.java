package ir.novinp.productionreport.mapServices.implementations;

import ir.novinp.productionreport.api.requestModel.LogRequest;
import ir.novinp.productionreport.api.responseModel.LogResponse;
import ir.novinp.productionreport.api.responseModel.OrderResponse;
import ir.novinp.productionreport.mapServices.OrderLogMapService;
import ir.novinp.productionreport.mapServices.OrderMapService;
import ir.novinp.productionreport.model.AppUser;
import ir.novinp.productionreport.model.GlassOrderLog;
import ir.novinp.productionreport.model.Order;
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

@Service("glassLogMapService")
public class GlassLogMapServiceImp implements OrderLogMapService {

    @Autowired
    private OrderLogService glassLogService;

    @Autowired
    private OrderMapService orderMapService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AppUserService userService;

    @Override
    public OrderResponse continueTask(LogRequest request) throws Throwable {

        Order order = getOrderById(request.getOrderId());

        Optional<GlassOrderLog> optionalLog = getGlassOrderLogByOrder(order);

        if (!optionalLog.isPresent()) {
            //nextStep
            return startNextStep(order);
        } else {
            //end last Step
            return endLastStep(order, optionalLog.get().getId());
        }
    }

    private OrderResponse startNextStep(Order order) {

        int glassProductStatus = order.getGlassProductionStatus() + 1;

        order.setGlassProductionStatus(glassProductStatus);
        order.setGlassProductionStepDone(false);



        if (glassProductStatus == Status.glassMovedOutStatusId &&
                order.getWindowProductionStatus() == Status.winMovedOutStatusId) {

            order.setOutDate(LocalDateTime.now());

        }


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = userService.findByUserName(authentication.getName());

        GlassOrderLog windowOrderLog = GlassOrderLog
                .builder()
                .appUser(appUser)
                .order(order)
                .description(null)
                .status(glassProductStatus)
                .build();


        Order savedOrder = orderService.save(order);
        glassLogService.save(windowOrderLog);
        return orderMapService.mapToResponse(savedOrder);
    }

    public OrderResponse endLastStep(Order order, Long logId) throws Throwable {

        order.setGlassProductionStepDone(true);

        if (order.getGlassProductionStatus() == Status.glassCompleteStatusId &&
                order.getWindowProductionStatus() == Status.winCompleteStatusId) {

            order.setCompleteDate(LocalDateTime.now());

        }

        GlassOrderLog glassOrderLog = (GlassOrderLog) glassLogService.findById(logId).orElseThrow(RuntimeException::new);
        glassOrderLog.setCompleteDate(LocalDateTime.now());

        Order savedOrder = orderService.save(order);
        glassLogService.save(glassOrderLog);
        return orderMapService.mapToResponse(savedOrder);

    }

    @Override
    public List<LogResponse> loadAll(Long orderId) throws Exception {
        List<GlassOrderLog> list = glassLogService
                .loadAllByOrderId(orderId);
        return list
                .stream()
                .map(this::mapToLogResponse)
                .collect(Collectors.toList());
    }

    private LogResponse mapToLogResponse(GlassOrderLog log) {
        return LogResponse
                .builder()
                .id(log.getId())
                .creationDate(log.getCreationDate())
                .completeDate(log.getCompleteDate())
                .appUser(log.getAppUser().getName())
                .status(Status.glassProduction.get(log.getStatus()))
                .description(log.getDescription())
                .orderId(log.getOrder().getOrderId())
                .build();
    }

    private Optional<GlassOrderLog> getGlassOrderLogByOrder(Order order) {

        return order
                .getGlassLogList()
                .stream()
                .filter(orderLog -> orderLog.getCompleteDate() == null)
                .findFirst();
    }

    private Order getOrderById(Long orderId) {
        return orderService.findById(orderId)
                .orElseThrow(RuntimeException::new);
    }
}
