package pl.toms.aplisens.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Encja reprezentująca kod produktów
 * 
 * @author toms
 * 
 */
@Entity
@Table(name = "kody")
public class Code {

	/**
	 * Dane produktu
	 */
	@OneToMany(mappedBy = "code")
	private List<Product> products;

	/**
	 * Kod produktu
	 */
	@Column(name = "kod")
	private String code;

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
	 * @param products
	 *            produkt
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	/**
	 * Zwraca kod produktu
	 * 
	 * @return kod produktu
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Ustawia kod produktu
	 * 
	 * @param code
	 *            kod produktu
	 */
	public void setTag(String code) {
		this.code = code;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Code [products=" + products + ", kod=" + code + "]";
	}

}
