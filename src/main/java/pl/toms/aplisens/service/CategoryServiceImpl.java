package pl.toms.aplisens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.toms.aplisens.domain.Category;
import pl.toms.aplisens.repository.BaseRepository;
import pl.toms.aplisens.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired 
	private CategoryRepository repo;
	
	public List<Category> getCategoryList() {

		return repo.findAll();
	}
}
