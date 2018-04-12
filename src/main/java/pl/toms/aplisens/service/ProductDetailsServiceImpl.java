package pl.toms.aplisens.service;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.domain.ProductVO;
import pl.toms.aplisens.util.AppMessage;
import pl.toms.aplisens.util.PresureUnits;
import pl.toms.aplisens.util.SpecialCategory;

/**
 * Implementacja serwisu wewnętrznego do zarządzania szczegółami produktów
 *
 */
@Service
public class ProductDetailsServiceImpl implements ProductDetailsService
{
    protected static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailsServiceImpl.class);

    /** Okno formularza ze szczegółami dla przetworników ciśnienia */
    private static final String PC_DETAILS_WINDOW = "product-details";

    /** Okno formularza ze szczegółami dla sond głębokości */
    private static final String SG_DETAILS_WINDOW = "product-details";

    /** Okno formularza ze szczegółami dla pozostałych urządzeń */
    private static final String DEFAULT_DETAILS_WINDOW = "product-details";

    /** Okno zwracane gdy nie przekazano identyfikatora produktu */
    private static final String NO_ARG_WINDOW = "product-details";
    
    private String returnWindow;
    
    @Autowired
    private ProductService productService;

    @Autowired
    private AppMessage appMessage;

    /**
     * {@inheritDoc}
     * 
     */
    public HashMap<String, Object> displayDetailsForm(Long productId, Model theModel)
    {
        if (productId != null && theModel != null)
        {
            Product product = productService.getProductById(productId);
            if (product == null)
            {
                throw new RuntimeException(appMessage.getAppMessage("error.product.load", "Błąd pobrania produktu", null));
            }
            LOGGER.debug("Load product : product={}", product);
            theModel.addAttribute("product", product);
            theModel.addAttribute("productVO", new ProductVO());
            String category = product.getCategory().getTag();
            if (category == null)
            {
                throw new RuntimeException(appMessage.getAppMessage("error.product.loadCategory", "Błąd pobraniakategorii produktu", null));
            }
            switch (category)
            {
                case "PC":
                    theModel.addAttribute("units", PresureUnits.values());
                    LOGGER.debug("Showing {}", PC_DETAILS_WINDOW);
                    returnWindow = PC_DETAILS_WINDOW;
                case "SG":
                    LOGGER.debug("Showing {}", SG_DETAILS_WINDOW);
                    returnWindow = SG_DETAILS_WINDOW;
                default:
                    LOGGER.debug("Showing {}", DEFAULT_DETAILS_WINDOW);
                    returnWindow = DEFAULT_DETAILS_WINDOW;
            }
        }
        else {
            returnWindow =  NO_ARG_WINDOW;
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put("window", returnWindow);
        data.put("model", theModel);
    }

    /**
     * {@inheritDoc}
     * 
     */
    public BigDecimal countPrice(Product product, HttpServletRequest request)
    {
        if ("SG".equals(product.getCategory().getTag()))
        {
            return null;
        }
        else if ("PC".equals(product.getCategory().getName()))
        {
            return countPC(product, request);
        }
        else
        {
            return null;
        }
    }

    /**
     * Oblicza cenę urządzenia z kategorii "PC"
     * 
     * @param product encja produktu
     * @param request informacje pochodzące z servletu
     * @return całkowita cena urządzenia
     */
    private BigDecimal countPC(Product product, HttpServletRequest request)
    {
        if (request == null)
        {
            throw new RuntimeException("CategoryId is null ");
        }
        float rangeLow = (float) request.getAttribute("rangeLow");
        float rangeHigh = (float) request.getAttribute("rangeHigh");
        String unit = (String) request.getAttribute("unit");
        BigDecimal rangePrice = countRangePrice(rangeLow, rangeHigh, unit);
        BigDecimal finalPrice = product.getPrice().add(rangePrice);
        return finalPrice;
    }

    /**
     * Oblicza dodatkową cenę za zakres pomiarowy urządzeń z kategorii "PC"
     * 
     * @param rangeLow dolna granica zakresu pomiarowego
     * @param rangeHigh górna granica zakresu pomiarowego
     * @return dodatek do ceny za zakres
     */
    private BigDecimal countRangePrice(float rangeLow, float rangeHigh, final String unit)
    {
        rangeLow *= PresureUnits.valueOf(unit).getMultiplier();
        rangeHigh *= PresureUnits.valueOf(unit).getMultiplier();

        if ((Math.abs(rangeHigh - rangeLow)) <= 10)
            return BigDecimal.valueOf(250);
        if (rangeHigh > 6000 || rangeLow > 6000)
        {
            if (rangeHigh > 20000 || rangeLow > 20000)
            {
                return BigDecimal.valueOf(200);
            }
            else
                return BigDecimal.valueOf(100);
        }
        else
            return BigDecimal.valueOf(0);
    }

}
