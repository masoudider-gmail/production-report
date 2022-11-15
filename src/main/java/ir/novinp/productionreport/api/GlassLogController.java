package ir.novinp.productionreport.api;

import ir.novinp.productionreport.api.requestModel.LogRequest;
import ir.novinp.productionreport.api.responseModel.LogResponse;
import ir.novinp.productionreport.mapServices.GlassLogMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/winLog")
public class GlassLogController {


    @Autowired
    private GlassLogMapService mapService;

    @PostMapping
    public ResponseEntity startNextStep(@RequestBody LogRequest request) throws Exception {
        LogResponse response = mapService.startNextStep(request);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping("/done")
    public ResponseEntity endLastStep( @RequestBody LogRequest request) throws Exception {
        LogResponse response = mapService.endLastStep( request);
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }
}
