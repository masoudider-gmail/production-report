package ir.novinp.productionreport.api.requestModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotNull(message = "Name Can Not Be Null")
    private String name;

    @NotNull(message = "WindowCount Can Not Be Null")
    private int windowCount;

    @NotNull(message = "WindowMeter Can Not Be Null")
    private double windowMeter;

    @NotNull(message = "GlassCount Can Not Be Null")
    private int glassCount;

    @NotNull(message = "GlassMeter Can Not Be Null")
    private double glassMeter;

}
