package pl.toms.aplisens.domain;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Klasa z wartościami dla produktów z kategorii "SG".
 * 
 * @see Product
 */
public class SGcategoryVO extends ProductVO {
    
    /**
     * Typ kabla
     */
    @Min(value=1)
    @Digits(fraction = 0, integer = 8)
    private Long cableType;
    
    /**
     * Długośc kabla
     */
    @Min(value=1)
    @Max(value=1000)
    @Digits(fraction = 0, integer = 4)
    private Long lenght;

    /**
     * Górny zakres pomiarowy produktu.
     */
    @Min(value=1)
    @Max(value=500)
    @Digits(fraction = 0, integer = 3)
    private BigDecimal rangeHigh;

    /**
     * Kod kabla produktu
     */
    //TODO chyba nie potrzebne
    private String cableCode;
    
    public Long getCableType()
    {
        return cableType;
    }

    public void setCableType(Long cableType)
    {
        this.cableType = cableType;
    }

    public Long getLenght()
    {
        return lenght;
    }

    public void setLenght(Long lenght)
    {
        this.lenght = lenght;
    }

    public BigDecimal getRangeHigh()
    {
        return rangeHigh;
    }

    public void setRangeHigh(BigDecimal rangeHigh)
    {
        this.rangeHigh = rangeHigh;
    }

    public String getCableCode()
    {
        return cableCode;
    }

    public void setCableCode(String cableCode)
    {
        this.cableCode = cableCode;
    }
    
    @Override
    public String toString()
    {
        return "SGcategoryVO [cableType=" + cableType + ", lenght=" + lenght + ", rangeHigh=" + rangeHigh + super.toString() + "]";
    }
}
