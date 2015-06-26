package de.xants.capitalista.model;

import de.xants.capitalista.R;

/**
 * Created by Don on 26.06.2015.
 */
public enum ProductionType {
    TYPE1(R.string.type1, R.drawable.ic_dvr_black_48dp),
    TYPE2(R.string.type2, R.drawable.ic_dvr_black_48dp),
    TYPE3(R.string.type3, R.drawable.ic_dvr_black_48dp),
    TYPE4(R.string.type4, R.drawable.ic_dvr_black_48dp),
    TYPE5(R.string.type5, R.drawable.ic_dvr_black_48dp),
    TYPE6(R.string.type6, R.drawable.ic_dvr_black_48dp),
    TYPE7(R.string.type7, R.drawable.ic_dvr_black_48dp),
    TYPE8(R.string.type8, R.drawable.ic_dvr_black_48dp),
    TYPE9(R.string.type9, R.drawable.ic_dvr_black_48dp);

    public final int TITLE, DRAWABLE;

    ProductionType(int title, int drawable) {
        TITLE = title;
        DRAWABLE = drawable;
    }
}
