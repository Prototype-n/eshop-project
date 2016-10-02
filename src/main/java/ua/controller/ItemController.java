package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import ua.form.filter.ItemFilterForm;
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
//	
//	@ModelAttribute("filter")
//	public ItemFilterForm getFilter(){
//		return new ItemFilterForm();
//	}
	
	@RequestMapping("/admin/item")
	public String showItem(Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ItemFilterForm form){
	//	model.addAttribute("items", itemService.findAll());
		model.addAttribute("page", itemService.findAll(form, pageable));
		System.out.println("form"+form.toString());
		System.out.println("pageble "+pageable);
	//	model.addAttribute("categories", categoryService.findAll());
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
			BindingResult br, Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ItemFilterForm filter){
			if(br.hasErrors()){
				model.addAttribute("items", itemService.findAll(filter, pageable));
				model.addAttribute("categories", categoryService.findAll());
				return "AdminItem";		
			}
			itemService.save(form);
			return "redirect:/admin/item"+getParams(pageable, filter);
	}
	
	@RequestMapping("/admin/item/update/{id}")
	public String updateItem(Model model,
			@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ItemFilterForm form){
		model.addAttribute("form", itemService.findForForm(id));
		model.addAttribute("page", itemService.findAll(form, pageable));
		//model.addAttribute("item", itemService.findOne(id));
		model.addAttribute("items", itemService.findAll());// pokazye vsepru udate
		model.addAttribute("categories", categoryService.findAll());
		return "AdminItem";
	}
	
	@RequestMapping(value = "/admin/item/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") ItemFilterForm form){
		itemService.delete(id);
		return "redirect:/admin/item"+getParams(pageable, form);
	}
	
	private String getParams(Pageable pageable, ItemFilterForm form){
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
		return buffer.toString();
	}
}