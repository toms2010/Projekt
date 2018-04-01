package pl.toms.aplisens.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.toms.aplisens.domain.Product;

/**
 * Serwis wewnętrzny do zarządzania produktami
 *
 * @see Product
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public interface ProductService {

    /**
     * Zwraca wszystkie produkty 
     *
     * @return lista produktów
     */
	List<Product> getAllProducts();

    /**
     * Zwraca wszystkie produkty z wybranej kategorii
     *
     * @param categoryId numer identyfikacyjny kategorii
     * @return lista produktów 
     */
	List<Product> getProductsByCategory(Long categoryId);
	
	/**
     * Zwraca produkt o podanym numerze identyfikacyjnym
     *
     * @param productId numer identyfikacyjny produktu
     * @return produkt
     */
	Product getProductById(Long productId);
}
