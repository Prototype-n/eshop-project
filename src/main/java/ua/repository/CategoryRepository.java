package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Category findByName(String name);
	
	default void delete (String name) {
		delete(findByName(name));
			
	}
}
