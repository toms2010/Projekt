package pl.toms.aplisens.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import pl.toms.aplisens.domain.PCproductVO;
import pl.toms.aplisens.domain.SGproductVO;

/**
 * Serwis do zarządzania podsumowaniem produktu.
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public interface SummaryService
{
    /**
     * Tworzy podsumowanie produktu z grupy "PC".
     * 
     * @param productVO obiekt z wartościami produktu
     * @return całkowita cena produktu
     */
    BigDecimal createSummary(PCproductVO productVO);

    /**
     * Tworzy podsumowanie produktu z grupy "SG".
     * 
     * @param productVO obiekt z wartościami produktu
     * @param theModel model
     * @return całkowita cena produktu
     */
    BigDecimal createSummary(SGproductVO productVO, Model theModel);
}
