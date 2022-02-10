package application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import application.model.Ordine;
import application.persistenza.Database;

@Controller
public class VisualizzaOrdineInConsegnaController {

	@GetMapping("visualizzaOrdiniInConsegna")
	public ModelAndView visualizzaOrdiniInConsegna( HttpServletRequest request ) {
		
		ModelAndView model = new ModelAndView();
	
		if ( request.getSession().getAttribute("admin") != null ) {
			model.setViewName("visualizzaOrdiniInConsegna");
			return model;
		}
		model.setViewName("redirect:/");
		return model;
	}
	
	@GetMapping("/chageState")
	public String getRecipes(@RequestParam("state") String state, HttpServletRequest request) {
		
		Ordine o = (Ordine) request.getSession().getAttribute("ordine_visual");
		
		o.setStato(state);
		
		Database.getInstance().getFactory().getOrdineDao().saveOrUpdate(o);
		
		return "redirect:/ordiniInConsegna";
	}
}
