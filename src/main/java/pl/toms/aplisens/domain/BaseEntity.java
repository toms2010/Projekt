package pl.toms.aplisens.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Nazwa encji
     */
    @NotNull(message = "{NotNull.baseEntity.name}")
    @Size(min = 3, max = 30, message="{Size.baseEntity.name}")
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Data utworzenia wpisu
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false, nullable = false)
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
