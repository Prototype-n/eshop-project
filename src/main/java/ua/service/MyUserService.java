package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.MyUser;

public interface MyUserService {

	void save(MyUser myUser);
	
	public MyUser findById(int id);
	
	MyUser findByLogin(String name);
	
	MyUser findByName(String name);
	
	MyUser findByLastName(String lastName);

	MyUser findByMail(String mail);

	MyUser findByPhone(String phone);
	
	MyUser findOne(int id);
	
	void delete (String name);
	
	void deleteByMail (String mail);

	void delete (int id);

	List<MyUser> findAll();
	
	public Page<MyUser> findAll(Pageable pageable);
}
