package pl.toms.aplisens.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/product")
public class ProductController {
	
	@RequestMapping("/list")
	public String List(Model theModel) {
		return "tabela";
	}

	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}
}
