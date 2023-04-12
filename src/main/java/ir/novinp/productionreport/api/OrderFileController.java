package ir.novinp.productionreport.api;

import ir.novinp.productionreport.mapServices.FileUtilMapService;
import ir.novinp.productionreport.model.status.FileNaming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class OrderFileController {


    @Autowired
    private FileUtilMapService fileService;

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
