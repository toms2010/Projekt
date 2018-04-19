package pl.toms.aplisens.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.domain.ProductDesign;
import pl.toms.aplisens.domain.ProductVO;
import pl.toms.aplisens.repository.ProductDesignRepository;
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
    private static final String PC_DETAILS_WINDOW = "product-PC-details";

    /** Okno formularza ze szczegółami dla sond głębokości. */
    private static final String SG_DETAILS_WINDOW = "inProgres";

    /** Okno formularza ze szczegółami dla pozostałych urządzeń. */
    private static final String DEFAULT_DETAILS_WINDOW = "inProgres";

    /** Okno zwracane gdy nie przekazano identyfikatora produktu. */
    private static final String NO_ARG_WINDOW = "category-list";

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDesignRepository repo;
    /**
     * Generator komunikatów aplikacji.
     */
    @Autowired
    private AppMessage appMessage;

    /**
     * {@inheritDoc}
     * 
     */
    public String displayDetailsForm(Long productId, Model theModel) {
        String returnWindow;

        if (productId != null || theModel != null) {
            Product product = productService.getProductById(productId);
            if (product == null) {
                throw new ApplicationException(appMessage.getAppMessage("error.product.load", null));
            }
            theModel.addAttribute("product", product);
            theModel.addAttribute("productVO", new ProductVO());
            String category = product.getCategory().getCode();
            if (category == null) {
                throw new ApplicationException(appMessage.getAppMessage("error.product.loadCategory", null));
            }

            switch (category) {
            case "PC":
                theModel.addAttribute("units", PresureUnits.values());
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

        LOGGER.debug(appMessage.getAppMessage("info.showing", new Object[] { returnWindow, productId }));
        return returnWindow;
    }

    /**
     * {@inheritDoc}
     * 
     */
    public BigDecimal countPricePC(ProductVO productVO) {
        if (productVO == null) {
            throw new ApplicationException(appMessage.getAppMessage("error.productVO.null", null));
        }
        BigDecimal finalPrice = BigDecimal.valueOf(0);
        List<BigDecimal> priceComponents = new ArrayList<>();
        priceComponents.add(productVO.getPrice());
        priceComponents.add(countPCRangePrice(productVO));
        priceComponents.add(countDesignPrice(productVO));

        for (BigDecimal price : priceComponents) {
            LOGGER.debug(appMessage.getAppMessage("info.price", new Object[] { price }));
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
        List<Long> designsIds = productVO.getProductDesignID();
        BigDecimal designPrice = new BigDecimal(0);
        if (designsIds == null || designsIds.isEmpty()) {
            return BigDecimal.ZERO;
        }
        List<ProductDesign> designs = repo.findAllById(designsIds);
        for (ProductDesign design : designs) {
            designPrice = designPrice.add(design.getPrice());
        }
        LOGGER.debug(appMessage.getAppMessage("info.price.design", new Object[] { designPrice }));
        return designPrice;
    }

    /**
     * Oblicza dodatkową cenę za zakres pomiarowy urządzeń z kategorii "PC".
     * 
     * @param productVO biekt z wartościami produktu
     * @return dodatek do ceny za zakres
     */
    private BigDecimal countPCRangePrice(ProductVO productVO) {
        BigDecimal rangeLow = productVO.getRangeLow();
        BigDecimal rangeHigh = productVO.getRangeHigh();
        if (productVO.getUnit() == null) {
            throw new ApplicationException(appMessage.getAppMessage("error.productVO.unit", null));
        }
        BigDecimal multiplicand = BigDecimal.valueOf(productVO.getUnit().getMultiplier());
        rangeLow = rangeLow.multiply(multiplicand);
        rangeHigh = rangeHigh.multiply(multiplicand);

        if ((rangeHigh.subtract(rangeLow)).abs().compareTo(BigDecimal.valueOf(10)) < 1) {
            return BigDecimal.valueOf(250);
        }
        if (rangeHigh.compareTo(BigDecimal.valueOf(6000)) > 0 || rangeLow.compareTo(BigDecimal.valueOf(6000)) > 0) {
            if (rangeHigh.compareTo(BigDecimal.valueOf(20000)) > 0 || rangeLow.compareTo(BigDecimal.valueOf(20000)) > 0) {
                return BigDecimal.valueOf(200);
            } else {
                return BigDecimal.valueOf(100);
            }
        } else {
            return BigDecimal.valueOf(0);
        }

    }

}
