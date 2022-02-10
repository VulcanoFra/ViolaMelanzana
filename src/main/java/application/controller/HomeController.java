package application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import application.model.Categoria;
import application.model.Chef;
import application.model.RicettaProxy;
import application.model.Utente;
import application.persistenza.Database;


@Controller
public class HomeController {
	
	@GetMapping("/")
	public ModelAndView getHomePage(HttpServletRequest request) {		
		
		ModelAndView model = new ModelAndView("index");
		
		if ( request.getSession().getAttribute("utente") != null ) {		
			
			if ( request.getSession().getAttribute("utente") instanceof Utente ) {
				Utente utente = (Utente) request.getSession().getAttribute("utente");	
				
				List<RicettaProxy> ricettaUtente = Database.getInstance().getFactory().getRicettaDao().findByPublisher(utente.getMail(), 4, 0);
				
				model.addObject("ricettaUtente", ricettaUtente);
			} 

		}
				
		return model;
	}
	
	@GetMapping("search")
	public String goToSearch(HttpServletRequest request) {		
		return "search";
	}
	
	@GetMapping("/ricettePopolari")
	@ResponseBody
	public List<RicettaProxy> getRicettePopolari(@RequestParam int offset, HttpServletRequest request) {
		List<RicettaProxy> ricettaPopolari = Database.getInstance().getFactory().getRicettaDao().findOrderBy("likes", 8, offset);
		return ricettaPopolari;
	}
	
	@GetMapping("/ultimeRicette")
	@ResponseBody
	public List<RicettaProxy> getUltimeRicette(@RequestParam int offset, HttpServletRequest request) {
		List<RicettaProxy> ultimeRicette = Database.getInstance().getFactory().getRicettaDao().findOrderBy("data_pubblicazione", 4, offset);
		return ultimeRicette;
	}
	
	@GetMapping("/ricettePubblicateUtente")
	@ResponseBody
	public List<RicettaProxy> getRicettePubblicateUtente(@RequestParam int offset, HttpServletRequest request) {
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
			
		List<RicettaProxy> ricettaUtente = Database.getInstance().getFactory().getRicettaDao().findByPublisher(utente.getMail(), 4, offset);
		
		return ricettaUtente;
	}
	
	@GetMapping("/tuttiChef")
	@ResponseBody
	public List<Chef> getTuttiChef(@RequestParam int offset, HttpServletRequest request) {
		
		List<Chef> tuttiChef = null;
		
		Boolean admin = (Boolean) request.getSession().getAttribute("admin");
		
		if ( admin == null ) {
			tuttiChef = Database.getInstance().getFactory().getChefDao().findOrderBy( 4, offset, false);
		} else if ( admin == true ) {
			tuttiChef = Database.getInstance().getFactory().getChefDao().findOrderBy( 4, offset, true);
		}
		return tuttiChef;
	}
	
	@GetMapping("/categorie")
	@ResponseBody
	public List<Categoria> getTutteCategorie(HttpServletRequest request) {		
		
		List<Categoria> tutteLeCategorie = Database.getInstance().getFactory().getCategoriaDao().findAll();
		
		return tutteLeCategorie;
	}
	
	@GetMapping("singleChef")
	@ResponseBody
	public Chef getSingleChef(@RequestParam String key, HttpServletRequest request) { 

		int id = Integer.parseInt(key);
		Chef chef = Database.getInstance().getFactory().getChefDao().findByPrimaryKey(id);
		
		return chef;
	}
}
