package application.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import application.model.Ordine;
import application.persistenza.Database;

@Controller
public class ConfirmedOrderController {
	
	@GetMapping("confirmedOrder")
	public ModelAndView ConfirmedOrder(HttpServletRequest req) {
		ModelAndView model = new ModelAndView();
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		if(!utenteLoggato.isNull(req)) {
			Ordine ordine = Database.getInstance().getFactory().getOrdineDao().findCurrentFromUser(utenteLoggato.getUtente(req).getMail());
			setData(ordine);
			model.addObject("ordine", ordine);
			System.out.println(ordine);
			model.setViewName("confirmedOrder");
			return model;
			
		}
		model.setViewName("redirect:/");
		return model;
	}
	
	private void setData(Ordine ordine) {
	    long now = System.currentTimeMillis();
	    Date sqlDate = new Date(now);
		ordine.setData(sqlDate);
		System.out.println(sqlDate);
		Database.getInstance().getFactory().getOrdineDao().saveOrUpdate(ordine);
	}
}
