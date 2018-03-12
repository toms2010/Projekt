package pl.toms.aplisens.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Encja reprezentująca kategorie produktów
 * @author toms
 * 
 */
@Entity
@Table(name="kategorie")
public class Category extends BaseEntity{
	
	/**
	 * Dane produktu
	 */
	@OneToMany(mappedBy="category")
	private List<Product> products;
	
	/**
	 * Tag produktu
	 */
	@Column(name="tag")
	private String tag;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Category [products=" + products + ", tag=" + tag + super.toString() + "]";
	}
	

	

}
