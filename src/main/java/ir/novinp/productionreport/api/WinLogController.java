package ir.novinp.productionreport.api;

import ir.novinp.productionreport.api.requestModel.WindowLogRequest;
import ir.novinp.productionreport.api.responseModel.WindowLogResponse;
import ir.novinp.productionreport.mapServices.WindowLogMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/winLog")
public class WinLogController {

    @Autowired
    private WindowLogMapService mapService;

    @PostMapping
    public ResponseEntity startNextStep(@RequestBody WindowLogRequest request) throws Exception {
        WindowLogResponse response = mapService.startNextStep(request);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping("/done")
    public ResponseEntity endLastStep( @RequestBody WindowLogRequest request) throws Exception {
        WindowLogResponse response = mapService.endLastStep( request);
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }
}
