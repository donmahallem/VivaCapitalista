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

import de.xants.capitalista.CM;
import de.xants.capitalista.model.otto.ProductionUpgradeEvent;

public class Production {
    private final ProductionType mProductionType;
    private int mLevel;

    public Production(ProductionType productionType) {
        this.mProductionType = productionType;
    }

    public ProductionType getProductionType() {
        return mProductionType;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    public double getProductionPerSec() {
        return this.mProductionType.BASE_PRODUCTION * Math.pow(this.mProductionType.MULTIPLIER, this.mLevel);
    }

    /**
     * Calculates the cost for the upgrade to the next level
     *
     * @return upgrade cost
     */
    public double upgradeCost() {
        return this.upgradeCost(1);
    }

    /**
     * Calculates the cost for the upgrade by x level
     *
     * @param level level
     * @return the upgrade cost
     */
    public double upgradeCost(int level) {
        return this.levelCost(this.mLevel + level) - this.levelCost();
    }

    /**
     * Calculates the cost for the current level
     *
     * @return cost for current level
     */
    public double levelCost() {
        return this.levelCost(this.mLevel);
    }

    /**
     * calculates the cost for the specified level
     *
     * @param level level
     * @return cost for level
     */
    public double levelCost(int level) {
        return this.mProductionType.BASE_COST * Math.pow(this.mProductionType.MULTIPLIER, level);
    }

    /**
     * upgrades the current production by x level
     *
     * @param level Level
     */
    public void upgrade(int level) {
        this.mLevel += level;
        CM.getBus().post(ProductionUpgradeEvent.create(level, this.mProductionType));
    }
}
