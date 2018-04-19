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
public class CableType extends BaseEntity
{
    /**
     * Cena wykonania produktu.
     */
    @Column(name = "price", nullable = false)
    @NotNull
    @Currency(value = { "PLN" })
    private BigDecimal price;

    /**
     * Opis wykonania produktu.
     */
    @Column(name = "description")
    private String description;

    
    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "CableType [price=" + price + ", description=" + description + "Super" + super.toString() + "]";
    }
    
    
}
