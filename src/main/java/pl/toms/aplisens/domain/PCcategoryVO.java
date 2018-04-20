package pl.toms.aplisens.domain;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Range;

import pl.toms.aplisens.util.PresureUnits;

/**
 * Klasa z wartościami dla produktów z kategorii "PC".
 * 
 * @see Product
 */
public class PCcategoryVO extends ProductVO {
   
    /**
     * Jednostka zakresu pomiarowego.
     */
    private PresureUnits unit = null;

    /**
     * Typ obudowy
     */
    private Housing housing;
    
    /**
     * Dolny zakres pomiarowy porduktu.
     */
    private BigDecimal rangeLow;

    /**
     * Górny zakres pomiarowy produktu.
     */
    private BigDecimal rangeHigh;

    public PresureUnits getUnit()
    {
        return unit;
    }

    public void setUnit(PresureUnits unit)
    {
        this.unit = unit;
    }

    public Housing getHousing()
    {
        return housing;
    }

    public void setHousing(Housing housing)
    {
        this.housing = housing;
    }

    public BigDecimal getRangeLow()
    {
        return rangeLow;
    }

    public void setRangeLow(BigDecimal rangeLow)
    {
        this.rangeLow = rangeLow;
    }

    public BigDecimal getRangeHigh()
    {
        return rangeHigh;
    }

    public void setRangeHigh(BigDecimal rangeHigh)
    {
        this.rangeHigh = rangeHigh;
    }
    


}
