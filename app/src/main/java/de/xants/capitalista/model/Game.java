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

import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.Map;

import de.xants.capitalista.CM;
import de.xants.capitalista.model.otto.UpgradeMultiplierChangeEvent;
import de.xants.capitalista.model.otto.UpgradeMultiplierEvent;
import timber.log.Timber;

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
    private Multiplier mUpgradeMultiplier = Multiplier.M_1;

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
        this.mProductionUpgraded = true;
    }

    @Produce
    public UpgradeMultiplierEvent getUpgradeMultiplier() {
        return UpgradeMultiplierEvent.create(this.mUpgradeMultiplier);
    }


    @Subscribe
    public void onUpgradeMultiplierChangeEvent(UpgradeMultiplierChangeEvent upgradeMultiplierChangeEvent) {
        Timber.d("onUpgradeMultiplierChangeEvent - " + this.mUpgradeMultiplier);
        if (this.mUpgradeMultiplier == Multiplier.M_1)
            this.mUpgradeMultiplier = Multiplier.M_10;
        else if (this.mUpgradeMultiplier == Multiplier.M_10)
            this.mUpgradeMultiplier = Multiplier.M_100;
        else if (this.mUpgradeMultiplier == Multiplier.M_100)
            this.mUpgradeMultiplier = Multiplier.M_MAX;
        else if (this.mUpgradeMultiplier == Multiplier.M_MAX)
            this.mUpgradeMultiplier = Multiplier.M_1;
        CM.getBus().post(UpgradeMultiplierEvent.create(this.mUpgradeMultiplier));
    }
    @Subscribe
    public void onUpgradeMultiplierEvent(UpgradeMultiplierEvent upgradeMultiplierEvent) {
        this.mUpgradeMultiplier = upgradeMultiplierEvent.MULTIPLIER;
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
