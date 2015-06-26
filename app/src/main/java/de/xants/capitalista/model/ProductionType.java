package de.xants.capitalista.model;

import de.xants.capitalista.R;

public enum ProductionType {
    TYPE1(R.string.type1, R.drawable.ic_dvr_black_48dp, 10, 1, 10 * 1000, 1.01),
    TYPE2(R.string.type2, R.drawable.ic_dvr_black_48dp, 200, 10, 30 * 1000, 1.01),
    TYPE3(R.string.type3, R.drawable.ic_dvr_black_48dp, 4000, 10, 60 * 1000, 1.01),
    TYPE4(R.string.type4, R.drawable.ic_dvr_black_48dp, 80000, 10, 2 * 60 * 1000, 1.01),
    TYPE5(R.string.type5, R.drawable.ic_dvr_black_48dp, 200000, 100, 6 * 60 * 1000, 1.01),
    TYPE6(R.string.type6, R.drawable.ic_dvr_black_48dp, 400000, 1000, 15 * 60 * 1000, 1.01),
    TYPE7(R.string.type7, R.drawable.ic_dvr_black_48dp, 1000000, 10000, 30 * 60 * 1000, 1.01),
    TYPE8(R.string.type8, R.drawable.ic_dvr_black_48dp, 4000000, 100000, 1 * 3600 * 1000, 1.01),
    TYPE9(R.string.type9, R.drawable.ic_dvr_black_48dp, 10000000, 1000000, 10 * 3600 * 1000, 1.01),
    TYPE10(R.string.type9, R.drawable.ic_dvr_black_48dp, 25000000, 10000000, 24 * 3600 * 1000, 1.01);

    public final int TITLE, DRAWABLE;
    public final double BASE_COST, MULTIPLIER, BASE_PRODUCTION, BASE_TIME;

    ProductionType(int title, int drawable, double baseCost, double baseProduction, double baseTime, double multiplier) {
        this.TITLE = title;
        this.DRAWABLE = drawable;
        this.BASE_TIME = baseTime;
        this.BASE_COST = baseCost;
        this.BASE_PRODUCTION = baseProduction;
        this.MULTIPLIER = multiplier;
    }

}
