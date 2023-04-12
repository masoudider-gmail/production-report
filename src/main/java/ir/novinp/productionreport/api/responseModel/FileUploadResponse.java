package ir.novinp.productionreport.api.responseModel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileUploadResponse {
    private String fileName;
    private String downloadUri;
    private Long size;
}
