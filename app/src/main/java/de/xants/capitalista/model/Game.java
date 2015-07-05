/*
 * Copyright 2015 https://github.com/donmahallem/VivaCapitalista
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.xants.capitalista.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Central class for all game related information
 */
public class Game {

    private double mWorth;
    private Map<ProductionType, Production> mProductionMap = new HashMap<ProductionType, Production>();
    private boolean mProductionUpgraded = true;
    private double mProductionPerSecCache = 0;
    /**
     * Stores the time the game last ticked
     */
    private long mLastTick = System.currentTimeMillis();
    /**
     * Global holder for current time
     */
    private long now = System.currentTimeMillis();

    public Game() {
        //  CM.getBus().register(this);
    }
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
    @Nullable
    public Production getProduction(ProductionType productionType) {
        return this.mProductionMap.get(productionType);
    }

    /**
     * Upgrades ProductionType by 1 level
     *
     * @param productionType ProductionType
     */
    public void upgrade(@NonNull ProductionType productionType) {
        this.upgrade(productionType, 1);
    }

    /**
     * Upgrades productiontype by x level
     *
     * @param productionType ProductionType
     * @param level          Level
     */
    public void upgrade(@NonNull ProductionType productionType, int level) {
        if (productionType == null)
            return;
        if (this.mProductionMap.containsKey(productionType))
            return;
        this.mProductionMap.get(productionType).upgrade(level);
        this.mProductionUpgraded = true;
    }


    /**
     * Ticks the game engine
     */
    public void tick() {
        this.now = System.currentTimeMillis();
        this.tick(now - this.mLastTick);
        this.mLastTick = now;
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
