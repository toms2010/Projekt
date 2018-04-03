package pl.toms.aplisens.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.service.ProductDetailsService;
import pl.toms.aplisens.service.ProductService;

/**
 * Kontroler zarządzający szczegółami produktu
 */
@Controller
public class ProductDetailsController {
	private static final String DETAILS_WINDOW = "product-details";

	protected static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailsController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductDetailsService productDetailsService;

	/**
	 * Zwraca stronę ze szczegółami produktu
	 * 
	 * @param theModel
	 * @param productId
	 *            identyfikator produktu
	 * @return product-details.jsp
	 */
	@RequestMapping("/details")
	public String getProductDetails(@RequestParam("productId") Long productId, Model theModel) {
		Product product = productService.getProductById(productId);
		LOGGER.debug("Load product : product={}", product);
		theModel.addAttribute("product", product);
		LOGGER.debug("Showing {}", DETAILS_WINDOW);
		return DETAILS_WINDOW;
	}

	/**
	 * Oblicza cenę produktu
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping("/saveProductVO")
	public String getPrice(@RequestParam("productId") Long productId, Model theModel, HttpServletRequest request) {
		Product product = productService.getProductById(productId);
		LOGGER.debug("Load product : product={}", product);
		BigDecimal price = productDetailsService.countPrice(product, request);
		LOGGER.debug("Showing {} with price: {}", "test", price);
		return "test";
	}
}