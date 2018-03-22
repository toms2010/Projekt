package pl.toms.aplisens.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * Encja reprezentująca kod produktów
 * 
 * @author toms
 * 
 */
@Entity
@Table(name = "kody")
@Component
public class GroupCode {

	/**
	 * Identyfikator encji
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
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