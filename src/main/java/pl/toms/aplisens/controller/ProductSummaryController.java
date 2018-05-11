package pl.toms.aplisens.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mysql.jdbc.StringUtils;

import pl.toms.aplisens.domain.PCproductVO;
import pl.toms.aplisens.domain.SGproductVO;
import pl.toms.aplisens.service.ProductDetailsService;
import pl.toms.aplisens.service.SummaryService;
import pl.toms.aplisens.util.AppMessage;
import pl.toms.aplisens.util.ApplicationException;

/**
 * Kontroler zarządzający podsumowaniem produktu.
 */
@Controller
public class ProductSummaryController {
    
    /** Okno z podsumowaniem produktu. */
    private static final String PRODUCT_SUMMARY = "product-summary";
    
    protected static final Logger LOGGER = LoggerFactory.getLogger(ProductSummaryController.class);
   
    /**
     * Serwis do obsługi szczegółów produktów.
     */
    @Autowired
    private ProductDetailsService productDetailsService;
    
    /**
     * Serwis do obsługi podsumowania produktów.
     */
    @Autowired
    private SummaryService summaryService;
    
    /**
     * Generator komunikatów aplikacji.
     */
    @Autowired
    private AppMessage appMessage;
    
    /**
     * Oblicza cenę produktu z grupy "PC", zbiera jego parametry oraz wyświetla okno podusmowania produktu.
     * 
     * @param productVO obiekt z wartościami produktu
     * @param theModel model
     * @return okno z podsumowaniem produktu 
     */
    @PostMapping("/countPCProduct")
    public String getPCSummary(@Valid @ModelAttribute("productVO") PCproductVO productVO, BindingResult bindingResult, Model theModel) {
        
        if (bindingResult.hasErrors()) {
            LOGGER.error(appMessage.getAppMessage("error.validationError", new Object[] {bindingResult.getFieldErrorCount()}));
            String window = productDetailsService.displayDetailsForm(productVO.getId(), theModel);
            if (StringUtils.isNullOrEmpty(window)) {
                throw new ApplicationException(appMessage.getAppMessage("error.product.window", null));
            }
            return window;
        }
        
        LOGGER.debug(appMessage.getAppMessage("info.infoVO", new Object[] {productVO}));
        BigDecimal price = summaryService.createSummary(productVO);
        theModel.addAttribute("totalPrice", price);
        return PRODUCT_SUMMARY;
    }
    
    /**
     * Oblicza cenę produktu z grupy "SG", zbiera jego parametry oraz wyświetla okno podusmowania produktu.
     * 
     * @param productVO obiekt z wartościami produktu
     * @param theModel model
     * @return okno z podsumowaniem produktu
     */
    @PostMapping("/countSGProduct")
    public String getSGSummary(@Valid @ModelAttribute("productVO") SGproductVO productVO, BindingResult bindingResult, Model theModel) {
        
        if (bindingResult.hasErrors()) {
            LOGGER.error(appMessage.getAppMessage("error.validationError", new Object[] {bindingResult.getFieldErrorCount()}));
            String window = productDetailsService.displayDetailsForm(productVO.getId(), theModel);
            if (StringUtils.isNullOrEmpty(window)) {
                throw new ApplicationException(appMessage.getAppMessage("error.product.window", null));
            }
            return "product-SG-details";
        }
        
        LOGGER.debug(appMessage.getAppMessage("info.infoVO", new Object[] {productVO}));
        BigDecimal price = summaryService.createSummary(productVO, theModel);
        theModel.addAttribute("totalPrice", price);
        return PRODUCT_SUMMARY;
    }
}
