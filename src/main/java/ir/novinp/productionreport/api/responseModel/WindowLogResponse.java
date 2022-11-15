package ir.novinp.productionreport.api.responseModel;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class WindowLogResponse {
    private Long id;
    private Long orderId;
    private LocalDateTime creationDate;
    private LocalDateTime completeDate;
    private String appUser;
    private String description;
    private String status;
}
