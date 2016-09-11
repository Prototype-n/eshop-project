package ua.service;

import java.util.List;

import ua.entity.Category;

public interface CategoryService {

	//void save(String name);
	
	void save(Category category);
	
	Category findByName(String name);
	
	public Category findOne(int id);
	
	void delete (String name);

	List<Category> findAll();
	
	void delete (int id);
 
	}
	
	
