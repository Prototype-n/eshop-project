package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.MyUser;
import ua.repository.MyUserRepository;
import ua.service.MyUserService;

@Service
@Transactional
public class MyUserServiceImpl implements MyUserService{

	@Autowired
	private MyUserRepository myUserRepository;

	@Override
	public void save(MyUser myUser) {
		myUserRepository.save(myUser);
	}

	@Override
	public MyUser findByName(String name) {
		return myUserRepository.findByName(name);
	}

	@Override
	public MyUser findByLastName(String lastName) {
		return myUserRepository.findByLastName(lastName);
	}

	@Override
	public MyUser findByPhone(String phone) {
		return myUserRepository.findByPhone(phone);
	}

	@Override
	public MyUser findByMail(String mail) {
		return myUserRepository.findByMail(mail);
	}

	@Override
	public void delete(String name) {
		myUserRepository.delete(name);
	}

	@Override
	public void deleteByMail(String mail) {
		myUserRepository.deleteByMail(mail);
	}

	@Override
	public List<MyUser> findAll() {
		return myUserRepository.findAll();
	}

	@Override
	public void delete(int id) {
		myUserRepository.delete(id);
	}

	@Override
	public MyUser findOne(int id) {
		return myUserRepository.findOneRoleInited(id);
		//return myUserRepository.findOneRoleInited(id);
	}
}
