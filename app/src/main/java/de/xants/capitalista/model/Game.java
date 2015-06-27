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

    /**
     * Upgrades ProductionType by 1 level
     *
     * @param productionType ProductionType
     */
    public void upgrade(ProductionType productionType) {
        this.upgrade(productionType, 1);
    }

    /**
     * Upgrades productiontype by x level
     *
     * @param productionType ProductionType
     * @param level          Level
     */
    public void upgrade(ProductionType productionType, int level) {
        if (productionType == null)
            return;
        if (this.mProductionMap.containsKey(productionType))
            return;
        this.mProductionMap.get(productionType).upgrade(level);
    }
}
