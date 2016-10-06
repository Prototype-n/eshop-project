package ua.repository;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javassist.expr.NewArray;
import ua.entity.Category;
import ua.entity.Item;
import ua.form.filter.ItemFilterForm;
import ua.service.implementation.specification.ItemFilterAdapter;

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
	
	
	@Query("SELECT i FROM Item i LEFT JOIN FETCH "
			+ "i.category "
			+ "WHERE i.category.id=:id")
	
	List<Item> findByCategoryIdWithoutPagination(@Param("id")int id);

//	@Query("SELECT r FROM Item r "
//			+ "WHERE r.category.id=:id")
	default Page<Item> findItemByCategoryId(ItemFilterAdapter form, Pageable pageable,@Param("id") int id) {
		List<Item> items = findByCategoryIdWithoutPagination(id); //findAll();
		
		///////////////////////
		int start = pageable.getOffset();
		int end = (start + pageable.getPageSize()) > items.size() ? items.size() : (start + pageable.getPageSize());
		Page<Item> pages = new PageImpl<Item>(items.subList(start, end), pageable, items.size());
		return pages;
	}
//	
//	@Query("SELECT r FROM Item r "
//			+ "WHERE r.category.id=:id")
//	Page<Item> findItemByCategoryId(Pageable pageable, @Param("id") int id);
//	
	default void test (Pageable pageable, int id) {
		System.out.println(pageable);
	}
	
	default void delete (String name) {
		delete(findByName(name));
	}
	
	public default List<Item> findRandom(int numbersItem){
		List <Item> list=findAll();
		List <Item> listRezult= new ArrayList<>();
		int Max=findAll().size()-1;
		int Min=0;
			
		for (int i = 0; i < numbersItem; i++) {			
			int random = Min + (int)(Math.random() * ((Max - Min) + 1));
			listRezult.add(list.get(random));
			System.out.println("numbersItem :"+numbersItem);
			System.out.println("i :"+i);
		}	
		return listRezult;
	}
}