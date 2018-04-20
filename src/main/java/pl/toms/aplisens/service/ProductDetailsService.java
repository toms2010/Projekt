package pl.toms.aplisens.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import pl.toms.aplisens.domain.PCcategoryVO;

/**
 * Serwis do zarządzania szczegółami produktu.
 *
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

    /**
     * Liczy cenę produktu z grupy "PC".
     * 
     * @param productVO obiekt z wartościami produktu
     * @return całkowita cena produktu
     */
    BigDecimal countPricePC(PCcategoryVO productVO);
}
