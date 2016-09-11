package ua.service;

import java.util.List;

import ua.entity.Item;
import ua.entity.MyUser;
import ua.entity.Role;

public interface MyUserService {

	void save(MyUser myUser);
	
	MyUser findByName(String name);
	
	MyUser findByLastName(String lastName);

	MyUser findByMail(String mail);

	MyUser findByPhone(String phone);
	
	MyUser findOne(int id);
	
	void delete (String name);
	
	void deleteByMail (String mail);

	void delete (int id);

	List<MyUser> findAll();
	
}
