package pl.toms.aplisens.domain;

import java.math.BigDecimal;
import java.util.List;

import pl.toms.aplisens.util.PresureUnits;

/**
 * Obiekt z wartościami dla produktu.
 * 
 * @see Product
 */
public class ProductVO {
    /**
     * Nazwa produktu.
     */
    private String name;

    /**
     * Tag produktu.
     */
    private String tag;

    /**
     * Cena produktu.
     */
    private BigDecimal price;

    /**
     * Dolny zakres pomiarowy porduktu.
     */
    private BigDecimal rangeLow;

    /**
     * Górny zakres pomiarowy produktu.
     */
    private BigDecimal rangeHigh;

    /**
     * Jednostka zakresu pomiarowego.
     */
    private PresureUnits unit = null;

    /**
     * Lista z identyfikatorami parametrów produktu.
     */
    private List<Long> productParameterID;

    /**
     * Lista z identyfikatorami wykonań produktu.
     */
    private List<Long> productDesignID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRangeLow() {
        return rangeLow;
    }

    public void setRangeLow(BigDecimal rangeLow) {
        this.rangeLow = rangeLow;
    }

    public BigDecimal getRangeHigh() {
        return rangeHigh;
    }

    public void setRangeHigh(BigDecimal rangeHigh) {
        this.rangeHigh = rangeHigh;
    }

    public PresureUnits getUnit() {
        return unit;
    }

    public void setUnit(PresureUnits unit) {
        this.unit = unit;
    }

    public List<Long> getProductParameterID() {
        return productParameterID;
    }

    public void setProductParameterID(List<Long> productParameterID) {
        this.productParameterID = productParameterID;
    }

    public List<Long> getProductDesignID() {
        return productDesignID;
    }

    public void setProductDesignID(List<Long> productDesignID) {
        this.productDesignID = productDesignID;
    }

    @Override
    public String toString() {
        return "ProductVO [name=" + name + ", tag=" + tag + ", price=" + price + ", rangeLow=" + rangeLow + ", rangeHigh=" + rangeHigh + ", unit="
                + unit + ", productParameterID=" + productParameterID + ", productDesignID=" + productDesignID + "]";
    }
}
