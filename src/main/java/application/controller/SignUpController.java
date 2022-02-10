package application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import application.model.Utente;
import application.persistenza.Database;
import application.utilities.SendEmail;
import application.utilities.Utilities;
import net.bytebuddy.utility.RandomString;


@Controller
public class SignUpController {
	
	@GetMapping("signUpPage")
	public ModelAndView signUpPage(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session.getAttribute("utente") != null)
			return new ModelAndView("redirect:/");
		return new ModelAndView("signup");
	}
	

	@PostMapping("register")
	public ModelAndView register(HttpServletRequest req, @RequestParam("mail") String mail,@RequestParam("nome") String nome, 
			@RequestParam("cognome") String cognome, @RequestParam("username") String username, @RequestParam("password") String password) {
		
		ModelAndView model = new ModelAndView("signup");
		
		if(Database.getInstance().getFactory().getUtenteDao().findByUnique(username) != null) {
			model.addObject("error", "Lo username usato è già registrato. Cambialo");
			return model;
		}
		
		Utente utente = new Utente();
		utente.setMail(mail);
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setPassword(password);
		utente.setUsername(username);
		utente.setMaster(false);
		utente.setEnable(false);
		String verificationCode = RandomString.make(45);
		utente.setVerificationCode(verificationCode);
		
		if(Database.getInstance().getFactory().getUtenteDao().save(utente, false)) {
			
			String link = Utilities.getSiteUrl(req) + "/verify?verification_code=" + verificationCode;
			boolean test = SendEmail.getInstance().sendEmailVerification(utente, link);
			
			if(test) {
				model.addObject("message", "Controlla la tua email per verificarla");
			}
		} 	
		return model;
	}
	
	@GetMapping("verify")
	public ModelAndView verificaCodice(HttpServletRequest req, @RequestParam("verification_code") String verificationCode) {
		ModelAndView model = new ModelAndView("verify");
		
		Utente u = Database.getInstance().getFactory().getUtenteDao().findByVerificationCode(verificationCode);
		if(u != null) {
			
			model.addObject("message", "Email verificata correttamente");
			
			Database.getInstance().getFactory().getUtenteDao().enableUser(u.getUsername());
			
		} else {
			model.addObject("error", "Impossibile verificare una email con questo link. Riprova più tardi o chiedine un altro");
		}
		
		return model;
	}
}
