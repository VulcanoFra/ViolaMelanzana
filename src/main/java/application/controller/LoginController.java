package application.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import application.model.Amministratore;
import application.model.NomiSequenze;
import application.model.Utente;
import application.persistenza.Database;
import application.persistenza.dao.jdbc.IdBroker;

@Controller
public class LoginController {

	@GetMapping("loginPage")
	public String getLoginPage(HttpServletRequest req) {
		if(req.getSession().getAttribute("utente") != null)
			return "redirect:/";
		return "login";
	}
	
	@PostMapping("/doLogin")
	public ModelAndView faiLogin(HttpServletRequest req, String username, String password) {
		
		ModelAndView model = new ModelAndView();
		Utente utente = Database.getInstance().getFactory().getUtenteDao().checkByUniqueAndPassword(username, password);
		HttpSession session = req.getSession(true);
		//TODO Mostrare diversi errori anche nella view. Potrebbe non essere enable, oppure potrebbe aver sbagliato password, oppure usa per entarre
		//uno username di google
		if(utente != null) {
			if(utente.isEnable()) {
				session.setAttribute("utente", utente);
				session.removeAttribute("admin");
				model.setViewName("redirect:/");
				return model;	
			}
			model.addObject("error", "Utente non abilitato. Devi confermare la tua email");
			model.setViewName("login");
			return model;
		}
			
		Amministratore admin = Database.getInstance().getFactory().getAmministratoreDao().checkByUniqueAndPassword(username, password);
		if(admin != null) {
			session.setAttribute("utente", admin);
			session.setAttribute("admin", true);
			model.setViewName("redirect:/");
			return model;	
		}

		model.addObject("error", "Combinazione username-password errata oppure username non associato a nessun account");
		model.setViewName("/login");
		return model;
	}
	
	@GetMapping("logOut")
	public String logOut(HttpServletRequest req, HttpServletResponse res) {	
		HttpSession session = req.getSession();
		session.removeAttribute("utente");
		session.removeAttribute("google");
		session.removeAttribute("admin");
		
		return "redirect:/";
	}
	
	@PostMapping("/googleSignIn")
	@ResponseBody
	public String addGoogleUser(@RequestParam("mail") String mailGoogle, @RequestParam("nome") String nomeGoogle, @RequestParam("cognome") String cognomeGoogle,
			HttpServletRequest req, HttpServletResponse resp)  {
		
		Utente u = Database.getInstance().getFactory().getUtenteDao().findByPrimaryKey(mailGoogle);
		if(u == null) {
			u = new Utente(mailGoogle, nomeGoogle, cognomeGoogle);
			try {
				u.setUsername("gmailUser" + IdBroker.getId(Database.getInstance().getConn(), NomiSequenze.GOOGLE));
				u.setMaster(false);
				u.setEnable(true);
				if(!Database.getInstance().getFactory().getUtenteDao().save(u, true))
					return "NO";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("utente", u);
		session.setAttribute("google", true);
		
		resp.setStatus(200);
		
		return "OK";
	}
	
}
