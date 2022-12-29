package ir.novinp.productionreport.mapServices;

import ir.novinp.productionreport.model.status.FileNaming;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUtilMapService {
    public void save(Long orderId, FileNaming naming, MultipartFile file) throws IOException;

    public Resource download(Long orderId, FileNaming naming) throws IOException;
}
