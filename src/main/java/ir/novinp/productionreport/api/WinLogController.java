package ir.novinp.productionreport.api;

import ir.novinp.productionreport.api.requestModel.LogRequest;
import ir.novinp.productionreport.api.responseModel.LogResponse;
import ir.novinp.productionreport.mapServices.WindowLogMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/winLog")
@CrossOrigin(origins = "*")
public class WinLogController {

    @Autowired
    private WindowLogMapService mapService;

    @PostMapping
    public ResponseEntity startNextStep(@RequestBody LogRequest request) throws Exception {
        LogResponse response = mapService.startNextStep(request);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping("/done")
    public ResponseEntity endLastStep(@RequestBody LogRequest request) throws Exception {
        LogResponse response = mapService.endLastStep(request);
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity loadAllByOrderId(@PathVariable Long id) throws Exception {
        List<LogResponse> logResponses = mapService.loadAll(id);
        return new ResponseEntity(logResponses, HttpStatus.OK);
    }
}
