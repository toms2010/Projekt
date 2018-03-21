package pl.toms.aplisens.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

@Component
@MappedSuperclass
public class BaseEntity {

	/**
	 * Identyfikator encji
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * Nazwa encji
	 */
	@Column(name = "nazwa")
	private String name;

	/**
	 * Data utworzenia wpisu
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_utworzenia", updatable = false, nullable = false)
	private Date createDate = new Date();

	/**
	 * Zwaraca identyfikator obiektu
	 * 
	 * @return identyfikator obiektu
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Zwraca nazwę obiektu
	 * 
	 * @return nazwa obiektu
	 */
	public String getName() {
		return name;
	}

	/**
	 * Ustawia nazwę obiektu
	 * 
	 * @param nazwa obiektu
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Zwraca date utworzenia
	 * 
	 * @return data utworzenia
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseEntity [productID=" + id + ", name=" + name + ", createDate=" + createDate + "]";
	}

}
