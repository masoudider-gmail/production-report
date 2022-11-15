package ir.novinp.productionreport.mapServices;

import ir.novinp.productionreport.api.requestModel.LogRequest;
import ir.novinp.productionreport.api.responseModel.LogResponse;

import java.util.List;

public interface WindowLogMapService {

    LogResponse startNextStep(LogRequest request) throws Exception;

    LogResponse endLastStep(LogRequest request) throws Exception;

    List<LogResponse> loadAll(Long orderId) throws Exception;

}
