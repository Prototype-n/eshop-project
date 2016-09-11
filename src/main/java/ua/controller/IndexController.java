package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String showIndex() {
		return "Index";

	}

	@RequestMapping("/admin")
	public String showAdmin() {
		return "AdminPanel";
	}
}
