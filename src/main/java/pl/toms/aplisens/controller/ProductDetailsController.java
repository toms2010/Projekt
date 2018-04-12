package pl.toms.aplisens.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.domain.ProductVO;
import pl.toms.aplisens.service.ProductDetailsService;
import pl.toms.aplisens.service.ProductService;
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
        Product product = productService.getProductById(productId);
        LOGGER.debug("Load product : product={}", product);
        theModel.addAttribute("product", product);
        theModel.addAttribute("productVO", new ProductVO());

        SpecialCategory cat = SpecialCategory.valueOf(product.getCategory().getTag());;
        switch (cat)
        {
            case PC:
                theModel.addAttribute("units", PresureUnits.values());
                LOGGER.debug("Showing {}", PC_DETAILS_WINDOW);
                return PC_DETAILS_WINDOW;
            case SG:
                LOGGER.debug("Showing {}", SG_DETAILS_WINDOW);
                return SG_DETAILS_WINDOW;
            default:
                LOGGER.debug("Showing {}", DEFAULT_DETAILS_WINDOW);
                return DEFAULT_DETAILS_WINDOW;
        }
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