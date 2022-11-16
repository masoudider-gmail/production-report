package ir.novinp.productionreport.mapServices.implementations;

import ir.novinp.productionreport.api.requestModel.LogRequest;
import ir.novinp.productionreport.api.responseModel.LogResponse;
import ir.novinp.productionreport.mapServices.GlassLogMapService;
import ir.novinp.productionreport.model.AppUser;
import ir.novinp.productionreport.model.GlassOrderLog;
import ir.novinp.productionreport.model.Order;
import ir.novinp.productionreport.model.status.Status;
import ir.novinp.productionreport.services.AppUserService;
import ir.novinp.productionreport.services.GlassOrderLogService;
import ir.novinp.productionreport.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GlassLogMapServiceImp implements GlassLogMapService {

    @Autowired
    private GlassOrderLogService logService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AppUserService userService;

    @Override
    public LogResponse startNextStep(LogRequest request) throws Exception {

        Order order = getOrderById(request.getOrderId());

        Optional<GlassOrderLog> optionalLog = getGlassOrderLogByOrder(order);

        if (optionalLog.isPresent())
            throw new Exception("Last Step Is Not Done Yet, You Can Not Start The Next Step!");

        int glassProductStatus = order.getGlassProductionStatus() + 1;

        order.setGlassProductionStatus(glassProductStatus);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = userService.findByUserName(authentication.getName());

        GlassOrderLog windowOrderLog = GlassOrderLog
                .builder()
                .appUser(appUser)
                .order(order)
                .description(request.getDescription())
                .status(glassProductStatus)
                .build();

        orderService.save(order);
        return mapToLogResponse(logService.save(windowOrderLog));
    }

    @Override
    public LogResponse endLastStep(LogRequest request) throws Exception {
        Order order = getOrderById(request.getOrderId());

        Optional<GlassOrderLog> optionalLog = getGlassOrderLogByOrder(order);

        if (!optionalLog.isPresent()) {
            throw new Exception("Last Step Is Done, You Must Start The Next Step");
        } else {

            GlassOrderLog glassOrderLog = logService.findById(optionalLog.get().getId()).orElseThrow(RuntimeException::new);
            glassOrderLog.setCompleteDate(LocalDateTime.now());

            return mapToLogResponse(logService.save(glassOrderLog));
        }
    }

    @Override
    public List<LogResponse> loadAll(Long orderId) throws Exception {
        return logService
                .loadAllByOrderId(orderId)
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
