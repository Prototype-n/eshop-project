package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Category;
import ua.form.ItemForm;
import ua.service.CategoryService;
import ua.service.ItemService;
import ua.service.implementation.editor.CategoryEditor;
import ua.service.implementation.validator.ItemFormValidator;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private CategoryService categoryService;

	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
	   binder.setValidator(new ItemFormValidator(itemService));
	}
	
	@ModelAttribute("form")
	public ItemForm getForm() {
		return new ItemForm();
	}
	
	@RequestMapping("/admin/item")
	public String showItem(Model model) {
		model.addAttribute("items", itemService.findAll());
		model.addAttribute("categories", categoryService.findAll());
		return "AdminItem";
	}

//	@RequestMapping(value = "/admin/item", method = RequestMethod.POST)
//	public String save(
//			@RequestParam int categoryId, 
//			@RequestParam String name, 
//			@RequestParam int price) {
//		itemService.save(categoryId, name, price);
//		return "redirect:/admin/item";
//	}
	
//	@RequestMapping(value = "/admin/item", method = RequestMethod.POST)
//	public String save(
//			@ModelAttribute("item") 
//			@Valid Item item) {
//			itemService.save(item);
//		return "redirect:/admin/item";
//	}
	
	@RequestMapping(value = "/admin/item", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("form") 
			@Valid ItemForm form,
			BindingResult br,
			Model model) {
			if(br.hasErrors()){
				model.addAttribute("items", itemService.findAll());
				model.addAttribute("categories", categoryService.findAll());
				return "AdminItem";
				
			}
		
			itemService.save(form);
			return "redirect:/admin/item";
	}
	
	@RequestMapping("/admin/item/update/{id}")
	public String updateItem(Model model,
			@PathVariable int id) {
		model.addAttribute("form", itemService.findForForm(id));
		model.addAttribute("item", itemService.findOne(id));
		//model.addAttribute("items", itemService.findAll());
		model.addAttribute("categories", categoryService.findAll());
		return "AdminItem";
	}
	
	@RequestMapping(value = "/admin/item/delete/{id}")
	public String delete(@PathVariable int id) {
		itemService.delete(id);
		return "redirect:/admin/item";
	}
}