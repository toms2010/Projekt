package pl.toms.aplisens.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService baseService;

	@RequestMapping("/list")
	public String lista(Model theModel) {
		List<Product> products = baseService.getProducts();
		
		theModel.addAttribute("product",products);
		return "products";
	}

}
