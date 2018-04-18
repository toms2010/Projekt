package pl.toms.aplisens.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.toms.aplisens.domain.Category;

/**
 * Interfejs definiujący metody dostępu do danych kategorii.
 *
 * @see Category
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface CategoryRepository extends BaseRepository<Category, Long> {

}
