package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.MyUser;
import ua.service.MyUserService;

public class MyUserValidator implements Validator{

	public final MyUserService myUserService;
	
	private static Pattern pn = Pattern.compile("^[a-z0-9_-]{3,15}$");
	private static Pattern pm = Pattern.compile("^[a-z@._]+$");
	private static Pattern pp = Pattern.compile("^[0-9]{1,12}$");

	public MyUserValidator (MyUserService myUserService){
		this.myUserService = myUserService;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return MyUser.class.equals(clazz);
	}

	@Override
	public void validate(Object terget, Errors errors) {
		
		MyUser myUser = (MyUser) terget;
		if(myUser.getId()==0)if(myUserService.findByMail(myUser.getMail())!=null){
			errors.rejectValue("mail", "", "Mail already regisred");
		}
		
		Matcher mln = pn.matcher(myUser.getLastName());
		if(!mln.matches()){
			errors.rejectValue("lastName", "", "Name format is A-z letters");	
		}

		Matcher mn = pn.matcher(myUser.getName());
		if(!mn.matches()){
			errors.rejectValue("name", "", "Name format is A-z letters");	
		}
		
		Matcher mm = pm.matcher(myUser.getMail());
		if(!mm.matches()){
			errors.rejectValue("mail", "", "Mail format is ====");	
		}
		
		Matcher mp = pp.matcher(myUser.getPhone());
		if(!mp.matches()){
			errors.rejectValue("phone", "", "Phone format is ====");	
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",     "", "Can`t be empty (name)");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Can`t be empty (Last name)");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail",     "", "Can`t be empty (mail)");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone",    "", "Can`t be empty (phone)");
	
	}
}
