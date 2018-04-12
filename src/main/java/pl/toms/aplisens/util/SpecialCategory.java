package pl.toms.aplisens.util;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Klasa resprzentująca kategorie dla których występują specjalne formularze
 *
 */
public class SpecialCategory{
    /**
     * Kategorie dla których występują specjalne formularze
     */
    public enum Category
    {
        /**
         * Sondy głębokości
         */
        SG,
        
        /**
         * Przetworniki ciśnienia
         */
        PC, 
        
        /**
         * Czujniki temperatury
         */
        CT
    }
    
    @Autowired
    private AppMessage appMessage;
    
    /**
     * Zwraca odpowiednią kategorie na podstawie przekazenego tagu, jeśli nie ma specjalnej kategori dla przekazanego tagu zwraca kategorie {@code} 
     * 
     * @param tag tag kategorii produktów
     * @return 
     */
    public void getValueByTag(String tag)
    {
        try {
            Category.valueOf(tag);
        }
        catch(NullPointerException exc) {
            throw new RuntimeException(appMessage.getAppMessage("error.product.load", "Błąd pobrania produktu", null));
        }
      
  
    }

}
        

