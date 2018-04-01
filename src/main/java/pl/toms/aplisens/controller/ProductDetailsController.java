package pl.toms.aplisens.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.service.ProductService;

/**
 * Kontroler zarządzający szczegółami produktu
 */
@Controller
public class ProductDetailsController {
	private static final String DETAILS_WINDOW = "product-details";
	
	protected final Logger LOGGER = LoggerFactory.getLogger(ProductDetailsController.class);
	
	@Autowired
	private ProductService productService;

	/**
	 * Zwraca stronę ze szczegółami produktu
	 * 
    * @param theModel
    * @param productId identyfikator produktu
    * @return product-details.jsp
	 */
	@RequestMapping("/details")
	public String getProductDetails(@RequestParam("productId") Long productId, Model theModel) {
		Product product = productService.getProductById(productId);
		LOGGER.debug("Load product : product={}", product);
		theModel.addAttribute("productDetails", product);
		LOGGER.debug("Showing {}", DETAILS_WINDOW);
		return DETAILS_WINDOW;
	}

}