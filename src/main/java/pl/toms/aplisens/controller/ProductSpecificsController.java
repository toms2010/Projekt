package pl.toms.aplisens.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductSpecificsController {

	@RequestMapping("/product-specifics")
	public String lista(Model theModel) {
		return "product-form";
	}

}
