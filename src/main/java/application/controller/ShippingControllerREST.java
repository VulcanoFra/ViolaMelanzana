package application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.model.Indirizzo;
import application.persistenza.Database;
import application.rest.prendiIndirizzo;

@RestController
public class ShippingControllerREST {
	@PostMapping("/getShipping")
	public prendiIndirizzo getIndirizzo(@RequestBody int id,
			HttpServletResponse resp) {
		prendiIndirizzo indirizzo = new prendiIndirizzo();
		Indirizzo ind = Database.getInstance().getFactory().getIndirizzoDao().findByPrimaryKey(id);
		if (ind != null) {
			indirizzo.setStatus("OK");
			indirizzo.setMessaggio("Indirizzo preso dal DB con successo");
			indirizzo.setIndirizzo(ind);
		}else {
			resp.setStatus(500);
			indirizzo.setStatus("Fail");
			indirizzo.setMessaggio("Ricaricare");
			indirizzo.setIndirizzo(null);
		}
		return indirizzo;
}
	
	@PostMapping("/saveShipping")
	public prendiIndirizzo salvaIndirizzo(@RequestBody Indirizzo ind,
			HttpServletResponse resp, HttpServletRequest req) {
		prendiIndirizzo indirizzo = new prendiIndirizzo();
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		if (utenteLoggato.isNull(req)) {
			return null;
		}
		ind.setMail(utenteLoggato.getUtente(req).getMail()); 
		if (Database.getInstance().getFactory().getIndirizzoDao().saveOrUpdate(ind)) {
			
			indirizzo.setStatus("OK");
			indirizzo.setMessaggio("Indirizzo salvato sul DB con successo");
			indirizzo.setIndirizzo(ind);
			
		}else {
			resp.setStatus(500);
			indirizzo.setStatus("Fail");
			indirizzo.setMessaggio("Indirizzo non salvato sul DB");
			indirizzo.setIndirizzo(null);
		}
		return indirizzo;
	}
	
	@PostMapping("/deleteShipping")
	public prendiIndirizzo eliminaIndirizzo(@RequestBody int id,
			HttpServletResponse resp, HttpServletRequest req) {
		prendiIndirizzo indirizzo = new prendiIndirizzo();
		if (Database.getInstance().getFactory().getIndirizzoDao().delete(id)) {
			
			indirizzo.setStatus("OK");
			indirizzo.setMessaggio("Indirizzo eliminato dal DB con successo");
			indirizzo.setIndirizzo(null);
			
		}else {
			resp.setStatus(500);
			indirizzo.setStatus("Fail");
			indirizzo.setMessaggio("Indirizzo non eliminato dal DB");
			indirizzo.setIndirizzo(null);
		}
		return indirizzo;
	}
	
}