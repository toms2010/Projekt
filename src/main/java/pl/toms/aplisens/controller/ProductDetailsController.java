package pl.toms.aplisens.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.jdbc.StringUtils;

import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.domain.ProductVO;
import pl.toms.aplisens.service.ProductDetailsService;
import pl.toms.aplisens.service.ProductService;
import pl.toms.aplisens.util.AppMessage;
import pl.toms.aplisens.util.PresureUnits;
import pl.toms.aplisens.util.SpecialCategory;

/**
 * Kontroler zarządzający szczegółami produktu
 */
@Controller
public class ProductDetailsController
{
    protected static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailsController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDetailsService productDetailsService;
    
    @Autowired
    private AppMessage appMessage;
    
    /** 
     * Zwraca stronę z formularzem szczegółów produktów
     * 
     * @param theModel
     * @param productId
     *        identyfikator produktu
     * @return product-details.jsp
     */
    @RequestMapping("/details")
    public String getProductDetails(@RequestParam("productId") Long productId, Model theModel)
    {
        HashMap<String, Object> data = productDetailsService.displayDetailsForm(productId, theModel);
        String window = (String) data.get("WINDOW");
        theModel = (Model) data.get("MODEL");
        if (StringUtils.isNullOrEmpty(window)) {
            throw new RuntimeException(appMessage.getAppMessage("error.product.loadCategory", "Błąd pobrania kategorii produktu [in]", null));
        }
        return window;
    }

    /**
     * Oblicza cenę produktu
     * 
     * @param
     * @param
     * @return
     */
    @RequestMapping("/saveProduct")
    public String getPrice(@ModelAttribute("productVO") ProductVO productVO, Model theModel)
    {
        LOGGER.debug("------------------------------------");
        LOGGER.debug(productVO.toString());
        return "test";
    }
}