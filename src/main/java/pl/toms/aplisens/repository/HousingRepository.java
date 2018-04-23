package pl.toms.aplisens.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.toms.aplisens.domain.Housing;

/**
 * Interfejs definiujący metody dostępu do danych wykonań produktu.
 *
 * @see Housing
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface HousingRepository extends BaseRepository<Housing, Long>{

}


