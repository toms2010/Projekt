package pl.toms.aplisens.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * Value object z wybranymi parametrami dla produktu.
 * 
 * @see Product
 */
public class ProductVO {
    
    /**
     * Identyfikator produktu.
     */
    @Min(value=1)
    @Digits(fraction = 0, integer = 8)
    private Long id;
    
    /**
     * Nazwa produktu.
     */
    @NotBlank
    @Size(min = 2, max = 30)
    private String name;

    /**
     * Kod produktu.
     */
    @NotBlank
    @Size(min = 1, max = 10)
    private String code;

    /**
     * Cena produktu.
     */
    @PositiveOrZero
    private BigDecimal price;

    /**
     * Lista z identyfikatorami parametrów produktu.
     */
    private List<Long> productParameterID;

    /**
     * Lista z identyfikatorami wykonań produktu.
     */
    private List<Long> productDesignID;
    
    /**
     * Mapa zawierająca wszystkie składniki ceny.
     */
    private Map<String, BigDecimal> priceComponents;
    
    /**
     * Szczegółowy kod wykonania wybranego produktu.
     */
    private String orderCode;
    
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public List<Long> getProductParameterID()
    {
        return productParameterID;
    }

    public void setProductParameterID(List<Long> productParameterID)
    {
        this.productParameterID = productParameterID;
    }

    public List<Long> getProductDesignID()
    {
        return productDesignID;
    }

    public void setProductDesignID(List<Long> productDesignID)
    {
        this.productDesignID = productDesignID;
    }

    public Map<String, BigDecimal> getPriceComponents()
    {
        return priceComponents;
    }

    public void setPriceComponents(Map<String, BigDecimal> priceComponents)
    {
        this.priceComponents = priceComponents;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    @Override
    public String toString()
    {
        return "ProductVO [id=" + id + ", name=" + name + ", code=" + code + ", price=" + price + ", productParameterID=" + productParameterID
            + ", productDesignID=" + productDesignID + ", priceComponents=" + priceComponents + ", orderCode=" + orderCode + "]";
    }

}
