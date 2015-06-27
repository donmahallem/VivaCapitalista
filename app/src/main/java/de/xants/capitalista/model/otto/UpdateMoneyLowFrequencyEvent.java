package de.xants.capitalista.model.otto;

/**
 * Event emited less frequent e.g. only every second
 */
public class UpdateMoneyLowFrequencyEvent {
    public final double MONEY;

    private UpdateMoneyLowFrequencyEvent(double money) {
        this.MONEY = money;
    }

    public static UpdateMoneyLowFrequencyEvent create(double money) {
        return new UpdateMoneyLowFrequencyEvent(money);
    }
}
