package pl.toms.aplisens.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

/**
 * Serwis do zarządzania szczegółami produktu.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public interface ProductDetailsService {

    /**
     * Zwraca okno z formularzem szczegółów produktu.
     * 
     * @param productId identyfikator produktu
     * @param theModel model
     * @return odpowiedni formularz ze szczegółami produktu
     */
    String displayDetailsForm(Long productId, Model theModel);
    
}
