package application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import application.model.Indirizzo;
import application.persistenza.Database;

@Controller
public class ShippingListController {
	
	@GetMapping("shippingList")
	public ModelAndView Shipping(HttpServletRequest req) {
		ModelAndView model = new ModelAndView();
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		if (!utenteLoggato.isNull(req)) {
			List<Indirizzo> indirizzi = Database.getInstance().getFactory().getIndirizzoDao().findAllFromUserEnable(utenteLoggato.getUtente(req).getMail());
			model.addObject("indirizzi", indirizzi);
			model.setViewName("shippingList");
			return model;
		}
		model.setViewName("redirect:/");
		return model;
	}
	
	@PostMapping("/pay")
	public String ControllShipping() {
			return "pay";
	}
	
	@PostMapping("/shipping")
	public String Shipping() {
				return "shipping";
	}

}