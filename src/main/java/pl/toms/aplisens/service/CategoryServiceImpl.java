package pl.toms.aplisens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.toms.aplisens.domain.Category;
import pl.toms.aplisens.repository.CategoryRepository;
import pl.toms.aplisens.util.AppMessage;
import pl.toms.aplisens.util.ApplicationException;

/**
 * Implementacja serwisu do zarządzania kategoriami.
 *
 * @see Category
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryRepository repo;

    /**
     * Generator komunikatów aplikacji.
     */
    @Autowired
    private AppMessage appMessage;
    
    /**
     * {@inheritDoc}
     */
    public List<Category> getCategoryList() {
        return repo.findAll();
    }

    /**
     * {@inheritDoc}
     */
    public Category getCategoryById(Long categoryId) {
        if (categoryId == null) {
            throw new ApplicationException(appMessage.getAppMessage("error.categoryId.null", null));
        }
        return repo.findOneById(categoryId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void saveCategory(Category category) {
        if (category == null) {
            throw new ApplicationException(appMessage.getAppMessage("error.category.null", null));
        }
        repo.save(category);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteCategory(Long categoryId) {
        if (categoryId == null) {
            throw new ApplicationException(appMessage.getAppMessage("error.categoryId.null", null));
        }
        repo.deleteById(categoryId);
    }
}