package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Category;
import ua.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@ModelAttribute("category")
	public Category getCategory(){
		return new Category();
	}
	
//	@RequestMapping("/admin/category")
//	public String showCategory(Model model){
//		model.addAttribute("categories", categoryService.findAll());
//		return "AdminCategory";
//	}

	@RequestMapping("/admin/category")
	public String showCategory(Model model, @PageableDefault(5) Pageable pageable){
		model.addAttribute("page", categoryService.findAll(pageable));
		return "AdminCategory";
	}
	
//		@RequestMapping(value= "/admin/category", method=RequestMethod.POST)
//		public String save(@RequestParam String name){
//			categoryService.save(name);
//			return "redirect:/admin/category";
//		}		
		
		@RequestMapping("/admin/category/delete/{id}")
		public String deleteCategory(@PathVariable int id){
			categoryService.delete(id);
			return "redirect:/admin/category";
		}
		
		@RequestMapping("/admin/category/update/{id}")
		public String updateCategory(@PathVariable int id, Model model ){
			model.addAttribute("category", categoryService.findOne(id));
			//model.addAttribute("categories", categoryService.findAll(pageable));
			return "AdminCategory";
		}
		
		@RequestMapping(value= "/admin/category", method=RequestMethod.POST)
		public String save(@ModelAttribute("category") @Valid Category category) {
			categoryService.save(category);
			return "redirect:/admin/category";
		}
	}