package ir.novinp.productionreport.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUtilService {
    void saveFile(String fileName, MultipartFile multipartFile) throws IOException;

    Resource downloadFile(String fileName) throws IOException;

}
