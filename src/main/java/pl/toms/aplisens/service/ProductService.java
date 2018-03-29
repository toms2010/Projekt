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
	List<Product> getProducts();

	/**
     * Zwraca produkt o podanym numerze identyfikacyjnym
     *
     * @param productId numer identyfikacyjny
     * @return produkt
     */
	Product getProduct(Long productId);
}
