package ir.novinp.productionreport.mapServices;

import ir.novinp.productionreport.api.requestModel.WindowLogRequest;
import ir.novinp.productionreport.api.responseModel.WindowLogResponse;

public interface WindowLogMapService {

    WindowLogResponse startNextStep(WindowLogRequest request);

    WindowLogResponse endLastStep(Long id, WindowLogRequest request);

}
