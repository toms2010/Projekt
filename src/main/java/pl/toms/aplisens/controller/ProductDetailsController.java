package pl.toms.aplisens.controller;

import com.mysql.jdbc.StringUtils;

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
import org.springframework.web.bind.annotation.RequestParam;

import pl.toms.aplisens.domain.PCcategoryVO;
import pl.toms.aplisens.domain.SGcategoryVO;
import pl.toms.aplisens.service.ProductDetailsService;
import pl.toms.aplisens.util.AppMessage;
import pl.toms.aplisens.util.ApplicationException;

/**
 * Kontroler zarządzający szczegółami produktu.
 */
@Controller
public class ProductDetailsController {
    /** Okno z podsumowaniem produktu. */
    private static final String PRODUCT_SUMMARY = "product-summary";

    protected static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailsController.class);

    /**
     * Serwis do obsługi szczegółów produktów.
     */
    @Autowired
    private ProductDetailsService productDetailsService;

    /**
     * Generator komunikatów aplikacji.
     */
    @Autowired
    private AppMessage appMessage;

    /**
     * Zwraca stronę z formularzem szczegółów produku.
     * 
     * @param theModel model
     * @param productId identyfikator produktu
     * @return okno ze szczegółami produktu 
     */
    @PostMapping("/details")
    public String getProductDetails(@RequestParam("productId") Long productId, Model theModel) {
        String window = productDetailsService.displayDetailsForm(productId, theModel);
        if (StringUtils.isNullOrEmpty(window)) {
            throw new ApplicationException(appMessage.getAppMessage("error.product.window", null));
        }
        return window;
    }

    /**
     * Zapisuje produkt z grupy "PC" i zwraca okno z podsumowaniem produktu.
     * 
     * @param productVO obiekt z wartościami produktu
     * @param theModel model
     * @return okno z podsumowaniem produktu 
     */
    @PostMapping("/savePCProduct")
    public String getPCSummary(@Valid @ModelAttribute("productVO") PCcategoryVO productVO, BindingResult bindingResult, Model theModel) {
        if (bindingResult.hasErrors()) {
            LOGGER.error(appMessage.getAppMessage("error.validationError", new Object[] {bindingResult.getFieldErrorCount()}));
            //TODO
        }
        LOGGER.debug(appMessage.getAppMessage("info.infoVO", new Object[] {productVO}));
        BigDecimal price = productDetailsService.createOrderPC(productVO);
        theModel.addAttribute("totalPrice", price);
        return PRODUCT_SUMMARY;
    }
    
    /**
     * Zapisuje produkt z grupy "PC" i zwraca okno z podsumowaniem produktu.
     * 
     * @param productVO obiekt z wartościami produktu
     * @param theModel model
     * @return okno z podsumowaniem produktu
     */
    @PostMapping("/saveSGProduct")
    public String getSGSummary(@Valid @ModelAttribute("productVO") SGcategoryVO productVO, BindingResult bindingResult, Model theModel) {
        if (bindingResult.hasErrors()) {
            LOGGER.error(appMessage.getAppMessage("error.validationError", new Object[] {bindingResult.getFieldErrorCount()}));
            //TODO
        }
        LOGGER.debug(appMessage.getAppMessage("info.infoVO", new Object[] {productVO}));
        BigDecimal price = productDetailsService.createOrderSG(productVO, theModel);
        theModel.addAttribute("totalPrice", price);
        return PRODUCT_SUMMARY;
    }
}