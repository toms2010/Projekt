package pl.toms.aplisens.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Encja reprezentująca kategorie produktów
 * 
 */
@Entity
@Table(name = "kategorie")
public class Category extends BaseEntity {

    /**
     * Dane kategorii
     */
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;

    /**
     * Tag kategorii
     */
    @Column(name = "tag", nullable=false)
    @NotNull(message = "To long")
    @Size(min = 2, max = 4)
    private String tag;

    /**
     *  Ilośc produktów w kategorii 
     *  */
    @Transient
    private Integer size;

    /**
     * Zwraca listę produktów nalezących do kategorii
     * 
     * @return lista produktów
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Ustawia listę produktów nalezących do kategorii
     * 
     * @param products lista produktów
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Zwraca tag kategorii
     * 
     * @return tag kategorii
     */
    public String getTag() {
        return tag;
    }

    /**
     * Ustawia tag kategorii
     * 
     * @param tag tag kategorii
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Zwraca ilość produktów w kategorii
     * 
     * @return ilość produktów
     */
    public Integer getSize() {
        setSize(products.size());
        return size;
    }

    /**
     * Ustawia ilośc produktów w kategorii
     * 
     * @param size ilość produktów
     */
    private void setSize(Integer size) {
        this.size = size;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString() 
     * */
    @Override
    public String toString() {
        return "Category [tag= " + tag + " ;" + super.toString() + "]";
    }
}
