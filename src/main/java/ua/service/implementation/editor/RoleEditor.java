package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Role;
import ua.service.RoleService;

public class RoleEditor extends PropertyEditorSupport{

	private final RoleService roleService;

	public RoleEditor(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Role role = roleService.findOne(Integer.valueOf(text));
		setValue(role);
	}

}
