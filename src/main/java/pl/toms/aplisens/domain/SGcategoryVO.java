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
    private CableType cableType;
    
    /**
     * Długośc kabla
     */
    private Integer lenght;

    /**
     * Górny zakres pomiarowy produktu.
     */
    private BigDecimal rangeHigh;

    public CableType getCableType()
    {
        return cableType;
    }

    public void setCableType(CableType cableType)
    {
        this.cableType = cableType;
    }

    public Integer getLenght()
    {
        return lenght;
    }

    public void setLenght(Integer lenght)
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
        return "SGcategoryVO [cableType=" + cableType + ", lenght=" + lenght + ", rangeHigh=" + rangeHigh + "]";
    }
    
    
    
}
