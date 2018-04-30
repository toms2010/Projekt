package pl.toms.aplisens.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import pl.toms.aplisens.domain.CableType;
import pl.toms.aplisens.domain.Housing;
import pl.toms.aplisens.domain.PCcategoryVO;
import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.domain.ProductDesign;
import pl.toms.aplisens.domain.ProductVO;
import pl.toms.aplisens.domain.SGcategoryVO;
import pl.toms.aplisens.repository.CableTypeRepository;
import pl.toms.aplisens.repository.HousingRepository;
import pl.toms.aplisens.repository.ProductDesignRepository;
import pl.toms.aplisens.util.AppMessage;
import pl.toms.aplisens.util.ApplicationException;
import pl.toms.aplisens.util.PresureUnits;

/**
 * Implementacja serwisu do zarządzania szczegółami produktów.
 */
@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailsServiceImpl.class);

    /** Okno formularza ze szczegółami dla przetworników ciśnienia. */
    private static final String PC_DETAILS_WINDOW = "product-PC-details";

    /** Okno formularza ze szczegółami dla sond głębokości. */
    private static final String SG_DETAILS_WINDOW = "product-SG-details";

    /** Okno formularza ze szczegółami dla pozostałych urządzeń. */
    private static final String DEFAULT_DETAILS_WINDOW = "inProgres";

    /** Okno zwracane gdy nie przekazano identyfikatora produktu. */
    private static final String NO_ARG_WINDOW = "category-list";

    /**
     * Serwis do obsługi produktów.
     */
    @Autowired
    private ProductService productService;

    /**
     * Interfejs definiujący metody dostępu do danych wykonań produktu.
     */
    @Autowired
    private ProductDesignRepository productDesignRepo;
    
    /**
     * Interfejs definiujący metody dostępu do danych typów obudów.
     */
    @Autowired
    private HousingRepository housingRepo;
    
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
                    theModel.addAttribute("productVO", new PCcategoryVO());
                returnWindow = PC_DETAILS_WINDOW;
                break;
            case "SG":
                theModel.addAttribute("cableTypes", cableTypeRepo.findAll());
                if (!theModel.containsAttribute("productVO"))
                    theModel.addAttribute("productVO", new SGcategoryVO());
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
    public BigDecimal createOrderPC(PCcategoryVO productVO) {
        if (productVO == null) {
            throw new ApplicationException(appMessage.getAppMessage("error.productVO.null", null));
        }
        Map<String, BigDecimal> rangeExtraPrice = countPCRangePrice(productVO);
        Map<String, BigDecimal> housingExtraPrice = countPCHousingPrice(productVO);
        BigDecimal finalPrice = countFinalPrice(productVO, rangeExtraPrice, housingExtraPrice);
        
        StringBuilder orderCode = new StringBuilder()
            .append(productVO.getCode()).append("/")
            .append(productVO.getHousingCode()).append("/")
            .append(productVO.getOrderCode())
            .append(productVO.getRangeLow()).append("..")
            .append(productVO.getRangeHigh()).append(productVO.getUnit().name());
    productVO.setOrderCode(orderCode.toString());
        
        return finalPrice;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    public BigDecimal createOrderSG(SGcategoryVO productVO, Model theModel)
    {
        if (productVO == null) {
            throw new ApplicationException(appMessage.getAppMessage("error.productVO.null", null));
        }
        Map<String, BigDecimal> rangeExtraPrice = countSGRangePrice(productVO);
        Map<String, BigDecimal> cableExtraPrice = countSGCablePrice(productVO, theModel);
        BigDecimal finalPrice = countFinalPrice(productVO, rangeExtraPrice, cableExtraPrice);
        
        StringBuilder orderCode = new StringBuilder()
                .append(productVO.getCode()).append("/")
                .append(productVO.getOrderCode())
                .append(productVO.getRangeHigh()).append(" mH2O/L=")
                .append(productVO.getLenght()).append("/")
                .append(productVO.getCableCode());
        productVO.setOrderCode(orderCode.toString());
        LOGGER.debug(appMessage.getAppMessage("info.orderCode", new Object[] {orderCode}));
     
        return finalPrice;
    }

    /**
     * Oblicza finalną cenę produktu sumując:
     * <pre>
     * - cena podstawowa produktu
     * - cena za wybrane wykonania
     * - przekazane dodatkowe składniki ceny
     * <pre>
     * @param productVO biekt z wartościami produktu
     * @param addictionalPrice dodatkowy składnik ceny
     * @return końcowa cena produktu
     */
    @SafeVarargs
    private final BigDecimal countFinalPrice(ProductVO productVO, Map<String, BigDecimal>...addictionalPrice) {
        BigDecimal finalPrice = BigDecimal.valueOf(0);
        Map<String, BigDecimal> priceComponents = new HashMap<>();
        
        priceComponents.put("basic_price", productVO.getPrice());
        priceComponents.putAll(countDesignPrice(productVO));
        for(int i=0; i<addictionalPrice.length; i++) {
            priceComponents.putAll(addictionalPrice[i]);
        }
        LOGGER.debug(appMessage.getAppMessage("info.price", new Object[] { priceComponents }));
        for (BigDecimal price : priceComponents.values()) {
            finalPrice = finalPrice.add(price);
        }
        return finalPrice;
    }
    
    /**
     * Oblicza dodatek do ceny za dodatkowe wykonania produktu. Dodaje kod wykonań do ProductVO.
     * 
     * @param productVO biekt z wartościami produktu
     * @return dodatek do ceny za wykonania
     */
    private Map<String, BigDecimal> countDesignPrice(ProductVO productVO) {
        Map<String, BigDecimal> result= new HashMap<>();
        List<Long> designsIds = productVO.getProductDesignID();
        BigDecimal designPrice = new BigDecimal(0);
        if (designsIds == null || designsIds.isEmpty()) {
            result.put("design_price", BigDecimal.ZERO);
        }
        StringBuilder designsCode = new StringBuilder();
        List<ProductDesign> designs = productDesignRepo.findAllById(designsIds);
        for (ProductDesign design : designs) {
            designPrice = designPrice.add(design.getPrice());
            designsCode.append(design.getName()).append("/");
        }
        productVO.setOrderCode(designsCode.toString());
        result.put("design_price", designPrice);
        return result;
    }

    /**
     * Oblicza dodatkową cenę za zakres pomiarowy urządzeń z kategorii "PC".
     * 
     * @param productVO biekt z wartościami produktu
     * @return dodatek do ceny za zakres
     */
    private Map<String, BigDecimal> countPCRangePrice(PCcategoryVO productVO) {
        Map<String, BigDecimal> result= new HashMap<>();
        BigDecimal rangeLow = productVO.getRangeLow();
        BigDecimal rangeHigh = productVO.getRangeHigh();
        if (productVO.getUnit() == null) {
            throw new ApplicationException(appMessage.getAppMessage("error.productVO.unit", null));
        }
        BigDecimal multiplicand = BigDecimal.valueOf(productVO.getUnit().getMultiplier());
        rangeLow = rangeLow.multiply(multiplicand);
        rangeHigh = rangeHigh.multiply(multiplicand);

        if ((rangeHigh.subtract(rangeLow)).abs().compareTo(BigDecimal.valueOf(10)) < 1) {
            result.put("range_price", BigDecimal.valueOf(250));
        }
        if (rangeHigh.compareTo(BigDecimal.valueOf(6000)) > 0 || rangeLow.compareTo(BigDecimal.valueOf(6000)) > 0) {
            if (rangeHigh.compareTo(BigDecimal.valueOf(20000)) > 0 || rangeLow.compareTo(BigDecimal.valueOf(20000)) > 0) {
                result.put("range_price", BigDecimal.valueOf(200));
            } else {
                result.put("range_price", BigDecimal.valueOf(100));
            }
        } else {
            result.put("range_price", BigDecimal.valueOf(0));
        }
        return result;
    }
    
    /**
     * Oblicza dodatkową cenę za zakres pomiarowy urządzeń z kategorii "SG".
     * 
     * @param productVO biekt z wartościami produktu
     * @return dodatek do ceny za zakres
     */
    private Map<String, BigDecimal> countSGRangePrice(SGcategoryVO productVO) {
        Map<String, BigDecimal> result= new HashMap<>();
        BigDecimal rangeHigh = productVO.getRangeHigh();
        if (rangeHigh == null ) {
            //TODO 
            throw new ApplicationException();
        }
        if (rangeHigh.compareTo(BigDecimal.valueOf(100)) > 0) {
            result.put("range_price", BigDecimal.valueOf(300));
        }
        else if (rangeHigh.compareTo(BigDecimal.valueOf(10)) > 0){
            result.put("range_price", BigDecimal.valueOf(100));
        }
        else {
            result.put("range_price", BigDecimal.valueOf(0));
        }
        return result;
    }
    
    /**
     * Oblicza dodatkową cenę za obudowę.
     * @param productVO biekt z wartościami produktu
     * @return dodatek do ceny za obudowę
     */
    private Map<String, BigDecimal> countPCHousingPrice(PCcategoryVO productVO)
    {
        Map<String, BigDecimal> result= new HashMap<>();
        Housing housing = housingRepo.findOneById(productVO.getHousingId());
        if (housing == null) {
            throw new ApplicationException(appMessage.getAppMessage("error.productVO.housing", null));
        }
        productVO.setHousingCode(housing.getCode());
        result.put("housing_price", housing.getPrice());
        return result;
    }
    
    /**
     * Oblicza dodatkową cenę za kabel.
     * 
     * @param productVO biekt z wartościami produktu
     * @param theModel model
     * @return dodatek do ceny za kabel
     */
    private Map<String, BigDecimal> countSGCablePrice(SGcategoryVO productVO, Model theModel) {
        Long lenght = productVO.getLenght();
        BigDecimal range = productVO.getRangeHigh();
        if (lenght == null || range == null) {
            //TODO
            throw new ApplicationException();
        }
        if (range.compareTo(BigDecimal.valueOf(lenght)) > 0 ) {
            lenght = range.longValue() +2;
            productVO.setLenght(lenght);
            String message = appMessage.getAppMessage("info.cableLenght", new Object[] {lenght, range, lenght});
            LOGGER.debug(message);
            theModel.addAttribute("lenghtInfo", message);
        }
        
//        if (productVO.getCableType() == null) {
//            throw new ApplicationException(appMessage.getAppMessage("error.productVO.cableType", null));
//        }
        CableType cable = cableTypeRepo.findOneById(productVO.getCableType());
        if (cable == null) {
            throw new ApplicationException(appMessage.getAppMessage("error.productVO.cableType", null));
        }
        BigDecimal cableTypePrice = cable.getPrice();
        productVO.setCableCode(cable.getName());
        Map<String, BigDecimal> result= new HashMap<>();
        result.put("cable_price", cableTypePrice.multiply(BigDecimal.valueOf(lenght)));
        return result;
    }
}
