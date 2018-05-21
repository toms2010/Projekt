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
import pl.toms.aplisens.domain.PCproductVO;
import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.domain.ProductDesign;
import pl.toms.aplisens.domain.ProductVO;
import pl.toms.aplisens.domain.SGproductVO;
import pl.toms.aplisens.repository.CableTypeRepository;
import pl.toms.aplisens.repository.HousingRepository;
import pl.toms.aplisens.repository.ProductDesignRepository;
import pl.toms.aplisens.util.AppMessage;
import pl.toms.aplisens.util.ApplicationException;

/**
 * Implementacja serwisu do zarządzania podsumowaniem produktów.
 *
 */
@Service
public class SummaryServiceImpl implements SummaryService {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailsServiceImpl.class);
    
    /**
     * Interfejs definiujący metody dostępu do typów kabli.
     */
    @Autowired
    private CableTypeRepository cableTypeRepo;
    
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
     * Generator komunikatów aplikacji.
     */
    @Autowired
    private AppMessage appMessage;
    
    /**
     * {@inheritDoc}
     * 
     */
    public BigDecimal createSummary(PCproductVO productVO) {
        if (productVO == null) {
            throw new ApplicationException(appMessage.getAppMessage("error.productVO.null", null));
        }
        BigDecimal finalPrice = countProductPrice(productVO);
        generateProductOrderCode(productVO);
        return finalPrice;
    }

    /**
     * {@inheritDoc}
     * 
     */
    public BigDecimal createSummary(SGproductVO productVO, Model theModel)
    {
        if (productVO == null) {
            throw new ApplicationException(appMessage.getAppMessage("error.productVO.null", null));
        }
        BigDecimal finalPrice = countProductPrice(productVO, theModel);
        generateProductOrderCode(productVO);
        return finalPrice;
    }

    /**
     * Tworzy kod zamówieniowy produktu dla produktów z grupy "PC".
     * 
     * @param productVO obiekt z wartościami produktu
     */
    private void generateProductOrderCode(PCproductVO productVO)
    {
        StringBuilder orderCode = new StringBuilder()
            .append(productVO.getCode()).append("/")
            .append(productVO.getHousingCode()).append("/")
            .append(productVO.getOrderCode())
            .append(productVO.getRangeLow()).append("..")
            .append(productVO.getRangeHigh()).append(productVO.getUnit().name());
        productVO.setOrderCode(orderCode.toString());
    }
    
    /**
     * Tworzy kod zamówieniowy produktu dla produktów z grupy "SG".
     * 
     * @param productVO obiekt z wartościami produktu
     */
    private void generateProductOrderCode(SGproductVO productVO)
    {
        Long cableId = productVO.getCableType();
        if (cableId == null)
            throw new ApplicationException(appMessage.getAppMessage("error.productVO.cableType", null));
        
        StringBuilder orderCode = new StringBuilder()
                .append(productVO.getCode()).append("/")
                .append(productVO.getOrderCode())
                .append(productVO.getRangeHigh()).append(" mH2O/L=")
                .append(productVO.getLenght()).append("/")
                .append(cableTypeRepo.findOneById(cableId).getName());
        productVO.setOrderCode(orderCode.toString());
        LOGGER.debug(appMessage.getAppMessage("info.orderCode", new Object[] {orderCode}));
    }
    
    private BigDecimal countProductPrice(PCproductVO productVO)
    {
        Map<String, BigDecimal> rangeExtraPrice = countPCRangePrice(productVO);
        Map<String, BigDecimal> housingExtraPrice = countPCHousingPrice(productVO);
        BigDecimal finalPrice = countFinalPrice(productVO, rangeExtraPrice, housingExtraPrice);
        return finalPrice;
    }
    
    private BigDecimal countProductPrice(SGproductVO productVO, Model theModel)
    {
        Map<String, BigDecimal> rangeExtraPrice = countSGRangePrice(productVO);
        Map<String, BigDecimal> cableExtraPrice = countSGCablePrice(productVO, theModel);
        BigDecimal finalPrice = countFinalPrice(productVO, rangeExtraPrice, cableExtraPrice);
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
    private Map<String, BigDecimal> countPCRangePrice(PCproductVO productVO) {
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
    private Map<String, BigDecimal> countSGRangePrice(SGproductVO productVO) {
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
    private Map<String, BigDecimal> countPCHousingPrice(PCproductVO productVO)
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
    private Map<String, BigDecimal> countSGCablePrice(SGproductVO productVO, Model theModel) {
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
        Map<String, BigDecimal> result= new HashMap<>();
        result.put("cable_price", cableTypePrice.multiply(BigDecimal.valueOf(lenght)));
        return result;
    }
}
