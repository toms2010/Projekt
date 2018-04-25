package pl.toms.aplisens.domain;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Min(value=1)
    @Digits(fraction = 0, integer = 8)
    private Long housingId;
    
    /**
     * Kod typu obudowy
     */
    @NotBlank
    @Size(min = 1, max = 10)
    //TODO chyba nie potrzebne
    private String housingCode;
    
    /**
     * Dolny zakres pomiarowy porduktu.
     */
    //TODO napisac metode na sprawdzanie czy cisnienei nie nizsze niz cis absolutnes
    @NotNull
    @Digits(fraction = 4, integer = 8)
    private BigDecimal rangeLow;

    /**
     * Górny zakres pomiarowy produktu.
     */
    @NotNull
    @Digits(fraction = 4, integer = 8)
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
