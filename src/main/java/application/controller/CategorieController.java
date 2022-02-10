package application.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import application.model.Categoria;
import application.persistenza.Database;

@Controller
public class CategorieController {
	
	@GetMapping("/getListCategories")
	public ModelAndView getListIngredientsPage() {
		ModelAndView model = new ModelAndView("listOfCategories");
		ArrayList<Categoria> categorie = (ArrayList<Categoria>) Database.getInstance().getFactory().getCategoriaDao().findAll();
		if(categorie == null)
			model.addObject("error", "Non ci sono ingredienti disponibili");
		else {
			model.addObject("lista", categorie);
		}
		
		return model;
	}
	
	@PostMapping("/getCategories")
	@ResponseBody
	public ArrayList<Categoria> getAllCategories() {
		ArrayList<Categoria> categorie = (ArrayList<Categoria>) Database.getInstance().getFactory().getCategoriaDao().findAll();

		return categorie;
	}
}
