package ir.novinp.productionreport.fileManager;

import ir.novinp.productionreport.api.responseModel.FileUploadResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile multipartFile) {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Long size = multipartFile.getSize();

        FileUtil.saveFile(fileName, multipartFile);

        FileUploadResponse response = FileUploadResponse
                .builder()
                .fileName(fileName)
                .size(size)
                .downloadUri("")
                .build();


        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/download")
    public ResponseEntity downloadFile() throws IOException {

        Resource file = FileUtil.getFile("CV.pdf");
        System.out.println("Download");
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + file.getFilename() + "\"";

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(file);
    }
}