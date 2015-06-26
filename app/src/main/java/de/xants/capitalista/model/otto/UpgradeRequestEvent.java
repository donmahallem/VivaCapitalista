package de.xants.capitalista.model.otto;

import de.xants.capitalista.model.ProductionType;

public class UpgradeRequestEvent {
    private final ProductionType mProductionType;

    public UpgradeRequestEvent(ProductionType productionType) {
        this.mProductionType = productionType;
    }

    public ProductionType getProductionType() {
        return mProductionType;
    }
}
