package pl.toms.aplisens.DTO;

import pl.toms.aplisens.domain.BaseEntity;

/**
 * Bazowa klasa obiektów DTO reprezentujących dane obiektów {@link BaseEntity}
 */
public class BaseDTO
{
    
    /**
     * Identyfikator obiektu
     *
     */
    private String id;
    
    /**
     * Nazwa obiektu
     */
    private String name;

    /**
     * Zwraca identyfikator obiektu
     *
     * @return identyfikator obiektu
     *
     */
    public String getId()
    {
        return id;
    }
    
    /**
     * Ustawia identyfikator obiektu
     *
     * @param id identyfikator obiektu
     *
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * Zwraca nazwę obiektu
     *
     * @return nazwa obiektu
     *
     */
    public String getName()
    {
        return name;
    }
    /**
     * Ustawia nazwę obiektu
     *
     * @param name nazwa obiektu
     *
     */
    public void setName(String name)
    {
        this.name = name;
    }
}