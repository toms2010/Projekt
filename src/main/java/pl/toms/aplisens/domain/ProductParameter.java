package pl.toms.aplisens.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Encja reprezentująca parametry product
 * 
 */
@Entity
@Table(name = "parameters")
public class ProductParameter extends BaseEntity {

    /**
     * Lista produktów
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "products_parameters", joinColumns = @JoinColumn(name = "parameter_ID"), inverseJoinColumns = @JoinColumn(name = "product_ID"))
    private List<Product> products;
    /**
     * Wartość parametru produktu
     */
    @Column(name = "param_value")
    private String value;

    /**
     * Kod parametru
     */
    @Column(name = "code")
    private String code;

    /**
     * Opis parametru
     */
    @Column(name = "description")
    private String description;

    /**
     * Zwraca listę produktów
     * 
     * @return lista produktów
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Ustawia listę produktów
     * 
     * @param products lista produktów
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Zwraca wartość parametru
     * 
     * @return wartość parametru
     */
    public String getValue() {
        return value;
    }

    /**
     * Ustawia wartość parametru
     * 
     * @param value wartość parametru
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * Zwraca kod parametru
     * 
     * @return code parametru
     */
    public String getCode() {
        return code;
    }

    /**
     * Ustawia kod parametru
     * 
     * @param code kod parametru
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Zwraca opis parametru
     * 
     * @return opis parametru
     */
    public String getDescription() {
        return description;
    }

    /**
     * Ustawia opis parametru
     * 
     * @param opis opis parametru
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString() */
    @Override
    public String toString() {
        return "ProductParameter [value=" + value + ", code=" + code + ", description=" + description + super.toString() + "]";
    }

}
