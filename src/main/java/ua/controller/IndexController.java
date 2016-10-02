package ua.controller;

import java.security.Principal;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

//	@RequestMapping("/")
//	public String showIndex() {
//		System.out.println(System.getProperty("catalina.home"));
//		return "Index";
//	}
	
	@RequestMapping("/") // add with spring security
	public String showIndex(Principal principal){
		System.out.println(principal);
		System.out.println(System.getProperty("catalina.home"));
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth instanceof AnonymousAuthenticationToken)){
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			System.out.println(userDetails.getAuthorities());
		}
		return "Index";
	}


	@RequestMapping("/admin")
	public String showAdmin() {
		return "AdminPanel";
	}
	
	@RequestMapping("/login")
	public String showLogin() {
		return "Login";
	}
	
	
}
