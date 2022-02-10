package application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import application.model.Utente;
import application.persistenza.Database;

@Controller
public class ResetPasswordController {

	@GetMapping("/resetPassword")
	public ModelAndView getResetPage(@RequestParam("token") String token) {
		ModelAndView model = new ModelAndView("resetPassword");
		model.addObject("token", token);
		return model;
		
	}
	
	@PostMapping("/doReset")
	public ModelAndView actionReset(HttpServletRequest req, String password, String confirmPassword, String token) {

		ModelAndView model = new ModelAndView("rispostaReset");
		Utente u = Database.getInstance().getFactory().getUtenteDao().findByToken(token);
		
		if(u == null) {
			model.addObject("error", "Scusaci, non è possibile aggiornare la password da questo link. Richiedine uno nuovo");
		} else {
			
			if(!Database.getInstance().getFactory().getUtenteDao().resetPassword(u, password))
				model.addObject("error", "Scusaci, al momento non è possibile cambiare la password. Riprova pi� tardi");
			else {
				model.addObject("message", "Password resettata correttamente");
			}
		}
			
		return model;
	}
}
