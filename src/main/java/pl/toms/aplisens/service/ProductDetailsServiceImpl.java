package pl.toms.aplisens.service;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import pl.toms.aplisens.domain.Product;

public class ProductDetailsServiceImpl implements ProductDetailsService{

    /**
     * {@inheritDoc}
     * @return 
     */
	public BigDecimal countPrice(Product product, HttpServletRequest request) {
		if ("SG".equals(product.getCategory().getTag())) {
			return null;
		}
		else if ("PC".equals(product.getCategory().getName())){
			return countPC(product, request);
		}
		else {
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
	private BigDecimal countPC(Product product, HttpServletRequest request) {
	    if (request == null) {
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
	private BigDecimal countRangePrice(float rangeLow,float rangeHigh,final String unit) {
	    if (!"kPa".equals(unit)) {
	        if("Pa".equals(unit)) {
	            rangeLow/=1000;
	            rangeHigh/=1000;
	        }
	        else
	        {
	            rangeLow*=1000;
	            rangeHigh*=1000;
	        }
	    }
		if ((Math.abs(rangeHigh-rangeLow))<=10)
		    return BigDecimal.valueOf(250);
		if (rangeHigh>6000 || rangeLow>6000) {
			if (rangeHigh > 20000|| rangeLow>20000) {
				return BigDecimal.valueOf(200);
			} else
				return BigDecimal.valueOf(100);
		} else
			return BigDecimal.valueOf(0);
	}
}



