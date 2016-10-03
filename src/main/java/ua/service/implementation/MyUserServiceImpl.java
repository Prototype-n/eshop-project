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
import org.springframework.transaction.annotation.Transactional;

import ua.entity.MyUser;
import ua.entity.Role;
import ua.form.filter.MyUserFilterForm;
import ua.repository.MyUserRepository;
import ua.repository.RoleRepository;
import ua.service.MyUserService;
import ua.service.implementation.specification.CategoryFilterAdapter;
import ua.service.implementation.specification.MyUserFilterAdapter;


@Service("userDetailsService")
@Transactional
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

	@Override
	public Page<MyUser> findAll(MyUserFilterForm form, Pageable pageable) {
		return myUserRepository.findAll(new MyUserFilterAdapter(form), pageable);
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
	
	public void saveMy(MyUser myUser){
	MyUser MU = myUserRepository.findByMail(myUser.getMail());
	if(MU==null){
		MU = new MyUser();
		MU.setLogin(myUser.getLogin());
		MU.setPassword(encoder.encode(myUser.getPassword()));
		MU.setName(myUser.getName());
		MU.setLastName(myUser.getLastName());
		MU.setMail(myUser.getMail());
		MU.setRole(roleRepository.findByName("ROLE_USER")); // Role_Admin or ROLE_ADMIN
		MU.setPhone(myUser.getPhone());
		myUserRepository.save(MU);
	}
}
}
