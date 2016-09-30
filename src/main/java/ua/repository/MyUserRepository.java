package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Integer> {

	MyUser findByLogin(String login);
	
	MyUser findByName(String name);

	MyUser findByLastName(String lastName);
	
	MyUser findByMail(String mail);

	MyUser findByPhone(String phone);
	
	@Query("SELECT r FROM MyUser r LEFT JOIN FETCH r.role "
			+ "WHERE r.id=:id")
	MyUser findOneRoleInited(@Param("id") int id);
	
	default void delete (String name) {
		delete(findByName(name));
	}

	default void deleteByMail(String mail) {
		delete(findByMail(mail));
	}
}