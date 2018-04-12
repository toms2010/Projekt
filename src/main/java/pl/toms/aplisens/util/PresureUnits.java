package pl.toms.aplisens.util;

/**
 * Jednostki ciśnienia (przeliczenie na kPa)
 */
public enum PresureUnits
{
     kPa(1), Pa(1000), mPa(0.001), bar(0.01);

    private double multiplier;

    PresureUnits(double multiplier)
    {
        this.multiplier = multiplier;
    }

    public double getMultiplier()
    {
        return multiplier;
    }
}