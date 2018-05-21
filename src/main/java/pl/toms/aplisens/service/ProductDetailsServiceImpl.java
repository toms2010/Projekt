package pl.toms.aplisens.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import pl.toms.aplisens.domain.PCproductVO;
import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.domain.SGproductVO;
import pl.toms.aplisens.repository.CableTypeRepository;
import pl.toms.aplisens.util.AppMessage;
import pl.toms.aplisens.util.ApplicationException;
import pl.toms.aplisens.util.PresureUnits;

/**
 * Implementacja serwisu do zarządzania szczegółami produktów.
 */
@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailsServiceImpl.class);

    /** 
     * Okno formularza ze szczegółami dla przetworników ciśnienia. 
     */
    private static final String PC_DETAILS_WINDOW = "product-PC-details";
    /** 
     * Okno formularza ze szczegółami dla sond głębokości. 
     */
    private static final String SG_DETAILS_WINDOW = "product-SG-details";
    /** 
     * Okno formularza ze szczegółami dla pozostałych urządzeń. 
     */
    private static final String DEFAULT_DETAILS_WINDOW = "inProgres";
    /** 
     * Okno zwracane gdy nie przekazano identyfikatora produktu.
     */
    private static final String NO_ARG_WINDOW = "category-list";

    /**
     * Serwis do obsługi produktów.
     */
    @Autowired
    private ProductService productService;

    /**
     * Interfejs definiujący metody dostępu do typów kabli.
     */
    @Autowired
    private CableTypeRepository cableTypeRepo;
    
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
        if (productId != null) {
            Product product = productService.getProductById(productId);
            if (product == null) {
                throw new ApplicationException(appMessage.getAppMessage("error.product.load", null));
            }
            theModel.addAttribute("product", product);
            String category = product.getCategory().getCode();
            if (category == null) {
                throw new ApplicationException(appMessage.getAppMessage("error.product.loadCategory", null));
            }

            switch (category) {
            case "PC":
                theModel.addAttribute("units", PresureUnits.values());
                if (!theModel.containsAttribute("productVO"))
                    theModel.addAttribute("productVO", new PCproductVO());
                returnWindow = PC_DETAILS_WINDOW;
                break;
            case "SG":
                theModel.addAttribute("cableTypes", cableTypeRepo.findAll());
                if (!theModel.containsAttribute("productVO"))
                    theModel.addAttribute("productVO", new SGproductVO());
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
}
