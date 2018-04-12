package pl.toms.aplisens.service;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import pl.toms.aplisens.domain.Category;
import pl.toms.aplisens.domain.Product;

/**
 * Serwis wewnętrzny do zarządzania kategoriami
 *
 * @see Category
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public interface ProductDetailsService
{
    HashMap<String, Object> displayDetailsForm(Long productId, Model theModel); 
    BigDecimal countPrice(Product product, HttpServletRequest request);
}
