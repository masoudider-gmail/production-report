package ir.novinp.productionreport.api;

import ir.novinp.productionreport.api.requestModel.OrderRequest;
import ir.novinp.productionreport.api.responseModel.OrderResponse;
import ir.novinp.productionreport.mapServices.FileUtilMapService;
import ir.novinp.productionreport.mapServices.OrderMapService;
import ir.novinp.productionreport.model.status.FileNaming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderMapService mapService;

    @Autowired
    private FileUtilMapService fileService;

    @GetMapping
    public ResponseEntity getAllOrders() {
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

    @GetMapping("/winCut/{orderId}")
    public ResponseEntity getWindowCutList(@PathVariable Long orderId) throws IOException {

        Resource winCutList = fileService.download(orderId, FileNaming.CUT_LIST);

        return getFile(winCutList);
    }

    @PostMapping("/winCut/{orderId}")
    public ResponseEntity getUploadWindowCutList(@PathVariable Long orderId, @RequestParam("file") MultipartFile file) throws IOException {

        fileService.save(orderId, FileNaming.CUT_LIST, file);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/winList/{orderId}")
    public ResponseEntity getWindowList(@PathVariable Long orderId) throws IOException {

        Resource winCutList = fileService.download(orderId, FileNaming.WIN_LIST);

        return getFile(winCutList);
    }

    @PostMapping("/winList/{orderId}")
    public ResponseEntity getUploadWindowList(@PathVariable Long orderId, @RequestParam("file") MultipartFile file) throws IOException {

        fileService.save(orderId, FileNaming.WIN_LIST, file);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/glassList/{orderId}")
    public ResponseEntity getGlassList(@PathVariable Long orderId) throws IOException {

        Resource winCutList = fileService.download(orderId, FileNaming.GLASS_LIST);

        return getFile(winCutList);
    }

    @PostMapping("/glassList/{orderId}")
    public ResponseEntity uploadGlassList(@PathVariable Long orderId, @RequestParam("file") MultipartFile file) throws IOException {

        fileService.save(orderId, FileNaming.GLASS_LIST, file);

        return new ResponseEntity(HttpStatus.OK);
    }

    private ResponseEntity getFile(Resource resource) {

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }

}
