package pl.toms.aplisens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.repository.CategoryRepository;
import pl.toms.aplisens.repository.ProductRepository;

/**
 * Implementacja serwisu wewnętrznego do zarządzania produktami
 *
 * @see Product
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository repo;
	@Autowired
	private CategoryRepository repoCategory;

	/**
	 * {@inheritDoc}
	 */
	public List<Product> getAllProducts() {
		return repo.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Product> getProductsByCategory(Long categoryId) {
        if (categoryId == null) {
            throw new RuntimeException("CategoryId is null ");
        }
		return repo.findAllByCategory(repoCategory.getOne(categoryId));
	}

	/**
	 * {@inheritDoc}
	 */
	public Product getProductById(Long productId) {
        if (productId == null) {
            throw new RuntimeException("ProductId is null ");
        }
		return repo.findOneById(productId);
	}

}
