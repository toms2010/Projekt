package pl.toms.aplisens.service;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import pl.toms.aplisens.domain.Product;

public interface ProductDetailsService {

	BigDecimal countPrice(Product product, HttpServletRequest request);


}
