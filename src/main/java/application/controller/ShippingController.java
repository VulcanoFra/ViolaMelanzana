package application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShippingController {
	@GetMapping("shipping")
	public String Shipping(HttpServletRequest req) {
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		if (!utenteLoggato.isNull(req))
			return "shipping";
		return "redirect:/";
	}
	
	
}