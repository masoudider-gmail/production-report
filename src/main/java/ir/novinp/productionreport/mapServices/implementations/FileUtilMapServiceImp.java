package ir.novinp.productionreport.mapServices.implementations;

import ir.novinp.productionreport.mapServices.FileUtilMapService;
import ir.novinp.productionreport.model.status.FileNaming;
import ir.novinp.productionreport.services.FileUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUtilMapServiceImp implements FileUtilMapService {

    @Autowired
    private FileUtilService service;

    public void save(Long orderId, FileNaming naming, MultipartFile file) throws IOException {
        service.saveFile(resolveName(orderId, naming), file);
    }

    public Resource download(Long orderId, FileNaming naming) throws IOException {
        return service.downloadFile(resolveName(orderId, naming));
    }

    private String resolveName(Long orderId, FileNaming naming) {
        return orderId +"_"+ naming.name();
    }

}
