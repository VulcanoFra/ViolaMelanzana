package application.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import application.model.Ricetta;
import application.model.Utente;
import application.persistenza.Database;

@Controller
public class RequestAdminViewController {
	
	@GetMapping("/requestAdminView")
	public String view(HttpServletRequest req)	{
		return "requestAdminView";
	}
	
	@GetMapping("/allBanRequest")
	@ResponseBody
	public List<Ricetta> getVanRequest(HttpServletRequest req){
		List<Ricetta> ricette= new ArrayList<Ricetta>();
		ricette= Database.getInstance().getFactory().getRicettaDao().getWithBan();
		return ricette;
	}
	
	@GetMapping("/allUsers")
	@ResponseBody
	public List<Utente> getAll(HttpServletRequest req){
		List<Utente> allUsers= new ArrayList<Utente>();
		allUsers= Database.getInstance().getFactory().getUtenteDao().findAllWithRequest();
		return allUsers;
	}

	@PostMapping("/getRequestForUser")
	@ResponseBody
	public List<List<String>> getRequestForUser(HttpServletRequest req) throws IOException{
		String mail1= req.getReader().readLine();
		String mail= mail1.substring(1,mail1.length()-1);
		List<List<String>> userRequest= new ArrayList<List<String>>();
		userRequest= Database.getInstance().getFactory().getUtenteDao().getUserRequest(mail);
		return userRequest;
	}
	
	@PostMapping("/getRecipe")
	@ResponseBody
	public Ricetta getRecipe(HttpServletRequest req) throws IOException {
		String id_ricetta= req.getReader().readLine();
		Ricetta ricetta= Database.getInstance().getFactory().getRicettaDao().findByPrimaryKey(Integer.parseInt(id_ricetta));
		return ricetta;
	}
	
	@PostMapping("/accept")
	@ResponseBody
	public String setAccept(HttpServletRequest req,@RequestParam("accept") String id_o_mail,@RequestParam("requestType") String requestType) throws IOException{
		if(requestType.equals("aggiunta ricetta")) {
			if(Database.getInstance().getFactory().getRicettaDao().update(Integer.parseInt(id_o_mail),true))
			return "OK";
		}
		if (requestType.equals("rimozione ricetta")) {
			if (Database.getInstance().getFactory().getRicettaDao().updateDeleteRequest(Integer.parseInt(id_o_mail),true) && 
			Database.getInstance().getFactory().getRicettaDao().update(Integer.parseInt(id_o_mail),false))
				return "OK";
		}
		
		if (requestType.equals("promozione a master"))
			if(Database.getInstance().getFactory().getUtenteDao().isMaster(id_o_mail,true))
				return "OK";
		
		if(requestType.equals("declassare utente master")) {
			if(Database.getInstance().getFactory().getUtenteDao().isMaster(id_o_mail,false)) 
				return "OK";
		}
		
		return "ERROR";
	}
	
	@PostMapping("/refuse")
	@ResponseBody
	public String setRefuse(HttpServletRequest req,@RequestParam("refuse") String id_o_mail,@RequestParam("requestType") String requestType) throws IOException{
		if(requestType.equals("aggiunta ricetta")) {
			if (Database.getInstance().getFactory().getRicettaDao().update(Integer.parseInt(id_o_mail),false))
					return "OK";

		}
		if (requestType.equals("rimozione ricetta")) {
			if(Database.getInstance().getFactory().getRicettaDao().updateDeleteRequest(Integer.parseInt(id_o_mail),false))
				return "OK";

		}
		return "ERROR";
	}
	
	@PostMapping("/banRecipe")
	@ResponseBody
	public String banRecipe(HttpServletRequest req,@RequestParam("banRecipe") String id_ricetta){
		if (Database.getInstance().getFactory().getRicettaDao().update(Integer.parseInt(id_ricetta),false))
			return "ELIMINATA";
		else
			return "ERROR";
	}
	
}
	
