package pl.toms.aplisens.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.service.ProductService;

/**
 * Kontroler zarządzający produktami
 */
@Controller
public class ProductController {
	private static final String PRODUCT_LIST_WINDOW = "product-list";
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;

	/**
    * Zwraca stronę z listą produktów o podanej kategorii
    * 
    * @param theModel
    * @param categoryId identyfikator grupy produktów
    * @return products.jsp
    */
	@PostMapping("/products")
	public String getProductList(Model theModel, @RequestParam("categoryId") Long categoryId) {
		List<Product> products = productService.getProductsByCategory(categoryId);
		LOGGER.debug("Load product list: products={}", products);
		theModel.addAttribute("products",products);
		LOGGER.debug("Showing {}", PRODUCT_LIST_WINDOW);
		return PRODUCT_LIST_WINDOW;
	}
}
