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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.toms.aplisens.domain.Category;
import pl.toms.aplisens.service.CategoryService;
import pl.toms.aplisens.util.AppMessage;

/**
 * Kontroler zarządzający kategoriami.
 */
@Controller
public class CategoryController {
    private static final String CATEGORY_LIST_WINDOW = "category-list";
    private static final String CREATE_CATEGORY_WINDOW = "category-frm";

    protected static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    /**
     * Generator komunikatów aplikacji
     */
    @Autowired
    private AppMessage appMessage;

    /**
     * Metoda zwracająca okno z listą kategorii produktów
     * 
     * @param theModel
     * @return okno z listą kategorii: CATEGORY_LIST_WINDOW
     */
    @GetMapping(value = { "/", "/category" })
    public String getCategoryList(Model theModel) {
        List<Category> categories = categoryService.getCategoryList();
        theModel.addAttribute("categoryList", categories);
        LOGGER.debug(appMessage.getAppMessage("info.showing", new Object[] { CATEGORY_LIST_WINDOW, categories }));
        return CATEGORY_LIST_WINDOW;
    }

    /**
     * Metoda zwracająca okno do edycji lub dodawania nowej kategorii
     * 
     * @param theModel
     * @param categoryId identyfikator kategorii (dla edycji), przy tworzeniu nie wymagany
     * @return CREATE_CATEGORY_WINDOW
     */
    @RequestMapping(value = {"adm/editCategory", "adm/addCategory"},  method= {RequestMethod.POST, RequestMethod.GET})
    public String editCategory(Model theModel, @RequestParam(name="categoryId", required=false ) Long categoryId) {
        Category category;
        if (categoryId == null) {
             category = new Category();
        }
        else {
            category = categoryService.getCategoryById(categoryId);
        }
        theModel.addAttribute("category", category);
        LOGGER.debug(appMessage.getAppMessage("info.showing", new Object[] { CREATE_CATEGORY_WINDOW, category }));
        return CREATE_CATEGORY_WINDOW;
    }

    /**
     * Metoda do usuwania kategorii
     * 
     * @param theModel
     * @return wraca do okna kategorii
     */
    @PostMapping("/adm/deleteCategory")
    public String deleteCategory(Model theModel, @RequestParam("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        LOGGER.debug(appMessage.getAppMessage("info.delete", new Object[] {categoryId}));
        return "redirect:/category";
    }

    /**
     * Metoda zapisująca kategorie
     * 
     * @param theModel
     * @param category kategoria do zapisania
     * @return wraca do okna kategorii
     */
    @PostMapping("adm/saveCategory")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        LOGGER.debug(appMessage.getAppMessage("info.save", new Object[] {category}));
        return "redirect:/category";
    }
    
  /**
  * Metoda obsługująca błąd HTTP 400
  * 
  * @return okno startowe
  */
 @GetMapping("/error400")
 public String getWindowFor400Error(Model theModel) {
     LOGGER.debug(appMessage.getAppMessage("error.badRequest.400", null));
     return "redirect:/category";
 }
}