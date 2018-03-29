package pl.toms.aplisens.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.toms.aplisens.domain.Category;

/**
 * Serwis wewnętrzny do zarządzania produktami
 *
 * @see Category
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public interface CategoryService {
	
    /**
     * Zwraca wszystkie kategorie
     *
     * @return lista ketegorii
     */
	List<Category> getCategoryList();

}
