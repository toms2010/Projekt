package pl.toms.aplisens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.repository.ProductRepository;

/**
 * Implementacja serwisu wewnętrznego do zarządzania produktami
 *
 * @see Product
 */
@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository repo;

    /**
     * {@inheritDoc}
     */
    public List<Product> getProducts(){
        return repo.findAll();
    }
    
    /**
     * {@inheritDoc}
     */
    public Product getProduct(Long productId){
        return repo.getOne(productId);
    }
    

}
