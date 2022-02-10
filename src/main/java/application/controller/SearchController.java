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
import application.persistenza.Database;
import application.utilities.Messaggi;


@Controller
public class SearchController {
	
	@GetMapping("category")
	public ModelAndView getRecipeCategory(@RequestParam String categoria, HttpServletRequest request) { 
		
		ModelAndView model = new ModelAndView("search");
		
		List<RicettaProxy> list = Database.getInstance().getFactory().getRicettaDao().findByCategory(categoria);
				
		int id = Integer.parseInt(categoria);
		Categoria cat = Database.getInstance().getFactory().getCategoriaDao().findByPrimaryKey(id);
		
		model.addObject("list", list);
		model.addObject("categoria", cat);
		
		return model;
	}
	
	@GetMapping("chef")
	public ModelAndView getRecipeChef(@RequestParam String key, HttpServletRequest request) { 
		
		ModelAndView model = new ModelAndView("search");
		
		List<RicettaProxy> list = Database.getInstance().getFactory().getRicettaDao().findPublishedBy(key);

		int id = Integer.parseInt(key);
		Chef chef = Database.getInstance().getFactory().getChefDao().findByPrimaryKey(id);
		
		model.addObject("list", list);
		model.addObject("chef", chef);
		
		return model;
	}
	
	@GetMapping("/ricerca")
	public ModelAndView getRecipes(@RequestParam("search") String search, @RequestParam("filter") String filter, HttpServletRequest request) {
			
		ModelAndView model = new ModelAndView("search");
		
		List<RicettaProxy> list = Database.getInstance().getFactory().getRicettaDao().search(search, filter);
		
		if ( list.size() == 0) {
			list = Database.getInstance().getFactory().getRicettaDao().findAll();
			model.addObject("list", list);
			model.addObject("message", "Questa ricerca non ha prodotto nessun risultato. Vedi le nostre migliore Ricette!");
			return model;
		}
		
		model.addObject("list", list);
		model.addObject("search", search);
		
		if ( !filter.equals("all") ) {
			int id = Integer.parseInt(filter);
			Categoria cat = Database.getInstance().getFactory().getCategoriaDao().findByPrimaryKey(id);
			model.addObject("filter", cat);
		}
		
		return model;
	}
	
	
	@GetMapping("/removeRecipe")
	@ResponseBody
	public Messaggi deleteRecipeFormChef(@RequestParam("recipe") String ricetta, @RequestParam("chef") String chef, HttpServletRequest request) {
		
		Messaggi msg = new Messaggi();
		
		int idRicetta = Integer.parseInt(ricetta);
		int idChef = Integer.parseInt(chef);
		if ( Database.getInstance().getFactory().getRicettaDao().deleteRecipeFormChef(idRicetta, idChef) ) {
			msg.setStatus("Auth");
			msg.setMessaggio("Auth");
			return msg;
		}
		
		msg.setStatus("nonAuth");
		msg.setMessaggio("La ricetta non e' stata eliminata.");

		return msg;
		
	}
	
}


