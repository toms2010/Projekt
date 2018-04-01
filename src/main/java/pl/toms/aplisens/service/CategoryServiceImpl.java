package pl.toms.aplisens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.toms.aplisens.domain.Category;
import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.repository.CategoryRepository;

/**
 * Implementacja serwisu wewnętrznego do zarządzania kategoriami
 *
 * @see Product
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CategoryServiceImpl implements CategoryService{
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
		return repo.getOne(categoryId);
	}
}
//NPE