package pl.toms.aplisens.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.toms.aplisens.domain.CableType;

/**
 * Interfejs definiujący metody dostępu do typów kabli.
 *
 * @see CableType
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface CableTypeRepository extends BaseRepository<CableType, Long> {

}
