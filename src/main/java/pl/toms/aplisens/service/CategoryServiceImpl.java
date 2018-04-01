package pl.toms.aplisens.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	protected final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired 
	private CategoryRepository repo;
	
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
		repo.save(category);
	}

    /**
     * {@inheritDoc}
     */
	public Category getCategoryById(Long categoryId) {
		Category category =repo.getOne(categoryId);
		LOGGER.debug("Load category : category={}", category);
		return category;
	}

    /**
     * {@inheritDoc}
     */
	public void deleteCategory(Long categoryId) {
		repo.deleteById(categoryId);
	}
}
//NPE