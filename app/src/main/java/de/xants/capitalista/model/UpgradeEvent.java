package de.xants.capitalista.model;

/**
 * Created by Don on 26.06.2015.
 */
public class UpgradeEvent {
    private final ProductionType mProductionType;

    public UpgradeEvent(ProductionType productionType) {
        this.mProductionType = productionType;
    }

    public ProductionType getProductionType() {
        return mProductionType;
    }
}
