package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Role;
import ua.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	@ModelAttribute("role")//??
	public Role getRole() {
		return new Role();
	}

	@RequestMapping("/admin/role")
	public String showRole(Model model) {
		model.addAttribute("roles", roleService.findAll());
		return "AdminRole";
	}

	@RequestMapping("/admin/role/delete/{id}")
	public String deleteRole(@PathVariable int id) {
		roleService.delete(id);
		return "redirect:/admin/role";
	}

	@RequestMapping("/admin/role/update/{id}")
	public String updateRole(@PathVariable int id, Model model) {
		model.addAttribute("role", roleService.findOne(id));
		return "AdminRole";
	}

	@RequestMapping(value = "/admin/role", method = RequestMethod.POST)
	public String save(@ModelAttribute("role") @Valid Role role) {
		roleService.save(role);
		return "redirect:/admin/role";
	}
}
