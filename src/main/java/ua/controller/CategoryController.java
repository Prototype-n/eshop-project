package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Category;
import ua.form.filter.CategoryFilterForm;
import ua.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@ModelAttribute("category")
	public Category getCategory(){
		return new Category();
	}
	
	@ModelAttribute("filter")
	public CategoryFilterForm getFilter(){
		return new CategoryFilterForm();
	}
	
//	@RequestMapping("/admin/category")
//	public String showCategory(Model model){
//		model.addAttribute("categories", categoryService.findAll());
//		return "AdminCategory";
//	}

	@RequestMapping("/admin/category")
	public String showCategory(Model model, 
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CategoryFilterForm form){
		model.addAttribute("page", categoryService.findAll(pageable, form));
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
			return "redirect:/admin/category" ;
		}
		
		private String getParams(Pageable pageable, CategoryFilterForm form){
			StringBuilder buffer = new StringBuilder();
			buffer.append("?page=");
			buffer.append(String.valueOf(pageable.getPageNumber()+1));
			buffer.append("&size=");
			buffer.append(String.valueOf(pageable.getPageSize()));
			if(pageable.getSort()!=null){
				buffer.append("&sort=");
				Sort sort = pageable.getSort();
				sort.forEach((order)->{
					buffer.append(order.getProperty());
					if(order.getDirection()!=Direction.ASC)
					buffer.append(",desc");
				});
			}
			buffer.append("&search=");
			buffer.append(form.getSearch());
			return buffer.toString();
		}
	}