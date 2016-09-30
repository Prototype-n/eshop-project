package ua.service.implementation;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.entity.MyUser;
import ua.entity.Role;
import ua.repository.MyUserRepository;
import ua.repository.RoleRepository;
import ua.service.MyUserService;


@Service("userDetailsService")
public class MyUserServiceImpl implements MyUserService, UserDetailsService {

	@Autowired
	private MyUserRepository myUserRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
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

	@Override
	public Page<MyUser> findAll(Pageable pageable) {
		return myUserRepository.findAll(pageable);
	}
	
	@Override
	public MyUser findById(int id) {
		return myUserRepository.findOne(id);
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
			return myUserRepository.findByLogin(login);
	}

	@Override
	public MyUser findByLogin(String login) {
		return myUserRepository.findByLogin(login);
	}
	
//	@PostConstruct
//	public void saveAdmin(){
//		MyUser myUser = myUserRepository.findOne(1);
//		if(myUser==null){
//			myUser = new MyUser();
//			myUser.setRole(roleRepository.findByName("ROLE_ADMIN")); // Role_Admin or ROLE_ADMIN
//			myUser.setLastName("Admin");
//			myUser.setMail("AdminMail");
//			myUser.setName("Admin");
//			myUser.setPhone("123456789");
//			myUser.setPassword(encoder.encode("admin"));
//			myUser.setLogin("admin");
//			myUser.setId(1);
//			myUserRepository.save(myUser);
//		}
//	}


}
