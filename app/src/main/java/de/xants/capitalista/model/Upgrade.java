package de.xants.capitalista.model;

public class Upgrade {
    public final ProductionType PRODUCTION_TYPE;
    public final double COST;

    Upgrade(ProductionType productionType, double cost) {
        this.PRODUCTION_TYPE = productionType;
        this.COST = cost;
    }

    public Upgrade create(int position) {
        switch (position % 10) {
            case 0:
                return new Upgrade(ProductionType.TYPE1, 10 * Math.pow(2, position));
            case 1:
                return new Upgrade(ProductionType.TYPE2, 10 * Math.pow(2, position));
            case 2:
                return new Upgrade(ProductionType.TYPE3, 10 * Math.pow(2, position));
            case 3:
                return new Upgrade(ProductionType.TYPE4, 10 * Math.pow(2, position));
            case 4:
                return new Upgrade(ProductionType.TYPE5, 10 * Math.pow(2, position));
            case 5:
                return new Upgrade(ProductionType.TYPE6, 10 * Math.pow(2, position));
            case 6:
                return new Upgrade(ProductionType.TYPE7, 10 * Math.pow(2, position));
            case 7:
                return new Upgrade(ProductionType.TYPE8, 10 * Math.pow(2, position));
            case 8:
                return new Upgrade(ProductionType.TYPE9, 10 * Math.pow(2, position));
            case 9:
                return new Upgrade(ProductionType.TYPE10, 10 * Math.pow(2, position));
            default:
                return null;
        }
    }


}
