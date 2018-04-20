package pl.toms.aplisens.domain;

import java.math.BigDecimal;

/**
 * Klasa z wartościami dla produktów z kategorii "SG".
 * 
 * @see Product
 */
public class SGcategoryVO extends ProductVO{
    /**
     * Dolny zakres pomiarowy porduktu.
     */
    private BigDecimal rangeLow = BigDecimal.ZERO;
    
    /**
     * Typ kabla
     */
    private CableType cableType;
    
    /**
     * Długośc kabla
     */
    private Integer lenght;

    /* (non-Javadoc)
     * @see java.lang.Object#toString() 
     */
    @Override
    public BigDecimal getRangeLow() {
        return rangeLow;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString() 
     */
    @Override
    public void setRangeLow(BigDecimal rangeLow) {
        this.rangeLow = rangeLow;
    }

    public CableType getCableType() {
        return cableType;
    }

    public void setCableType(CableType cableType) {
        this.cableType = cableType;
    }

    public Integer getLenght() {
        return lenght;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }

    @Override
    public String toString() {
        return "SGcategoryVO [rangeLow=" + rangeLow + ", cableType=" + cableType + ", lenght=" + lenght + super.toString() + "]";
    }
}
