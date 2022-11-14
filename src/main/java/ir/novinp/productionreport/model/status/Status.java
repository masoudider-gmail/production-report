package ir.novinp.productionreport.model.status;

import java.util.HashMap;
import java.util.Map;

public class Status {

    public static Map<Integer, String> glassProduction = new HashMap<>();

    public static Map<Integer, String> windowProduction = new HashMap<>();

    static {
        windowProduction.put(1, "IN_THE_COMPANY");
        windowProduction.put(2, "CUTTING_PROFILE");
        windowProduction.put(3, "CUTTING_SASH");
        windowProduction.put(4, "CUTTING_GALVANIZE");
        windowProduction.put(5, "WELDING");
        windowProduction.put(6, "WELDING_SASH");
        windowProduction.put(7, "CLEANING_WELDERS");
        windowProduction.put(8, "CUTTING_MULLION");
        windowProduction.put(9, "APPLYING_MULLION");
        windowProduction.put(10, "GASKET");
        windowProduction.put(11, "JOINING_SASH_AND_FRAME");
        windowProduction.put(12, "CUTTING_GLAZING_BEAD");
        windowProduction.put(13, "APPLYING_HANDLERS");
        windowProduction.put(14, "APPLY_WATER_DRIP");
        windowProduction.put(15, "COMPLETE");
        windowProduction.put(16, "MOVED_OUT");

        glassProduction.put(1, "IN_THE_COMPANY");
        glassProduction.put(2, "CUTTING_GLASS");
        glassProduction.put(3, "CUTTING_SPACER");
        glassProduction.put(4, "WASHING");
        glassProduction.put(5, "APPLY_SPACER");
        glassProduction.put(6, "APPLY_GLUE");
        glassProduction.put(7, "COMPLETE");
        glassProduction.put(8, "MOVED_OUT");
    }
}
