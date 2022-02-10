package application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import application.model.Ordine;
import application.persistenza.Database;

@Controller
public class CartController {
	
	@GetMapping("cart")
	public ModelAndView Cart(HttpServletRequest req) {
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		ModelAndView model = new ModelAndView();
		if (!utenteLoggato.isNull(req)) {
			Ordine ordine = Database.getInstance().getFactory().getOrdineDao().findCurrentFromUser(utenteLoggato.getUtente(req).getMail());
			model.addObject("ordine", ordine);
			model.setViewName("cart");
			return model;
		}
		
		model.setViewName("cart");
		return model;
	}
	
	@GetMapping("search/cart")
	public ModelAndView CartOther(HttpServletRequest req) {
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		ModelAndView model = new ModelAndView();
		if (!utenteLoggato.isNull(req)) {
			Ordine ordine = Database.getInstance().getFactory().getOrdineDao().findCurrentFromUser(utenteLoggato.getUtente(req).getMail());
			model.addObject("ordine", ordine);
			model.setViewName("cart");
			return model;
		}
		
		model.setViewName("cart");
		return model;
	}
	
	@PostMapping("/shippingList")
	public String ShippingList(HttpServletRequest req) {
			return "shippingList";
	}

}
