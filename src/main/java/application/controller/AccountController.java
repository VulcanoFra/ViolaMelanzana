package application.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import application.model.Indirizzo;
import application.model.Ordine;
import application.model.Prodotto;
import application.model.Utente;
import application.persistenza.Database;
import application.utilities.Messaggi;

@Controller
public class AccountController {
	
	@GetMapping("account")
	public String getProfile(HttpServletRequest request) {
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		
		if ( utente == null ) {
			return "redirect:/";
		}
		
		return "account";
	}
	
	@GetMapping("account/address")
	@ResponseBody
	public List<Indirizzo> getAddress(HttpServletRequest request) {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		
		List<Indirizzo> indirizzi = Database.getInstance().getFactory().getIndirizzoDao().findAllFromUserEnable(utente.getMail());
		
		return indirizzi;
	}
	
	@GetMapping("account/orders")
	@ResponseBody
	public List<Ordine> getOrders(HttpServletRequest request) {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		
		List<Ordine> ordini = Database.getInstance().getFactory().getOrdineDao().findAllOrdersFromUser(utente.getMail());
		
		return ordini;
	}
	
	
	
	
	@PostMapping("/data")
	@ResponseBody
	public Utente setData(@RequestParam("name") String name, @RequestParam("lastName") String lastName, HttpServletRequest request) {
		
		Utente utenteOld = (Utente) request.getSession().getAttribute("utente");
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
				
		utente.setNome(name);
		utente.setCognome(lastName);
		
		if ( Database.getInstance().getFactory().getUtenteDao().updateData(utente) ) {	
			HttpSession session = request.getSession(true);
			session.setAttribute("utente", utente);
			
			return utente;
		} 
		
		return utenteOld;
	}
	
	@PostMapping("/username")
	@ResponseBody
	public Messaggi setUsername(@RequestParam("newUsername") String newUsername, HttpServletRequest request) {
		
		Messaggi msg = new Messaggi();
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		
		utente.setUsername(newUsername);
		
		if ( Database.getInstance().getFactory().getUtenteDao().updateUsername(utente) ) {
			HttpSession session = request.getSession(true);
			session.setAttribute("utente", utente);
			
			msg.setStatus("Auth");
			msg.setMessaggio("Auth");
			return msg;
		}
		
		msg.setStatus("nonAuth");
		msg.setMessaggio("Modifiche non salvate.");
		
		return msg;
	}
	
	@PostMapping("/checkUsername")
	@ResponseBody
	public Messaggi checkUsername(@RequestParam("newUsername") String newUsername, HttpServletRequest request) {
		
		Messaggi msg = new Messaggi();
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
				
		if ( utente.getUsername().equals(newUsername) ) {
			msg.setStatus("exist");
			msg.setMessaggio("Non hai modificato il tuo nome utente.");
			return msg;
		} else if ( Database.getInstance().getFactory().getUtenteDao().findByUnique(newUsername) != null ) {
			msg.setStatus("exist");
			msg.setMessaggio("Nome utente esistente.");
			return msg;
		}
		
		msg.setStatus("nonExist");
		msg.setMessaggio("");
		
		return msg;
	}

	
	
	@PostMapping("/password")
	@ResponseBody
	public Messaggi verifyPassword(@RequestParam("newPassword") String newPassword, HttpServletRequest request) {
		
		Messaggi msg = new Messaggi();
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		
		utente.setPassword(newPassword);
		
		if ( Database.getInstance().getFactory().getUtenteDao().updatePassword(utente) ) {
			HttpSession session = request.getSession(true);
			session.setAttribute("utente", utente);
			
			msg.setStatus("Auth");
			msg.setMessaggio("Auth");
			return msg;
		}
		
		msg.setStatus("nonAuth");
		msg.setMessaggio("Modifiche non salvate.");
		
		return msg;
	}
	
	@PostMapping("/verifyPassword")
	@ResponseBody
	public Messaggi setPassword(@RequestParam String password, HttpServletRequest request) {
		
		Messaggi msg = new Messaggi();
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		Utente checkedUtente = Database.getInstance().getFactory().getUtenteDao().checkByUniqueAndPassword(utente.getUsername(), password);
		
		if ( checkedUtente != null ) {
			msg.setStatus("Auth");
			msg.setMessaggio("Auth");
			return msg;
		}
		
		msg.setStatus("nonAuth");
		msg.setMessaggio("Controlla la tua password");
		
		return msg;
	}
	
	
	@GetMapping("/orderDetails")
	@ResponseBody
	public HashMap<Prodotto, Integer> getOrderDetail(@RequestParam int order, HttpServletRequest request) {
		System.out.println(order);
		
		HashMap<Prodotto, Integer> prod = Database.getInstance().getFactory().getOrdineDao().findProductsOfOrder(order);

		return prod;
	}
	
	
	
	
	
	
	
	
	@GetMapping("/getAddress")
	@ResponseBody
	public Indirizzo getAddress(@RequestParam String addressId, HttpServletRequest request) {

		int id = Integer.parseInt(addressId);
		
		Indirizzo indirizzo =  Database.getInstance().getFactory().getIndirizzoDao().findByPrimaryKey(id);
	
		return indirizzo;
	}
	
	@PostMapping("/deleteAddress")
	@ResponseBody
	public Messaggi deleteAddress(@RequestParam String addressId, HttpServletRequest request) {

		int id = Integer.parseInt(addressId);
		
		Messaggi msg = new Messaggi();
			
		if ( Database.getInstance().getFactory().getIndirizzoDao().deleteAddress(id) ) {
			msg.setStatus("Auth");
			msg.setMessaggio("Auth");
			return msg;
		}
		
		msg.setStatus("nonAuth");
		msg.setMessaggio("Si e' verificato un problema. L'indirizzo non e' stato rimosso.");
		
		return msg;
	}
	
	@PostMapping("/saveAddres")
	@ResponseBody
	public Messaggi saveAddress(@RequestParam("id") int id, @RequestParam("via") String via, @RequestParam("num") String num, 
							  @RequestParam("cap") String cap, @RequestParam("city") String city, 
							  @RequestParam("prov") String prov, @RequestParam("tel") String tel,
							  HttpServletRequest request) {
		
		Messaggi msg = new Messaggi();
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		
		Indirizzo ind = new Indirizzo();
		
		ind.setId(id);
		ind.setIndirizzo(via);
		ind.setN_civico(num);
		ind.setCap(cap);
		ind.setCitta(city);
		ind.setProvincia(prov);
		ind.setTelefono(tel);
		ind.setMail(utente.getMail());
		ind.setAttivo(true);
		
		if (  Database.getInstance().getFactory().getIndirizzoDao().saveOrUpdate(ind) ) {
			msg.setStatus("Auth");
			msg.setMessaggio("Auth");
			return msg;
		}
		
		msg.setStatus("nonAuth");
		msg.setMessaggio("Modifiche non salvate.");
		
		return msg;
	}
	

}
