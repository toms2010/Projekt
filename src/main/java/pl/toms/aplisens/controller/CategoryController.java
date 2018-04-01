package pl.toms.aplisens.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.toms.aplisens.domain.Category;
import pl.toms.aplisens.service.CategoryService;

/**
 * Kontroler zarządzający kategoriami
 */
@Controller
public class CategoryController {
	protected final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
	private static final String CATEGORY_LIST_WINDOW="category-list";
	private static final String CREATE_CATEGORY_WINDOW = "category-frm";

	@Autowired
	private CategoryService categoryService;

	/**
	 * Metoda zwracająca okno z listą kategorii produktów
	 * 
	 * @param theModel
	 * @return category-list.jsp
	 */
	@GetMapping(value = { "/", "/category" })
	public String getCategoryList(Model theModel) {
		List<Category> category = categoryService.getCategoryList();
		LOGGER.debug("Load Category list: category={}", category);
		theModel.addAttribute("categoryList", category);
		LOGGER.debug("Showing {}", CATEGORY_LIST_WINDOW);
		return CATEGORY_LIST_WINDOW;
	}

	/**
	 * Metoda zwracająca okno do dodawania nowej kategorii
	 * 
	 * @param theModel
	 * @return category-frm.jsp
	 */
	@GetMapping("adm/addCategory")
	public String addNewCategory(Model theModel) {
		Category category = new Category();
		LOGGER.debug("Create new Category: category={}", category);
		theModel.addAttribute("category", category);
		LOGGER.debug("Showing {}", CREATE_CATEGORY_WINDOW);
		return CREATE_CATEGORY_WINDOW;
	}

	/**
	 * Metoda zwracająca okno do edycji kategorii !!NIE DZIALA!!
	 * 
	 * @param theModel
	 * @return category-frm.jsp
	 */
	@PostMapping("adm/editCategory")
	public String editCategory(Model theModel, @RequestParam("categoryId") Long categoryId) {
		Category category = categoryService.getCategoryById(categoryId);
		LOGGER.debug("Editing Category: category={}", category);
		theModel.addAttribute("category", category);
		LOGGER.debug("Showing {}", CREATE_CATEGORY_WINDOW);
		return CREATE_CATEGORY_WINDOW;
	}
	
	/**
	 * Metoda zapisująca kategorie
	 * 
	 * @param category kategoria do zapisania
	 * @return category-list.jsp
	 */
	@PostMapping("adm/saveCategory")
	public String saveCategory(@ModelAttribute("category") Category category) {
		LOGGER.debug("Saving Category: category={}", category);
		categoryService.saveCategory(category);
		return "redirect:/category";
	}
}