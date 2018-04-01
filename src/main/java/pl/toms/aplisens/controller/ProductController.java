package pl.toms.aplisens.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.service.ProductService;

/**
 * Kontroler zarządzający produktami
 */
@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	/**
    * Zwraca stronę z listą produktów o podanej kategorii
    * 
    * @param model
    * @param categoryId identyfikator grupy produktów
    * @return products.jsp
    */
	@RequestMapping("/products")
	public String getProductList(Model theModel, @RequestParam("categoryId") Long categoryId) {
		List<Product> products = productService.getProductsByCategory(categoryId);
		theModel.addAttribute("products",products);
		return "product-list";
	}

}
