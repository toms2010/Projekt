package pl.toms.aplisens.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import pl.toms.aplisens.domain.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>{
	
	BaseEntity findOneByName(String name);
}
