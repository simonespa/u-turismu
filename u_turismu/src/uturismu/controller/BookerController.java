package uturismu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("bo")
public class BookerController {

	@RequestMapping(value="/home", method=RequestMethod.POST)
	public String showHomePage() {
		return "home";
	}

}
