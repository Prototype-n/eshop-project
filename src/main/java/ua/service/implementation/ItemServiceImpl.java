package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Category;
import ua.entity.Item;
import ua.form.ItemForm;
import ua.form.filter.CategoryFilterForm;
import ua.form.filter.ItemFilterForm;
import ua.repository.CategoryRepository;
import ua.repository.ItemRepository;
import ua.service.FileWriter;
import ua.service.FileWriter.Folder;
import ua.service.ItemService;
import ua.service.implementation.specification.CategoryFilterAdapter;
import ua.service.implementation.specification.ItemFilterAdapter;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{

	
	@Autowired
	private ItemRepository itemRepository;
	
	
	@Autowired
	private FileWriter fileWriter;
	
	@Override
	public void save(Item item) {
		//Item item = new Item();
	//	Category category =  categoryRepository.findOne(item.getCategory().getId());
		//item.setCategory(category);
//		item.setName(name);
//		item.setPrice(price);
		itemRepository.save(item);
		
//		Category category = new Category();
//		category.setName(nameCategory);
//		categoryRepository.save(category);
//		item.setCategory(category);
//		item.setName(name);
//		item.setPrice(price);
//		itemRepository.save(item);
//		System.out.println(name);
//		System.out.println(nameCategory);
//		//dorobutu z categorijamu
	}

	@Override
	public Item findByName(String name) {
		return itemRepository.findByName(name);
	}

	@Override
	public Item findOne(int id) {
		return itemRepository.findOne(id);
	}
	
	@Override
	public void delete(String name) {
		itemRepository.delete(name);
	}

	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public void delete(int id) {
		itemRepository.delete(id);
	}

	@Override
	public void save(ItemForm form) {
			Item item = new Item();
			item.setCategory(form.getCategory());
			item.setName(form.getName());
			item.setPrice(Integer.parseInt(form.getPrice()));
			item.setId(form.getId());
			item.setPath(form.getPath());
			item.setVersion(form.getVersion());
			itemRepository.saveAndFlush(item);
			String extension = fileWriter.write(Folder.ITEM, form.getFile(), item.getId());
			if(extension!=null){
				item.setVersion(form.getVersion()+1);
				item.setPath(extension);
				itemRepository.save(item);
			}
	}
	
	@Override
	public ItemForm findForForm(int id) {
		Item item = itemRepository.findOneCategoryInited(id);
		ItemForm form = new ItemForm();
		form.setId(item.getId());
		form.setCategory(item.getCategory());
		form.setPrice(String.valueOf(item.getPrice()));
		form.setName(item.getName());
		form.setPath(item.getPath());
		form.setVersion(item.getVersion());
		
		return form;
	}
	
	@Override
	public Page<Item> findAll(ItemFilterForm form, Pageable pageable) {
		return itemRepository.findAll(new ItemFilterAdapter(form), pageable);
	}	
}