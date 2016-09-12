package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Category;
import ua.repository.CategoryRepository;
import ua.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;

//	@Override
//	public void save(String name) {
//		Category category = new Category();
//		category.setName(name);
//		categoryRepository.save(category);
//	}

	@Override
	public void save(Category category) {
		categoryRepository.save(category);
	}
	
	@Override
	public Category findByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public void delete(String name) {
		categoryRepository.delete(name);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}
	
	@Override
	public void delete(int id) {
		categoryRepository.delete(id);	
	}

	@Override
	public Category findOne(int id) {
		return categoryRepository.findOne(id);
	}
}
