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
import javax.persistence.Table;

/**
 * Encja reprezentująca produky
 * @author toms
 *
 */
@Entity
@Table(name = "casing")
public class Casing extends BaseEntity{
   
    /**
     * Lista produktów
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "produkt_parametry", 
                joinColumns = @JoinColumn(name = "parametrID"), 
                inverseJoinColumns = @JoinColumn(name = "produktID"))
    private List<Product> products;

    /**
     * Kod parametru
     */
    @Column(name="kod")
    private String code;
    
    /**
     * Opis parametru
     */
    @Column(name="opis")
    private String description;
    
    /**
     * Cena wykonania produktu
     */
    @Column(name = "cena")
    private BigDecimal price;
    
    
}
