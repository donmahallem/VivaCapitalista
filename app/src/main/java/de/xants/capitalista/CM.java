package de.xants.capitalista;

import com.squareup.otto.Bus;

public class CM {

    private static Bus BUS = new Bus();

    public static Bus getBus() {
        return BUS;
    }
}
