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
			return countSG(product, request);
		}
		else if ("PC".equals(product.getCategory().getName())){
			return countPC(product, request);
		}
		else {
			return null;
		}
	}
	
	
	private BigDecimal countPC(Product product, HttpServletRequest request) {
		Float rangeLow = (Float) request.getAttribute("rangeLow");
		Float rangeHigh = (Float) request.getAttribute("rangeHigh");

		return rangePrice(rangeLow, rangeHigh);
	}
	
	private BigDecimal countSG(Product product, HttpServletRequest request) {
		return null;
	}
	
	private BigDecimal rangePrice(BigDecimal rangeLow,BigDecimal rangeHigh) {
		if ((Math.abs(rangeHigh-rangeLow))<=10) return 250;
		if (rangeHigh>6000 || rangeLow>6000) {
			if (rangeHigh > 20000|| rangeLow>20000) {
				return 200;
			} else
				return 100;
		} else
			return 0;
		
	}

}



