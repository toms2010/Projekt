package pl.toms.aplisens.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.domain.ProductVO;
import pl.toms.aplisens.util.AppMessage;
import pl.toms.aplisens.util.ApplicationException;
import pl.toms.aplisens.util.PresureUnits;

/**
 * Implementacja serwisu do zarządzania szczegółami produktów.
 *
 */
@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailsServiceImpl.class);

    /** Okno formularza ze szczegółami dla przetworników ciśnienia. */
    private static final String PC_DETAILS_WINDOW = "product-details";

    /** Okno formularza ze szczegółami dla sond głębokości. */
    private static final String SG_DETAILS_WINDOW = "inProgres";

    /** Okno formularza ze szczegółami dla pozostałych urządzeń. */
    private static final String DEFAULT_DETAILS_WINDOW = "inProgres";

    /** Okno zwracane gdy nie przekazano identyfikatora produktu. */
    private static final String NO_ARG_WINDOW = "category-list";

    @Autowired
    private ProductService productService;

    /**
     * Generator komunikatów aplikacji.
     */
    @Autowired
    private AppMessage appMessage;

    /**
     * {@inheritDoc}
     * 
     */
    public Map<String, Object> displayDetailsForm(Long productId, Model theModel) {
        String returnWindow;
        HashMap<String, Object> additionalAttributes = new HashMap<>();
        HashMap<String, Object> data = new HashMap<>();

        if (productId != null || theModel != null) {
            Product product = productService.getProductById(productId);
            if (product == null)
                throw new ApplicationException(appMessage.getAppMessage("error.product.load", null));

            theModel.addAttribute("product", product);
            theModel.addAttribute("productVO", new ProductVO());
            String category = product.getCategory().getTag();
            if (category == null)
                throw new ApplicationException(appMessage.getAppMessage("error.product.loadCategory", null));

            switch (category) {
            case "PC":
                additionalAttributes.put("units", PresureUnits.values());
                returnWindow = PC_DETAILS_WINDOW;
                break;
            case "SG":
                returnWindow = SG_DETAILS_WINDOW;
                break;
            default:
                returnWindow = DEFAULT_DETAILS_WINDOW;
                break;
            }
        } else {
            returnWindow = NO_ARG_WINDOW;
        }
        data.put("WINDOW", returnWindow);
        data.put("ADDITIONAL_ATTRIBUTES", additionalAttributes);
        LOGGER.debug(appMessage.getAppMessage("info.showing", new Object[] { returnWindow, productId }));
        return data;
    }

    /**
     * {@inheritDoc}
     * 
     */
    public BigDecimal countPricePC(ProductVO productVO) {
        if (productVO == null)
            throw new ApplicationException(appMessage.getAppMessage("error.productVO.null", null));

        BigDecimal finalPrice = BigDecimal.ZERO;
        List<BigDecimal> priceComponents = new ArrayList<>();
        priceComponents.add(productVO.getPrice());
        priceComponents.add(countPCRangePrice(productVO));
        priceComponents.add(countDesignPrice(productVO));

        for (BigDecimal price : priceComponents) {
            finalPrice = finalPrice.add(price);
        }
        return finalPrice;
    }

    /**
     * Oblicza dodatek do ceny za dodatkowe wykonania produktu.
     *         
     * @param productVO biekt z wartościami produktu
     * @return dodatek do ceny za wykonania
     */
    private BigDecimal countDesignPrice(ProductVO productVO) {
        List<Long> designs = productVO.getProductDesignID();
        if (designs == null || designs.isEmpty())
            return BigDecimal.ZERO;
        // TODO Załadowanie listy design
        return BigDecimal.valueOf(500);
    }

    /**
     * Oblicza dodatkową cenę za zakres pomiarowy urządzeń z kategorii "PC"
     * 
     * @param productVO biekt z wartościami produktu
     * @return dodatek do ceny za zakres
     */
    private BigDecimal countPCRangePrice(ProductVO productVO) {
        BigDecimal rangeLow = productVO.getRangeLow();
        BigDecimal rangeHigh = productVO.getRangeHigh();
        if (productVO.getUnit() == null)
            throw new ApplicationException(appMessage.getAppMessage("error.productVO.unit", null));
            
        BigDecimal multiplicand = BigDecimal.valueOf(productVO.getUnit().getMultiplier());
        rangeLow = rangeLow.multiply(multiplicand);
        rangeHigh = rangeHigh.multiply(multiplicand);

        if ((rangeHigh.subtract(rangeLow)).abs().compareTo(BigDecimal.valueOf(10)) < 1)
            return BigDecimal.valueOf(250);

        if (rangeHigh.compareTo(BigDecimal.valueOf(6000)) > 0 || rangeLow.compareTo(BigDecimal.valueOf(6000)) > 0) {
            if (rangeHigh.compareTo(BigDecimal.valueOf(20000)) > 0 || rangeLow.compareTo(BigDecimal.valueOf(20000)) > 0) {
                return BigDecimal.valueOf(200);
            } else
                return BigDecimal.valueOf(100);
        } else
            return BigDecimal.valueOf(0);
    }

}
