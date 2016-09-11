package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	Item findByName(String name);
	
//	Category findByItemCategory(String name);
//	
//	int findByItemCategoryId(String name);

	@Query("SELECT r FROM Item r LEFT JOIN FETCH r.category "
			+ "WHERE r.id=:id")
	Item findOneCategoryInited(@Param("id") int id);
	
	
	default void delete (String name) {
		delete(findByName(name));
}
}
