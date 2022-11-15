package ir.novinp.productionreport.mapServices;

import ir.novinp.productionreport.api.requestModel.OrderRequest;
import ir.novinp.productionreport.api.responseModel.OrderResponse;

import java.util.List;

public interface OrderMapService {

    List<OrderResponse> getAll();

    OrderResponse save(OrderRequest order);

    OrderResponse updateById(Long orderId, OrderRequest order) throws Exception;

    void deleteById(Long orderId);

}
