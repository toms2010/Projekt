package pl.toms.aplisens.domain;

import java.util.List;

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
     * 
     */
    private Housing housing;
    
    public PresureUnits getUnit() {
        return unit;
    }

    public void setUnit(PresureUnits unit) {
        this.unit = unit;
    }

}
