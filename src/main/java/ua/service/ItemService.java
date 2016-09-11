package ua.service;

import java.util.List;

import ua.entity.Item;
import ua.form.ItemForm;

public interface ItemService {

	//void save(int categoryId, String name, int price);
	
	void save(Item item);
	
	void save(ItemForm form);
	
	Item findByName(String name);
	
	Item findOne(int id);
	
	void delete (String name);

	List<Item> findAll();
	
	void delete (int id);

	ItemForm findForForm(int id);
}
