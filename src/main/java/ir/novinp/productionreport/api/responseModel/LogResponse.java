package ir.novinp.productionreport.api.responseModel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class LogResponse {
    private Long id;
    private Long orderId;
    private LocalDateTime creationDate;
    private LocalDateTime completeDate;
    private String appUser;
    private String description;
    private String status;
}
