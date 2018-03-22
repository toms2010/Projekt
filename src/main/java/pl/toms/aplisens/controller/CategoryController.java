package pl.toms.aplisens.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.toms.aplisens.domain.Category;
import pl.toms.aplisens.service.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/start")
	public String lista(Model theModel) {
		List<Category> categoryList = categoryService.getCategoryList();
		theModel.addAttribute("categoryList",categoryList);
		return "index";
	}

}
