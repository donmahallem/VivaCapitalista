package de.xants.capitalista.model.otto;

import de.xants.capitalista.model.ProductionType;

public class ProductionUpgradeEvent {
    public final int LEVEL;
    public final ProductionType PRODUCTION_TYPE;

    private ProductionUpgradeEvent(int level, ProductionType productionType) {
        this.LEVEL = level;
        this.PRODUCTION_TYPE = productionType;
    }

    public static ProductionUpgradeEvent create(int level, ProductionType productionType) {
        return new ProductionUpgradeEvent(level, productionType);
    }
}
