package application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import application.model.Raccolta;
import application.model.Ricetta;
import application.model.Utente;
import application.persistenza.Database;

@Controller
public class FolderController {
	@GetMapping("/like")
	@ResponseBody
	public List <Ricetta> getLike(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Utente ut=(Utente) session.getAttribute("utente");
		List <Ricetta> ricettePreferite=new ArrayList<Ricetta>();
		ricettePreferite=Database.getInstance().getFactory().getLikesRicettaDao().findForUser(ut.getMail());
		return ricettePreferite;
	}
	
	@GetMapping("/folderLike")
	public String getFolderLike(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Utente ut=(Utente) session.getAttribute("utente");
		List <Raccolta> raccolteUtente=new ArrayList<Raccolta>();
		raccolteUtente=Database.getInstance().getFactory().getRaccoltaDao().getFolderForUser(ut.getMail());
		session.setAttribute("raccolteUtente", raccolteUtente);
		return "folderLike";
	}
		
	@PostMapping("newFolder")
	public String newFolder(HttpServletRequest req,@RequestParam("nome") String nameFolder) {
		HttpSession session = req.getSession();
		Utente ut=(Utente) session.getAttribute("utente");
		List <Raccolta> raccolteUtente=new ArrayList<Raccolta>();
		raccolteUtente=(List<Raccolta>) session.getAttribute("raccolteUtente");
		for (Raccolta raccolta: raccolteUtente) {
			if (raccolta.getNome().equals(nameFolder)) 
				return "folderLike";
		}
		Database.getInstance().getFactory().getRaccoltaDao().newFolder(nameFolder, ut.getMail());
		return "redirect:/singleFolder?nome="+nameFolder;

	}
	
	


	
	
	
	
	
	
	
	@GetMapping("search/like")
	@ResponseBody
	public List <Ricetta> getLikeOther(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Utente ut=(Utente) session.getAttribute("utente");
		List <Ricetta> ricettePreferite=new ArrayList<Ricetta>();
		ricettePreferite=Database.getInstance().getFactory().getLikesRicettaDao().findForUser(ut.getMail());
		return ricettePreferite;
	}
	
	@GetMapping("search/folderLike")
	public String getFolderLikeOther(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Utente ut=(Utente) session.getAttribute("utente");
		List <Raccolta> raccolteUtente=new ArrayList<Raccolta>();
		raccolteUtente=Database.getInstance().getFactory().getRaccoltaDao().getFolderForUser(ut.getMail());
		session.setAttribute("raccolteUtente", raccolteUtente);
		return "folderLike";
	}
		
	@PostMapping("search/newFolder")
	public String newFolderOther(HttpServletRequest req,@RequestParam("nome") String nameFolder) {
		HttpSession session = req.getSession();
		Utente ut=(Utente) session.getAttribute("utente");
		List <Raccolta> raccolteUtente=new ArrayList<Raccolta>();
		raccolteUtente=(List<Raccolta>) session.getAttribute("raccolteUtente");
		for (Raccolta raccolta: raccolteUtente) {
			if (raccolta.getNome().equals(nameFolder)) 
				return "folderLike";
		}
		Database.getInstance().getFactory().getRaccoltaDao().newFolder(nameFolder, ut.getMail());
		return "redirect:/singleFolder?nome="+nameFolder;

	}
	

}

