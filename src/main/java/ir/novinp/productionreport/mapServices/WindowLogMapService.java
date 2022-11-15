package ir.novinp.productionreport.mapServices;

import ir.novinp.productionreport.api.requestModel.WindowLogRequest;
import ir.novinp.productionreport.api.responseModel.WindowLogResponse;

public interface WindowLogMapService {

    WindowLogResponse startNextStep(WindowLogRequest request) throws Exception;

    WindowLogResponse endLastStep( WindowLogRequest request) throws Exception;

}
