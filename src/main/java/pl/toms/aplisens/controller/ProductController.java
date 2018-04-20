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

import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.service.ProductService;
import pl.toms.aplisens.util.AppMessage;

/**
 * Kontroler zarządzający produktami.
 */
@Controller
public class ProductController {
    /** Okno z listą produktów. */
    private static final String PRODUCT_LIST_WINDOW = "product-list";

    protected static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    /**
     * Serwis do obsługi produktów.
     */
    @Autowired
    private ProductService productService;

    /**
     * Generator komunikatów aplikacji.
     */
    @Autowired
    private AppMessage appMessage;

    /**
     * Zwraca stronę z listą produktów o podanej kategorii.
     * 
     * @param theModel model
     * @param categoryId identyfikator grupy produktów
     * @return okno z listą produktów {@link #PRODUCT_LIST_WINDOW}
     */
    @RequestMapping(value = "/products", method = { RequestMethod.POST, RequestMethod.GET })
    public String getProductList(Model theModel, @RequestParam("categoryId") Long categoryId) {
        List<Product> products = productService.getProductsByCategory(categoryId);
        theModel.addAttribute("products", products);
        LOGGER.debug(appMessage.getAppMessage("info.showing", new Object[] { PRODUCT_LIST_WINDOW, products }));
        return PRODUCT_LIST_WINDOW;
    }

    /**
     * W BUDOWIE. Metoda zwracająca okno do dodawania nowego produktu.
     * 
     * @param theModel model
     * @return inProgres
     */
    @GetMapping("adm/addProduct")
    public String addNewProduct(Model theModel) {
        Product product = new Product();
        theModel.addAttribute("product", product);
        LOGGER.debug("W budowie");
        return "inProgres";
    }

    /**
     * W BUDOWIE Metoda zwracająca okno do edycji produktów.
     * 
     * @param theModel model
     * @param productId identyfikator produktu
     * @return inProgres
     */
    @PostMapping("adm/editProduct")
    public String editCategory(Model theModel, @RequestParam("productId") Long productId) {
        Product product = productService.getProductById(productId);
        theModel.addAttribute("product", product);
        LOGGER.debug("W budowie");
        return "inProgres";
    }

    /**
     * Metoda do usuwania produktów.
     * 
     * @param theModel model
     * @return wraca do okna produktów {@link #getProductList}
     */
    @PostMapping("/adm/deleteProduct")
    public String deleteProduct(Model theModel, @RequestParam("productId") Long productId) {
        productService.deleteProduct(productId);
        LOGGER.debug(appMessage.getAppMessage("info.delete", new Object[] { productId }));
        return "redirect:/products";
    }

    /**
     *  W BUDOWIE Metoda zapisująca produkt.
     * 
     * @param product produkt do zapisania
     * @return wraca do okna produktów {@link #getProductList}
     */
    @PostMapping("adm/saveProduct")
    public String saveCategory(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        LOGGER.debug(appMessage.getAppMessage("info.save", new Object[] { product }));
        return "redirect:/products";
    }
}
