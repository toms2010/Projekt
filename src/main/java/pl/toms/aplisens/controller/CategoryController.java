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
    *
    * @param 
    * @return 
    */
	@RequestMapping("/")
	public String lista(Model theModel) {
	    //logger
		List<Category> categoryList = categoryService.getCategoryList();
		theModel.addAttribute("categoryList",categoryList);
		return "index";
	}
}

// Spring Tool Suite 



// import javax.inject.Inject;
///**
// * Kontroler zarządzający grupami składników
// */
//@RestController
//@RequestMapping("/grupaskl/manage")
//public class GrupaSkladnikowManageController {
//    private static final LoggerInfo LOGGER = LoggerInfo.getInstance(GrupaSkladnikowManageController.class);
//
//    @Inject
//    private GrupaSkladnikowManageService manageService;
//
//    /**
//     * Tworzy nową grupę składników
//     *
//     * @param grupaSklDTO dane grupy składników do zapisu
//     * @return dane utworzonej grupy składników
//     */
//    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
//    public GrupaSklDTO create(@RequestBody final GrupaSklDTO grupaSklDTO) {
//        LOGGER.info(MessageFormat.format("Create new grupaSkladnikow from POST: grupaSklDTO={0}", grupaSklDTO));
//        return manageService.create(grupaSklDTO);
//    }