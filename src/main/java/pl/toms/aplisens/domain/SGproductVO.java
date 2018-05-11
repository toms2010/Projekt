package pl.toms.aplisens.domain;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Klasa z wartościami dla produktów z kategorii "SG".
 * 
 * @see Product
 */
public class SGproductVO extends ProductVO {
    
    /**
     * Typ kabla
     */
    private Long cableType;
    
    /**
     * Długośc kabla
     */
    @NotNull
    @Min(value=1)
    @Max(value=1000)
    @Digits(fraction = 0, integer = 8)
    private Long lenght;

    /**
     * Górny zakres pomiarowy produktu.
     */
    @NotNull
    @Min(value=1)
    @Max(value=500)
    @Digits(fraction = 0, integer = 8)
    private BigDecimal rangeHigh;
    
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
    
    @Override
    public String toString()
    {
        return "SGcategoryVO [cableType=" + cableType + ", lenght=" + lenght + ", rangeHigh=" + rangeHigh + super.toString() + "]";
    }
}
