package pl.toms.aplisens.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.toms.aplisens.domain.Product;
import pl.toms.aplisens.repository.ProductRepository;


@Service
public class BaseServiceImpl implements BaseService{

	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Autowired 
//	protected ProductRepository repo;
//	
//	public List<Product> getProducts(){
//		
//		return repo.findAll();
//	}
}
