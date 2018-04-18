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
 * Encja reprezentująca obudowy produktów
 * 
 */
@Entity
@Table(name = "housing")
public class Housing extends BaseEntity {
    /**
     * Lista produktów
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "products_housing", joinColumns = @JoinColumn(name = "housing_ID"), inverseJoinColumns = @JoinColumn(name = "product_ID"))
    private List<Product> products;

    /**
     * Kod parametru
     */
    @Column(name = "code", nullable = false)
    @NotBlank
    private String code;

    /**
     * Opis parametru
     */
    @Column(name = "description")
    private String description;

    /**
     * Cena wykonania produktu
     */
    @Column(name = "price", nullable = false)
    @NotNull
    @Currency(value = { "PLN" }) //TODO sprawdzić czy działa
    private BigDecimal price;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Housing [products=" + products + ", code=" + code + ", description=" + description + ", price=" + price + "]";
    }

    
}
