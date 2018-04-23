package pl.toms.aplisens.domain;

import java.math.BigDecimal;

/**
 * Klasa z wartościami dla produktów z kategorii "SG".
 * 
 * @see Product
 */
public class SGcategoryVO extends ProductVO{
    /**
     * Typ kabla
     */
    private Long cableType;
    
    /**
     * Długośc kabla
     */
    private Long lenght;

    /**
     * Górny zakres pomiarowy produktu.
     */
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
