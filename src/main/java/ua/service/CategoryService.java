package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Category;

public interface CategoryService {

	//void save(String name);
	
	void save(Category category);
	
	Category findByName(String name);
	
	public Category findOne(int id);
	
	public Page<Category> findAll(Pageable pageable);
	
	void delete (String name);

	List<Category> findAll();
	
	void delete (int id);
 
	}
	
	
