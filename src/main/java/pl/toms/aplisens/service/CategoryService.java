package pl.toms.aplisens.service;

import java.util.List;

import pl.toms.aplisens.domain.Category;

/**
 * Serwis wewnętrzny do zarządzania produktami
 *
 * @see Category
 */
public interface CategoryService {
	
    /**
     * Zwraca wszystkie kategorie
     *
     * @return lista ketegorii
     */
	List<Category> getCategoryList();

}
