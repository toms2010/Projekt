package pl.toms.aplisens.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.toms.aplisens.domain.Category;
import pl.toms.aplisens.service.CategoryService;

/**
 * Kontroler zarządzający kategoriami
 */
@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	/**
    * Metoda zwracająca pierwsze okno z listą kategorii produktów
    * 
    * @param Model 
    * @return first-list.jsp
    */
	@RequestMapping(value= {"/", "/category"})
	public String getFirstWindow(Model theModel) {
	    //logger
		List<Category> categoryList = categoryService.getCategoryList();
		theModel.addAttribute("categoryList",categoryList);
		return "first-list";
	}
}