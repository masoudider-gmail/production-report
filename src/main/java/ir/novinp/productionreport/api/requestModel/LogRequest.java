package ir.novinp.productionreport.api.requestModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogRequest {

    private Long orderId;
    private String description;

}

