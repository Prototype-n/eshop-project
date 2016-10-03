package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Category;
import ua.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {

	Item findByName(String name);
	
//	Category findByItemCategory(String name);
//	
//	int findByItemCategoryId(String name);

	@Query("SELECT r FROM Item r LEFT JOIN FETCH r.category "
			+ "WHERE r.id=:id")
	Item findOneCategoryInited(@Param("id") int id);
	
	
	@Query("SELECT i FROM Item i LEFT JOIN FETCH "
			+ "i.category ")
	List<Item> findAll();
	
	@Query(value="SELECT i FROM Item i LEFT JOIN FETCH "
			+ "i.category ",
			countQuery="SELECT count(i.id) FROM Item i")
	Page<Item> findAll(Pageable pageable);
	
	@Query("SELECT i FROM Item i LEFT JOIN FETCH "
			+ "i.category "
			+ "WHERE i.id=:id")
	Item findForForm(@Param("id")int id);
	
	default void delete (String name) {
		delete(findByName(name));
}
}
