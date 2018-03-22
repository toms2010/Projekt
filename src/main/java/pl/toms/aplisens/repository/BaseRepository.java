package pl.toms.aplisens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import pl.toms.aplisens.domain.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>{
	
	/**
	 * Wyszukuje encje po nazwie
	 * 
	 * @param name nazwa encji do wyszukania
	 * @return encja o podanej nazwie
	 */
	BaseEntity findOneByName(String name);

}
