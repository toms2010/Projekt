package pl.toms.aplisens.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    /** Okno z listą kategorii. */
    private static final String CATEGORY_LIST_WINDOW = "category-list";
    /** Okno z formularzem do edycji/dodawania kategorii. */
    private static final String CREATE_CATEGORY_WINDOW = "category-frm";

    protected static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    /**
     * Serwis do obsługi kategorii.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Generator komunikatów aplikacji.
     */
    @Autowired
    private AppMessage appMessage;

    /**
     * Metoda zwracająca okno z listą kategorii produktów.
     * 
     * @param theModel model
     * @return okno z listą kategorii:  {@link #CATEGORY_LIST_WINDOW}
     */
    @GetMapping(value = { "/", "/category" })
    public String getCategoryList(Model theModel) {
        List<Category> categories = categoryService.getCategoryList();
        theModel.addAttribute("categoryList", categories);
        LOGGER.debug(appMessage.getAppMessage("info.showing", new Object[] { CATEGORY_LIST_WINDOW, categories }));
        return CATEGORY_LIST_WINDOW;
    }

    /**
     * Metoda zwracająca okno do edycji lub dodawania nowej kategorii.
     * 
     * @param theModel model
     * @param categoryId identyfikator kategorii (dla edycji), przy tworzeniu nie wymagany
     * @return okno z listą kategorii:  {@link #CREATE_CATEGORY_WINDOW}
     */
    @RequestMapping(value = { "adm/editCategory", "adm/addCategory" }, method = { RequestMethod.POST, RequestMethod.GET })
    public String editCategory(Model theModel, @RequestParam(name = "categoryId", required = false) Long categoryId) {
        Category category;
        if (categoryId == null) {
            category = new Category();
        } else {
            category = categoryService.getCategoryById(categoryId);
        }
        theModel.addAttribute("category", category);
        LOGGER.debug(appMessage.getAppMessage("info.showing", new Object[] { CREATE_CATEGORY_WINDOW, category }));
        return CREATE_CATEGORY_WINDOW;
    }

    /**
     * Metoda do usuwania kategorii.
     * 
     * @param theModel model
     * @return wraca do okna kategorii {@link #getCategoryList}
     */
    @PostMapping("/adm/deleteCategory")
    public String deleteCategory(Model theModel, @RequestParam("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        LOGGER.debug(appMessage.getAppMessage("info.delete", new Object[] { categoryId }));
        return "redirect:/category";
    }

    /**
     * Metoda zapisująca kategorie.
     * 
     * @param theModel model
     * @param category kategoria do zapisania
     * @param bindingResult rezultat walidacji
     * @return wraca do okna kategorii {@link #getCategoryList}
     */
    @PostMapping(value = { "adm/saveCategory"})
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, Model theModel) {
        if (bindingResult.hasErrors()) {
            LOGGER.debug("Błąd walidacji, dokładna ilośc błędów: {}", bindingResult.getFieldErrorCount());
            theModel.addAttribute("category", category);
            return CREATE_CATEGORY_WINDOW;
        } else {
            categoryService.saveCategory(category);
            LOGGER.debug(appMessage.getAppMessage("info.save", new Object[] { category }));
            return "redirect:/category";
        }
    }
}