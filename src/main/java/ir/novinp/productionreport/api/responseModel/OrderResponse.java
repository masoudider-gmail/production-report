package ir.novinp.productionreport.api.responseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private boolean windowProductionStepDone;
    private String glassProductionStatus;
    private boolean glassProductionStepDone;
    private LocalDate creationDate;
    private LocalDate lastModificationDate;
    private LocalDate completeDate;
    private LocalDate outDate;
    private String appUser;
}
