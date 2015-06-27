package de.xants.capitalista.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Central class for all game related information
 */
public class Game {

    private double mWorth;
    private Map<ProductionType, Production> mProductionMap = new HashMap<ProductionType, Production>();

    public double getProductionPerSec() {
        double production = 0;
        for (Production p : this.mProductionMap.values()) {
            production += p.getProductionPerSec();
        }
        return production;
    }

    public Production getProduction(ProductionType productionType) {
        return this.mProductionMap.get(productionType);
    }
}
