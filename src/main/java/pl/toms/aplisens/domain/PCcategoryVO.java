package pl.toms.aplisens.domain;

import java.math.BigDecimal;

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
    private Long housingId;
    
    /**
     * Kod typu obudowy
     */
    private String housingCode;
    
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

    public Long getHousingId()
    {
        return housingId;
    }

    public void setHousingId(Long housingId)
    {
        this.housingId = housingId;
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

    public String getHousingCode()
    {
        return housingCode;
    }

    public void setHousingCode(String housingCode)
    {
        this.housingCode = housingCode;
    }

    @Override
    public String toString()
    {
        return "PCcategoryVO [unit=" + unit + ", housingId=" + housingId + ", rangeLow=" + rangeLow + ", rangeHigh=" + rangeHigh +  super.toString() + "]";
    }
    


}
