package pl.toms.aplisens.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.toms.aplisens.util.AppMessage;

/**
 * Kontroler do obsługi błędów.
 */
@Controller
public class ErrorController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    
    /**
     * Generator komunikatów aplikacji.
     */
    @Autowired
    private AppMessage appMessage;
    
//    /**
//     * Metoda obsługująca błąd HTTP 400.
//     * 
//     * @return okno startowe
//     */
//    @GetMapping("/error400")
//    public String getWindowFor400Error(Model theModel) {
//        LOGGER.debug(appMessage.getAppMessage("error.badRequest.400", null));
//        return "redirect:/category";
//    }
}