package ua.service;

import java.util.List;

import ua.entity.Role;

public interface RoleService {

	void save(Role role);

	Role findByName(String name);

	public Role findOne(int id);

	void delete(String name);

	List<Role> findAll();

	void delete(int id);

}
