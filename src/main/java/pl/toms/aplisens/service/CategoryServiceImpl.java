package pl.toms.aplisens.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.toms.aplisens.domain.Category;
import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.repository.CategoryRepository;

/**
 * Implementacja serwisu wewnętrznego do zarządzania kategoriami
 *
 * @see Product
 */
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired 
	private CategoryRepository repo;
	@Autowired 
	private EntityManager em;
	
    /**
     * {@inheritDoc}
     */
	public List<Category> getCategoryList() {
		return repo.findAll();
	}

    /**
     * {@inheritDoc}
     */
	public void saveCategory(Category category) {
        if (category == null) {
            throw new RuntimeException("Category is null ");
        }
		em.merge(category);
//		repo.save(category);
	}

    /**
     * {@inheritDoc}
     */
	public Category getCategoryById(Long categoryId) {
        if (categoryId == null) {
            throw new RuntimeException("CategoryId is null ");
        }
		return repo.findOneById(categoryId);
	}

    /**
     * {@inheritDoc}
     */
	public void deleteCategory(Long categoryId) {
        if (categoryId == null) {
            throw new RuntimeException("CategoryId is null ");
        }
		repo.deleteById(categoryId);
	}
}