package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);
	
	default void delete (String name) {
		delete(findByName(name));
			
	}		
}