package pl.toms.aplisens.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.toms.aplisens.domain.Category;

/**
 * Serwis do zarządzania kategoriami.
 *
 * @see Category
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public interface CategoryService {

    /**
     * Zwraca wszystkie kategorie.
     *
     * @return lista kategorii
     */
    List<Category> getCategoryList();

    /**
     * Zwraca kategorie o podanym identyfikatorze.
     *
     * @return kategoria
     */
    Category getCategoryById(Long categoryId);

    /**
     * Zapisuje kategorie.
     *
     * @param category kategoria
     */
    void saveCategory(Category category);

    /**
     * Usuwa kategorie.
     *
     * @param categoryId identyfikator kategorii
     */
    void deleteCategory(Long categoryId);

}
