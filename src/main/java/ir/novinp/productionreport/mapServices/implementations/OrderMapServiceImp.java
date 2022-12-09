package ir.novinp.productionreport.mapServices.implementations;

import ir.novinp.productionreport.api.requestModel.OrderRequest;
import ir.novinp.productionreport.api.responseModel.OrderResponse;
import ir.novinp.productionreport.mapServices.OrderMapService;
import ir.novinp.productionreport.model.AppUser;
import ir.novinp.productionreport.model.Order;
import ir.novinp.productionreport.model.status.Status;
import ir.novinp.productionreport.services.AppUserService;
import ir.novinp.productionreport.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapServiceImp implements OrderMapService {

    @Autowired
    private OrderService service;

    @Autowired
    private AppUserService userService;

    @Override
    public List<OrderResponse> getAll() {
        return service
                .getAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public OrderResponse save(OrderRequest order) {
        return mapToResponse(service.save(mapToOrder(order)));
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public OrderResponse updateById(Long orderId, OrderRequest request) throws Exception {
        Order order = service.findById(orderId).orElseThrow(()->new Exception("Order Id Is Not Valid!"));
        order.setName(request.getName());
        order.setWindowCount(request.getWindowCount());
        order.setWindowMeter(request.getWindowMeter());
        order.setGlassCount(request.getGlassCount());
        order.setGlassMeter(request.getGlassMeter());

        return mapToResponse(service.save(order));
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public void deleteById(Long orderId) {
        service.delete(orderId);
    }

    public OrderResponse mapToResponse(Order order) {
        return OrderResponse
                .builder()
                .orderId(order.getOrderId())
                .name(order.getName())
                .picture(order.getPicture())
                .windowCount(order.getWindowCount())
                .windowMeter(order.getWindowMeter())
                .glassCount(order.getGlassCount())
                .glassMeter(order.getGlassMeter())
                .windowProductionStatus(Status.windowProduction.get(order.getWindowProductionStatus()))
                .windowProductionStepDone(order.isWindowProductionStepDone())
                .glassProductionStatus(Status.glassProduction.get(order.getGlassProductionStatus()))
                .glassProductionStepDone(order.isGlassProductionStepDone())
                .creationDate(order.getCreationDate())
                .lastModificationDate(order.getLastModificationDate())
                .completeDate(order.getCompleteDate())
                .outDate(order.getOutDate())
                .appUser(order.getAppUser().getName()+" "+order.getAppUser().getLastName())
                .build();
    }

    public Order mapToOrder(OrderRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = userService.findByUserName(authentication.getName());

        Order order = Order
                .builder()
                .name(request.getName())
                .windowCount(request.getWindowCount())
                .windowMeter(request.getWindowMeter())
                .glassCount(request.getGlassCount())
                .glassMeter(request.getGlassMeter())
                .windowProductionStatus(1)
                .glassProductionStatus(1)
                .appUser(appUser)
                .build();

        return order;
    }
}
