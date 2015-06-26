package de.xants.capitalista.model;

public class Production {
    private ProductionType mProductionType;

    public Production(ProductionType productionType) {
        this.mProductionType = productionType;
    }

    public ProductionType getProductionType() {
        return mProductionType;
    }

    public void setProductionType(ProductionType productionType) {
        mProductionType = productionType;
    }
}
