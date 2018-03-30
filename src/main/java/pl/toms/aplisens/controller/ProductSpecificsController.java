package pl.toms.aplisens.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.service.ProductService;

/**
 * Kontroler zarządzający specyfikacją produktu
 */
@Controller
public class ProductSpecificsController
{
    @Autowired
    private ProductService productService;
    
    /**
     *
     * @param 
     * @return 
     */
    @RequestMapping("/product-details")
    public String lista(@RequestParam("productId") Long productId, Model theModel)
    {
        Product productDetails = productService.getProduct(productId);
        theModel.addAttribute("productDetails", productDetails);
        return "product-details";
    }

}