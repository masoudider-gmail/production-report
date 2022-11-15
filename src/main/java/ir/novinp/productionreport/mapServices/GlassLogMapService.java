package ir.novinp.productionreport.mapServices;

import ir.novinp.productionreport.api.requestModel.LogRequest;
import ir.novinp.productionreport.api.responseModel.LogResponse;

public interface GlassLogMapService {

    LogResponse startNextStep(LogRequest request) throws Exception;

    LogResponse endLastStep(LogRequest request) throws Exception;
}
