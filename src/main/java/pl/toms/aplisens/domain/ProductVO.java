package pl.toms.aplisens.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Klasa z wartościami dla produktu.
 * 
 * @see Product
 */
public class ProductVO {
    /**
     * Nazwa produktu.
     */
    private String name;

    /**
     * Kod produktu.
     */
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        return "ProductVO [name=" + name + ", code=" + code + ", price=" + price + ", rangeLow=" + rangeLow + ", rangeHigh=" + rangeHigh
                + ", productParameterID=" + productParameterID + ", productDesignID=" + productDesignID + "]";
    }

   
}
