package pl.toms.aplisens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.repository.CategoryRepository;
import pl.toms.aplisens.repository.ProductRepository;
import pl.toms.aplisens.util.AppMessage;
import pl.toms.aplisens.util.ApplicationException;

/**
 * Implementacja serwisu do zarządzania produktami
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
     * Generator komunikatów aplikacji
     */
    @Autowired
    private AppMessage appMessage;

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
            throw new ApplicationException(appMessage.getAppMessage("error.categoryId.null", null));
        }
        return repo.findAllByCategory(repoCategory.getOne(categoryId));
    }

    /**
     * {@inheritDoc}
     */
    public Product getProductById(Long productId) {
        if (productId == null) {
            throw new ApplicationException(appMessage.getAppMessage("error.productId.null", null));
        }
        return repo.findOneById(productId);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteProduct(Long productId) {
        if (productId == null) {
            throw new ApplicationException(appMessage.getAppMessage("error.productId.null", null));
        }
        repo.deleteById(productId);
    }

    /**
     * {@inheritDoc}
     */
    public void saveProduct(Product product) {
        if (product == null) {
            throw new ApplicationException(appMessage.getAppMessage("error.product.null", null));
        }
        repo.save(product);
    }

}
