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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Currency;

/**
 * Encja reprezentująca produky.
 *
 */
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    /**
     * Lista parametrów produktu.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "products_parameters", joinColumns = @JoinColumn(name = "product_ID"), inverseJoinColumns = @JoinColumn(name = "parameter_ID"))
    private List<ProductParameter> productParameter;

    /**
     * Lista wykonań produktu.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "products_designs", joinColumns = @JoinColumn(name = "product_ID"), inverseJoinColumns = @JoinColumn(name = "design_ID"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ProductDesign> productDesign;

    /**
     * Lista obudów produktów.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "products_housing", joinColumns = @JoinColumn(name = "product_ID"), inverseJoinColumns = @JoinColumn(name = "housing_ID"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Housing> housing;
    
    /**
     * Identyfikator kategorii produktu.
     */
    @ManyToOne 
    @JoinColumn(name = "category_ID", nullable = false)
    @NotEmpty
    private Category category;

    /**
     * Kod produktu.
     */
    @Column(name = "code", nullable = false)
    @NotBlank
    @Size(min = 2, max = 10)
    private String code;

    /**
     * Opis produktu.
     */
    @Column(name = "description")
    private String description;

    /**
     * Cena produktu.
     */
    @Column(name = "price")
    @NotNull
    @Currency(value = { "PLN" }) //TODO sprawdzić czy działa
    private BigDecimal price;

    /**
     * Zwraca liste parametrów produktu.
     * 
     * @return lista parametrów produktu
     */
    public List<ProductParameter> getProductParameter() {
        return productParameter;
    }

    /**
     * Ustawia liste parametrów produktu.
     * 
     * @param productParameter lista parametrów produktu
     */
    public void setProductParameter(List<ProductParameter> productParameter) {
        this.productParameter = productParameter;
    }

    /**
     * Zwraca liste wykonań produktu.
     * 
     * @return lista wykonań produktu
     */
    public List<ProductDesign> getProductDesign() {
        return productDesign;
    }

    /**
     * Ustawia liste wykonań produktu.
     * 
     * @param productDesign lista wykonań produktu
     */
    public void setProductDesign(List<ProductDesign> productDesign) {
        this.productDesign = productDesign;
    }

    /**
     * Zwraca liste obudów produktu.
     * 
     * @return lista obudów produktu
     */
    public List<Housing> getHousing() {
        return housing;
    }
    
    /**
     * Ustawia liste obudów produktu.
     * 
     * @param housing lista obudów produktu
     */
    public void setHousing(List<Housing> housing) {
        this.housing = housing;
    }

    /**
     * Zwraca kategorie produktu.
     * 
     * @return kategoria produktu
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Ustawia kategorie produktu.
     * 
     * @param category Kategoria produktu
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Zwraca kod produktu.
     * 
     * @return kod produktu
     */
    public String getCode() {
        return code;
    }

    /**
     * Ustawia kod produktu.
     * 
     * @param code kod produktu
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Zwraca opis produktu.
     * 
     * @return opis produktu
     */
    public String getDescription() {
        return description;
    }

    /**
     * Ustawia opis produktu.
     * 
     * @param description opis produktu
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Zwraca cene produktu.
     * 
     * @return cena produktu
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Ustawia cene produktu.
     * 
     * @param price cena produktu
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString() */
    @Override
    public String toString() {
        return "Product [category=" + category + "; " + super.toString() + ", code=" + code + ", description=" + description + ", price=" + price
                + "]";
    }
}
