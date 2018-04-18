package pl.toms.aplisens.controller;

import com.mysql.jdbc.StringUtils;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.toms.aplisens.domain.ProductVO;
import pl.toms.aplisens.service.ProductDetailsService;
import pl.toms.aplisens.util.AppMessage;
import pl.toms.aplisens.util.ApplicationException;

/**
 * Kontroler zarządzający szczegółami produktu.
 */
@Controller
public class ProductDetailsController {
    private static final String PRODUCT_SUMMARY = "product-summary";

    protected static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailsController.class);

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
     * Zwraca okno z podsumowaniem produktu.
     * 
     * @param productVO obiekt z wartościami produktu
     * @param theModel model
     * @return okno z podsumowaniem produktu
     */
    @PostMapping("/saveProduct")
    public String getPrice(@ModelAttribute("productVO") ProductVO productVO, Model theModel) {
        LOGGER.debug("Pobrano wartości: {}", productVO);
        BigDecimal price = productDetailsService.countPricePC(productVO);
        theModel.addAttribute("totalPrice", price);
        return PRODUCT_SUMMARY;
    }
}