package application.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import application.model.Ingrediente;
import application.persistenza.Database;

@Controller
public class IngredientsController {

	@GetMapping("/getListIngredients")
	public ModelAndView getListIngredientsPage() {
		ModelAndView model = new ModelAndView("listOfIngredients");
		ArrayList<Ingrediente> ingredientiDisponibili = (ArrayList<Ingrediente>) Database.getInstance().getFactory().getIngredienteDao().findAll();
		if(ingredientiDisponibili == null)
			model.addObject("error", "Non ci sono ingredienti disponibili");
		else {
			model.addObject("lista", ingredientiDisponibili);
		}
		
		return model;
	}
	
	@PostMapping("/getIngredients")
	@ResponseBody
	public ArrayList<Ingrediente> getIngredients(){
		ArrayList<Ingrediente> ingredientiDisponibili = (ArrayList<Ingrediente>) Database.getInstance().getFactory().getIngredienteDao().findAll();
		
		return ingredientiDisponibili;
	}
	
}
