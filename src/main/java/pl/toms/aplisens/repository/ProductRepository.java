package pl.toms.aplisens.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.toms.aplisens.domain.Category;
import pl.toms.aplisens.domain.Product;


/**
 * Interfejs definiujący metody dostępu do danych produktów
 *
 * @see Product
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface ProductRepository extends BaseRepository<Product, Long>{

	List<Product> findAllByCategory(Category category);
}
