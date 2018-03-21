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

import org.springframework.stereotype.Component;

/**
 * Encja reprezentująca produky
 * @author toms
 *
 */
@Entity
@Table(name = "produkty")
@Component
public class Product extends BaseEntity{
	
	/**
	 * Lista parametrów produktu
	 */
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "produkt_parametry", 
				joinColumns = @JoinColumn(name = "produktID"), 
				inverseJoinColumns = @JoinColumn(name = "parametrID"))
	private List<ProductParameter> productParameter;
	
	/**
	 * Lista wykonań produktu
	 */
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "produkt_wykonania", 
				joinColumns = @JoinColumn(name = "produktID"), 
				inverseJoinColumns = @JoinColumn(name = "wykonanieID"))
	private List<ProductDesign> productDesign;
	
	/**
	 * Identyfikator kategorii produktu
	 */
    @ManyToOne 
    @JoinColumn(name="kategoria_ID")
	private Category category;
	
	/**
	 * Kod produktu
	 */
    @ManyToOne 
    @JoinColumn(name="kod_ID")
	private GroupCode code;
	
	/**
	 * Opis produktu
	 */
	@Column(name="dok_opis")
	private String description;
	
	/**
	 * Cena produktu
	 */
	@Column(name="cena")
	private BigDecimal price;
	
	/**
	 * Zwraca liste parametrów produktu
	 * 
	 * @return lista parametrów produktu
	 */
	public List<ProductParameter> getProductParameter() {
		return productParameter;
	}

	/**
	 * Ustawia liste parametrów produktu
	 * 
	 * @param productParameter lista parametrów produktu
	 */
	public void setProductParameter(List<ProductParameter> productParameter) {
		this.productParameter = productParameter;
	}

	/**
	 * Zwraca liste wykonań produktu
	 * 
	 * @return lista wykonań produktu
	 */
	public List<ProductDesign> getProductDesign() {
		return productDesign;
	}

	/**
	 * Ustawia liste wykonań produktu
	 * 
	 * @param productDesign lista wykonań produktu
	 */
	public void setProductDesign(List<ProductDesign> productDesign) {
		this.productDesign = productDesign;
	}

	/**
	 * Zwraca kategorie produktu
	 * 
	 * @return kategoria produktu
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Ustawia kategorie produktu
	 * 
	 * @param category Kategoria produktu
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Zwraca kod produktu
	 * 
	 * @return kod produktu
	 */
	public GroupCode getCode() {
		return code;
	}
	
	/**
	 * Ustawia kod produktu
	 * 
	 * @param code kod produktu
	 */
	public void setCode(GroupCode code) {
		this.code = code;
	}

	/**
	 * Zwraca opis produktu
	 * 
	 * @return opis produktu
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Ustawia opis produktu
	 * 
	 * @param description opis produktu
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Zwraca cene produktu
	 * 
	 * @return cena produktu
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Ustawia cene produktu
	 * 
	 * @param price cena produktu
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [category=" + category + ", code=" + code + ", description=" + description + ", price=" + price
				+ super.toString() + "]";
	}	
}
