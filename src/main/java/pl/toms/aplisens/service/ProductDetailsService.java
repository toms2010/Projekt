package pl.toms.aplisens.service;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.toms.aplisens.domain.Category;
import pl.toms.aplisens.domain.Product;

/**
 * Serwis wewnętrzny do zarządzania kategoriami
 *
 * @see Category
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public interface ProductDetailsService
{
    
    public enum PresureUnits
    {
         kPa(1), Pa(1000), mPa(0.001), bar(0.01);

        private double multiplier;

        PresureUnits(double multiplier)
        {
            this.multiplier = multiplier;
        }

        public double getMultiplier()
        {
            return multiplier;
        }
    }
    
    public enum SpecialCategory
    {
         SG, PC, CT
    }

    BigDecimal countPrice(Product product, HttpServletRequest request);

}
