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



///**
// * {@inheritDoc}
// */
//@Override
//public int hashCode() {
//    final int prime = 31;
//    int result = super.hashCode();
//    result = prime * result + ObjectUtils.hashCode(nazwa);
//    result = prime * result + ObjectUtils.hashCode(typ);
//    result = prime * result + ObjectUtils.hashCode(skladnikList);
//    return result;
//}
//
///**
// * {@inheritDoc}
// */
//@Override
//public boolean equals(final Object obj) {
//    if (this == obj) {
//        return true;
//    }
//    if (!(obj instanceof GrupaSklDTO)) {
//        return false;
//    }
//    final GrupaSklDTO other = (GrupaSklDTO) obj;
//    if (CommonLib.notEqual(nazwa, other.nazwa, null)) {
//        return false;
//    }
//    if (CommonLib.notEqual(typ, other.typ, null)) {
//        return false;
//    }
//    if (CollectionLib.notEqual(skladnikList, other.skladnikList, new ArrayList<>())) {
//        return false;
//    }
//    return true;
//}
//
///**
// * {@inheritDoc}
// *
// * @author Michal Tomaszczak (66% = 2 / 3)
// * @author Katarzyna Herczak (33% = 1 / 3)
// */
//@Override
//public String toString() {
//    return "GrupaSklDTO [nazwa=" + nazwa + ", typ=" + typ + ", skladnikList=" + skladnikList + ", toString()=" + super.toString() + "]";
//}