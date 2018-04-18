package pl.toms.aplisens.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.toms.aplisens.domain.ProductDesign;

/**
 * Interfejs definiujący metody dostępu do danych wykonań produktu.
 *
 * @see ProductDesign
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface ProductDesignRepository extends BaseRepository<ProductDesign, Long> {

}
