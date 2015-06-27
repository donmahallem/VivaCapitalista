package de.xants.capitalista.model;

public class Production {
    private ProductionType mProductionType;
    private int mLevel;

    public Production(ProductionType productionType) {
        this.mProductionType = productionType;
    }

    public ProductionType getProductionType() {
        return mProductionType;
    }

    public void setProductionType(ProductionType productionType) {
        mProductionType = productionType;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        mLevel = level;
    }
}
