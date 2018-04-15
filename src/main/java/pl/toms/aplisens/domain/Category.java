package pl.toms.aplisens.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Encja reprezentująca kategorie produktów
 * 
 */
@Entity
@Table(name = "kategorie")
public class Category extends BaseEntity {

    /**
     * Dane produktu
     */
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;

    /**
     * Tag produktu
     */
    @Column(name = "tag")
    private String tag;

    /* Ilośc produktów w kategorii */
    @Transient
    private Integer size;

    /**
     * Zwraca produkt
     * 
     * @return produkt
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Ustawia produkt
     * 
     * @param products produkt
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Zwraca tag produktu
     * 
     * @return tag produktu
     */
    public String getTag() {
        return tag;
    }

    /**
     * Ustawia tag produktu
     * 
     * @param tag tag produktu
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Zwraca ilośc produktów
     * 
     * @return ilośc produktów
     */
    public Integer getSize() {
        setSize(products.size());
        return size;
    }

    /**
     * Ustawia ilośc produktów
     * 
     * @param size ilośc produktów
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
