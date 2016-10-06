package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.entity.Category;
import ua.form.ItemForm;
import ua.form.filter.ItemFilterForm;
import ua.service.CategoryService;
import ua.service.ItemService;
import ua.service.implementation.editor.CategoryEditor;
import ua.service.implementation.validator.ItemFormValidator;

@Controller
public class IndexController {

//	@RequestMapping("/")
//	public String showIndex() {
//		System.out.println(System.getProperty("catalina.home"));
//		return "Index";
//	}
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private CategoryService categoryService;
	
	@ModelAttribute("form")
	public ItemForm getForm() {
		return new ItemForm();
	}
	
	@ModelAttribute("filter")
	public ItemFilterForm getFilter(){
		return new ItemFilterForm();
	}
	
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
	   binder.setValidator(new ItemFormValidator(itemService));
	}
	
	
	@RequestMapping("/") // add with spring security
	public String showIndex(
			Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ItemFilterForm form,
			Principal principal){
		System.out.println(principal);
		System.out.println(System.getProperty("catalina.home"));
		
		model.addAttribute("listRandom", itemService.findRandom(8));
		//model.addAttribute("items", itemService.findAll());
		//model.addAttribute("page", itemService.findAll(form, pageable));
		model.addAttribute("categories", categoryService.findAll());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth instanceof AnonymousAuthenticationToken)){
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			System.out.println(userDetails.getAuthorities());
		}
		return "Index";
	}	

	@RequestMapping("/admin")
	public String showAdmin() {
		return "AdminPanel";
	}
	
	@RequestMapping("/login")
	public String showLogin() {
		return "Login";
	}
}
