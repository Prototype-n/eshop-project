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

import ua.entity.MyUser;
import ua.entity.Role;
import ua.form.filter.MyUserFilterForm;
import ua.service.MyUserService;
import ua.service.RoleService;
import ua.service.implementation.editor.RoleEditor;
import ua.service.implementation.validator.MyUserValidator;

@Controller
public class MyUserController {
	
	@Autowired
	private MyUserService myUserService;
	
	@Autowired
	private RoleService roleService;
	
	@InitBinder("myUser")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(Role.class, new RoleEditor(roleService));
	   binder.setValidator(new MyUserValidator(myUserService));
	}
	
	@ModelAttribute("myUser")
	public MyUser getMyUser() {
		return new MyUser();
	}
	
	@ModelAttribute("filter")
	public MyUserFilterForm getFilter(){
		return new MyUserFilterForm();
	}
	
	@RequestMapping("/admin/myUser")
	public String showMyUser(Model model, 
			
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") MyUserFilterForm form){
	//	model.addAttribute("myUsers", myUserService.findAll());
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("page", myUserService.findAll(pageable));
		return "AdminMyUser";
	}
	
	@RequestMapping(value = "/admin/myUser", method = RequestMethod.POST)
	public String save(Model model, 
			@ModelAttribute("myUser")
			@Valid MyUser myUser,
			BindingResult br, 
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") MyUserFilterForm form){
		if(br.hasErrors()){
			model.addAttribute("page", myUserService.findAll(form, pageable));
			model.addAttribute("myUsers", myUserService.findAll());
			model.addAttribute("roles", roleService.findAll());
		return "AdminMyUser";		
		}
		myUserService.save(myUser);
		return "redirect:/admin/myUser"+getParams(pageable, form);
	}
	
	@RequestMapping("/admin/myUser/update/{id}")
	public String updateMyUser(Model model,
			@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") MyUserFilterForm filter){
		model.addAttribute("myUser", myUserService.findOne(id));
		model.addAttribute("page", myUserService.findAll(filter, pageable));
		model.addAttribute("roles", roleService.findAll());
		return "AdminMyUser";
	}
	
	@RequestMapping(value = "/admin/myUser/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") MyUserFilterForm form){
		myUserService.delete(id);
		return "redirect:/admin/myUser"+getParams(pageable, form);
	}
	
//bez pageble
//	@RequestMapping("/admin/myUser")
//	public String showMyUser (Model model){
//		model.addAttribute("roles", roleService.findAll());
//		model.addAttribute("myUsers", myUserService.findAll());
//		return "AdminMyUser";
//	}
	
	private String getParams(Pageable pageable, MyUserFilterForm form){
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