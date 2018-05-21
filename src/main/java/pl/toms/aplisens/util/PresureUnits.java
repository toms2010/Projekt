package pl.toms.aplisens.util;

/**
 * Jednostki ciśnienia (przeliczenie na kPa).
 */
public enum PresureUnits {
    
    kPa(1), 
    Pa(1000),
    mPa(0.001), 
    bar(0.01);

    /**
     * Przelicznik jednostki do jednostki bazowej (kPa).
     */
    private double multiplier;

    /**
     * Konstruktor
     * 
     * @param multiplier przelicznik do kPA
     */
    PresureUnits(final double multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Zwraca {@link #multiplier}
     *
     * @return przelicznik jednostki
     */
    public double getMultiplier() {
        return multiplier;
    }
}