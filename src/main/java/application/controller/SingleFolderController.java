package application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import application.model.Ricetta;
import application.model.Utente;
import application.persistenza.Database;

@Controller
public class SingleFolderController {
	@GetMapping("/singleFolder")
	public String getSingleFolder(@RequestParam String nome,HttpServletRequest req) { 
		HttpSession session = req.getSession();
		session.setAttribute("nome", nome);
		return "singleFolder";
	}
	
	@GetMapping("folder")
	@ResponseBody
	public List<Ricetta> getFolder(HttpServletRequest req) { 
		HttpSession session = req.getSession();
		Utente ut=(Utente) session.getAttribute("utente");
		String nome = (String) session.getAttribute("nome");
		List<Ricetta> list = Database.getInstance().getFactory().getRaccoltaDao().getRecipeForFolder(ut.getMail(), nome);
		return list;
	}
	
	
	@PostMapping("deleteFolder")
	@ResponseBody
	public String deleteFolder(HttpServletRequest req, @RequestParam("nomeRaccolta") String nome) { 
		HttpSession session = req.getSession();
		Utente ut=(Utente) session.getAttribute("utente");;
		if (Database.getInstance().getFactory().getRaccoltaDao().deleteFolder(nome, ut.getMail()))
			return "OK";
		else
			return "ERROR";

	}
	
	
	@PostMapping("/removeFromFolder")
	@ResponseBody
	public String removeRecipeFromFolder(@RequestParam("id_ricetta")int id_ricetta,HttpServletRequest req) {
		HttpSession session = req.getSession();
		String nome=(String) session.getAttribute("nome");
		Utente ut=(Utente) session.getAttribute("utente");
		if(Database.getInstance().getFactory().getRaccoltaDao().deleteRecipeFromFolder(nome, ut.getMail(),id_ricetta))
			return "OK";
		else
			return "ERROR";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("search/singleFolder")
	public String getSingleFolderOther(@RequestParam String nome,HttpServletRequest req) { 
		HttpSession session = req.getSession();
		session.setAttribute("nome", nome);
		return "singleFolder";
	}
	
	@GetMapping("search/folder")
	@ResponseBody
	public List<Ricetta> getFolderOther(HttpServletRequest req) { 
		HttpSession session = req.getSession();
		Utente ut=(Utente) session.getAttribute("utente");
		String nome = (String) session.getAttribute("nome");
		List<Ricetta> list = Database.getInstance().getFactory().getRaccoltaDao().getRecipeForFolder(ut.getMail(), nome);
		return list;
	}
	
	
	@PostMapping("search/deleteFolder")
	@ResponseBody
	public String deleteFolderOther(HttpServletRequest req, @RequestParam("nomeRaccolta") String nome) { 
		HttpSession session = req.getSession();
		Utente ut=(Utente) session.getAttribute("utente");;
		if (Database.getInstance().getFactory().getRaccoltaDao().deleteFolder(nome, ut.getMail()))
			return "OK";
		else
			return "ERROR";

	}
	
	
	@PostMapping("search/removeFromFolder")
	@ResponseBody
	public String removeRecipeFromFolderOther(@RequestParam("id_ricetta") String id_ricetta,@RequestParam("nomeRaccolta") String nome,HttpServletRequest req) {
		HttpSession session = req.getSession();
		Utente ut=(Utente) session.getAttribute("utente");
		if(Database.getInstance().getFactory().getRaccoltaDao().deleteRecipeFromFolder(nome, ut.getMail(), Integer.parseInt(id_ricetta)))
			return "OK";
		else
			return "ERROR";
	}
}
	

