package application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import application.model.Ordine;
import application.persistenza.Database;
import application.utilities.Messaggi;

@Controller
public class OrdiniInConsegnaController {
	
	@GetMapping("ordiniInConsegna")
	public ModelAndView ordiniInCosegna(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		if ( request.getSession().getAttribute("admin") != null ) {
			List<Ordine> ordini =  Database.getInstance().getFactory().getOrdineDao().OrdiniInConsegna();
			request.getSession().setAttribute("ordini", ordini);
			model.setViewName("ordiniInConsegna");
			return model;
		}
		
		model.setViewName("redirect:/");
		return model;
	}
	

	
	
}
