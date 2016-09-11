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

import ua.entity.MyUser;
import ua.entity.Role;
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
	
	@RequestMapping(value = "/admin/myUser", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("myUser") 
			@Valid MyUser myUser,
			BindingResult br,
			Model model) {
		if(br.hasErrors()){
			model.addAttribute("myUsers", myUserService.findAll());
			model.addAttribute("roles", roleService.findAll());
		return "AdminMyUser";		
		}
		myUserService.save(myUser);
		return "redirect:/admin/myUser";
	}
	
	@RequestMapping("/admin/myUser/update/{id}")
	public String updateMyUser(Model model,
			@PathVariable int id) {
		
		System.out.println("User");
		System.out.println(myUserService.findOne(id));
		System.out.println("roles");
		System.out.println(roleService.findAll());
				
		model.addAttribute("myUser", myUserService.findOne(id));
		model.addAttribute("roles", roleService.findAll());
		return "AdminMyUser";
	}
	
	@RequestMapping(value = "/admin/myUser/delete/{id}")
	public String delete(@PathVariable int id) {
		myUserService.delete(id);
		return "redirect:/admin/myUser";
	}
	

	@RequestMapping("/admin/myUser")
	public String showMyUser (Model model){
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("myUsers", myUserService.findAll());
		return "AdminMyUser";
	}
}
