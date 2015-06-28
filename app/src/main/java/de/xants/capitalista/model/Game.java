package de.xants.capitalista.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Central class for all game related information
 */
public class Game {

    private double mWorth;
    private Map<ProductionType, Production> mProductionMap = new HashMap<ProductionType, Production>();
    private boolean mProductionUpgraded = true;
    private long mLastTickSecond = 0;
    private double mProductionPerSecCache = 0;
    /**
     * Stores the time the game last ticked
     */
    private long mLastTick = System.currentTimeMillis();
    /**
     * Global holder for current time
     */
    private long now = System.currentTimeMillis();

    public synchronized double getProductionPerSecond() {
        if (!this.mProductionUpgraded) {
            return this.mProductionPerSecCache;
        }
        this.mProductionUpgraded = false;
        this.mProductionPerSecCache = 0;
        for (Production p : this.mProductionMap.values()) {
            this.mProductionPerSecCache += p.getProductionPerSec();
        }
        return this.mProductionPerSecCache;
    }

    /**
     * Gets the production for the specified ProductionType
     *
     * @param productionType Production Type
     * @return the production or @null
     */
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
        this.mProductionUpgraded=true;
    }

    /**
     * Ticks the game engine
     */
    public void tick() {
        this.now = System.currentTimeMillis();
        this.tick(now - this.mLastTick);
        this.mLastTick=now;
    }

    /**
     * Steps the game by x miliseconds
     *
     * @param milisecond the game tick step
     */
    public void tick(long milisecond) {
        this.mWorth += this.getProductionPerSecond() * milisecond;
    }
}
