package ir.novinp.productionreport.api;

import ir.novinp.productionreport.api.requestModel.LogRequest;
import ir.novinp.productionreport.api.responseModel.LogResponse;
import ir.novinp.productionreport.api.responseModel.OrderResponse;
import ir.novinp.productionreport.mapServices.OrderLogMapService;
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
    private OrderLogMapService windowLogMapService;

    @PostMapping
    public ResponseEntity continueTask(@RequestBody LogRequest request) throws Throwable {
        OrderResponse response = windowLogMapService.continueTask(request);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity loadAllByOrderId(@PathVariable Long id) throws Exception {
        List<LogResponse> logResponses = windowLogMapService.loadAll(id);
        return new ResponseEntity(logResponses, HttpStatus.OK);
    }
}
