package de.xants.capitalista;

import com.squareup.otto.Bus;

/**
 * Created by Don on 26.06.2015.
 */
public class CM {

    private static Bus BUS = new Bus();

    public static Bus getBus() {
        return BUS;
    }
}
