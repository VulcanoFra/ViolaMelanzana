package application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PayController {
	@GetMapping("pay")
	public String pay(HttpServletRequest req) {
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		if (!utenteLoggato.isNull(req))
			return "pay";
		return "redirect:/";
	}
}