package de.xants.capitalista.model;

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

    public double upgradeCost() {
        return this.upgradeCost(1);
    }

    public double upgradeCost(int level) {
        return this.levelCost(this.mLevel + level) - this.levelCost();
    }

    public double levelCost() {
        return this.levelCost(this.mLevel);
    }

    public double levelCost(int level) {
        return this.mProductionType.BASE_COST * Math.pow(this.mProductionType.MULTIPLIER, level);
    }
}
