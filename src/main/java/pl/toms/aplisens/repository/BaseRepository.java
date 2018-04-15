package pl.toms.aplisens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import pl.toms.aplisens.domain.BaseEntity;

/**
 * Interfejs definiujący metody dostępu do encji
 *
 * @param <T> typ encji
 * @param <ID> typ identyfikatora bazodanowego encji
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {

    /**
     * Wyszukuje encje po nazwie
     * 
     * @param name nazwa encji do wyszukania
     * @return encja o podanej nazwie
     */
    T findOneByName(String name);

    /* Wyszukuje encje po identyfikatorze
     * @param name identyfikator encji do wyszukania
     * @return encja o podanym identyfikatore 
     * */
    T findOneById(Long id);
}
