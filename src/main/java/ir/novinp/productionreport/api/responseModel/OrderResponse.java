package ir.novinp.productionreport.api.responseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long orderId;
    private String name;
    private String picture;
    private int windowCount;
    private double windowMeter;
    private int glassCount;
    private double glassMeter;
    private String windowProductionStatus;
    private String glassProductionStatus;
    private LocalDateTime creationDate;
    private LocalDateTime lastModificationDate;
    private LocalDateTime completeDate;
    private LocalDateTime outDate;
    private String appUser;
}
