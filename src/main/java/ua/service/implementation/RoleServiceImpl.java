package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Role;
import ua.repository.RoleRepository;
import ua.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void save(Role role) {
		roleRepository.save(role);
	}

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public Role findOne(int id) {
		return roleRepository.findOne(id);
	}

	@Override
	public void delete(String name) {
		roleRepository.delete(name);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public void delete(int id) {
		roleRepository.delete(id);
	}

}
