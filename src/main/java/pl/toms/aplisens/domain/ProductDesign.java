package pl.toms.aplisens.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Currency;

/**
 * Encja reprezentująca wykonania product.
 * 
 */
@Entity
@Table(name = "designs")
public class ProductDesign extends BaseEntity {

    /**
     * Lista produktów.
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
    @JoinTable(name = "products_designs", joinColumns = @JoinColumn(name = "design_ID"), inverseJoinColumns = @JoinColumn(name = "product_ID"))
    private List<Product> products;

    /**
     * Opis wykonania produktu.
     */
    @Column(name = "description")
    private String description;

    /**
     * Kod wykonania produktu.
     */
    @Column(name = "code", nullable = false)
    @NotBlank
    private String code;

    /**
     * Cena wykonania produktu.
     */
    @Column(name = "price", nullable = false)
    @NotNull
    @Currency(value = { "PLN" }) // TODO sprawdzić czy działa
    private BigDecimal price;

    /**
     * Zwraca produkty.
     * 
     * @return produkty
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Ustawia produkty.
     * 
     * @param products produkty
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Zwraca opis wykonania.
     * 
     * @return opis wykonania
     */
    public String getDescription() {
        return description;
    }

    /**
     * Ustawia opis wykonania.
     * 
     * @param description opis wykonania
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Zwraca kod wykonania.
     * 
     * @return kod wykonania
     */
    public String getCode() {
        return code;
    }

    /**
     * Ustaiwa kod wykonania.
     * 
     * @param code kod wykonania
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Zwraca cene wykonania.
     * 
     * @return cena wykonania
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Ustawia cene wykonania.
     * 
     * @param price cena wykonania
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString() */
    @Override
    public String toString() {
        return "ProductDesign [products=" + products + ", description=" + description + ", code=" + code + ", price=" + price + super.toString()
                + "]";
    }
}
