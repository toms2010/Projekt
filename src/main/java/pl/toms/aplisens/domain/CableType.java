package pl.toms.aplisens.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Currency;

/**
 * Encja reprezentująca typy kabli.
 * 
 */
@Entity
@Table(name = "cables")
public class CableType extends BaseEntity {
    /**
     * Cena za 1m kabla.
     */
    @Column(name = "price", nullable = false)
    @NotNull
    @Currency(value = { "PLN" })
    private BigDecimal price;

    /**
     * Opis kabla.
     */
    @Column(name = "description")
    private String description;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString() {
        return "CableType [price=" + price + ", description=" + description + "Super" + super.toString() + "]";
    }

}
