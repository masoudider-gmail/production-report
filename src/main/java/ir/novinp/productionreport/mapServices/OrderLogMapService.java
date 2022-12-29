package ir.novinp.productionreport.mapServices;

import ir.novinp.productionreport.api.requestModel.LogRequest;
import ir.novinp.productionreport.api.responseModel.LogResponse;
import ir.novinp.productionreport.api.responseModel.OrderResponse;

import java.util.List;

public interface OrderLogMapService {

    OrderResponse continueTask(LogRequest request) throws Throwable;

    List<LogResponse> loadAll(Long orderId) throws Exception;

}
