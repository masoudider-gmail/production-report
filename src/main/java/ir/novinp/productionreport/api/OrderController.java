package ir.novinp.productionreport.api;

import ir.novinp.productionreport.api.requestModel.OrderRequest;
import ir.novinp.productionreport.api.responseModel.OrderResponse;
import ir.novinp.productionreport.mapServices.OrderMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderMapService mapService;

    @GetMapping
    public ResponseEntity getAllOrders() {
        System.out.println("requested");
        return new ResponseEntity(mapService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveOrder(@RequestBody @Valid OrderRequest request) {
        OrderResponse savedOrder = mapService.save(request);
        return new ResponseEntity(savedOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid OrderRequest request) throws Exception {
        OrderResponse orderResponse = mapService.updateById(id, request);
        return new ResponseEntity(orderResponse, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {
        mapService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
