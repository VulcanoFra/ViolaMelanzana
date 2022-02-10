package application.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import application.model.Chef;
import application.model.NomiSequenze;
import application.persistenza.Database;
import application.persistenza.dao.jdbc.IdBroker;

@Controller
public class ChefController {

	@GetMapping("/insertChefPage")
	public String getInsertChefPage(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session.getAttribute("admin") != null) {
			return "insertChef";
		}
		
		return "error";
	}
	
	@PostMapping("/insertChef")
	public String insertChef(String nome, String cognome, String descrizione, String linkImg, HttpServletRequest req) {
		
		Chef chef = new Chef();
		try {
			chef.setId(IdBroker.getId(Database.getInstance().getConn(), NomiSequenze.CHEF));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chef.setNome(nome);
		chef.setCognome(cognome);
		chef.setDescrizione(descrizione);
		chef.setImg_link(linkImg);
		
		Database.getInstance().getFactory().getChefDao().saveOrUpdate(chef);
		return "redirect:/insertChefPage";
	}
	
}
