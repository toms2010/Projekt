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

/**
 * Bazowa klasa encji
 */
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
     * Ustawia identyfikator
     * 
     * @param id identyfikator obiektu
     */
    public void setId(final Long id) {
        this.id = id;
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
     * @param name nazwa obiektu
     */
    public void setName(final String name) {
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

    /**
     * Ustawia date utworzenia obiektu
     * 
     * @param createDate data utworzenia obiektu
     */
    public void setCreateDate(final Date createDate) {
        this.createDate = createDate;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString() */
    @Override
    public String toString() {
        return "BaseEntity [id=" + id + ", name=" + name + ", createDate=" + createDate + "]";
    }

}
