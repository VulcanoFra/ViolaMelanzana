package application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.model.Ordine;
import application.model.Prodotto;
import application.model.ProdottoQuantita;
import application.persistenza.Database;
import application.utilities.StatoOrdine;
import application.utilities.Messaggi;

@RestController
public class CartControllerREST {
	
	@RequestMapping(value = "/changeStatus", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Messaggi saveOrdine(@RequestBody ProdottoQuantita prodotto_quantita, HttpServletResponse resp, HttpServletRequest req) {
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		Messaggi messaggio = new Messaggi();
		
		Ordine ordine = Database.getInstance().getFactory().getOrdineDao().findCurrentFromUser( utenteLoggato.getUtente(req).getMail());
		
		ordine.setStato(StatoOrdine.IN_ATTESA_DELL_INDIRIZZO);
		ordine.setTotale(prodotto_quantita.getPrezzo());
		if (!utenteLoggato.isNull(req) && Database.getInstance().getFactory().getOrdineDao().saveOrUpdate(ordine)) {
			for(var prodotto : prodotto_quantita.getProdotto_quantita().keySet()) {
				Prodotto pro = Database.getInstance().getFactory().getProdottoDao().findByPrimaryKey(prodotto);
				ordine.getProdottiInOrder().put(pro, prodotto_quantita.getProdotto_quantita().get(prodotto));
				Database.getInstance().getFactory().getOrdineDao().updateQuantita(ordine.getId(), prodotto, ordine.getProdottiInOrder().get(pro));
			}
			messaggio.setStatus("OK");
			messaggio.setMessaggio("Ordine aggiornato sul DB con successo");
			req.getSession().setAttribute("ordine", ordine);
		}else {
			resp.setStatus(500);
			messaggio.setStatus("Fail");
			messaggio.setMessaggio("Ordine non aggiornato sul DB con successo");
		}
		return messaggio;
	}
	
	@PostMapping("/deleteProduct")
	public Messaggi deleteP(@RequestParam String nome_prodotto, HttpServletResponse resp, HttpServletRequest req){
		UtenteControlloLog utenteLoggato = new UtenteControlloLog();
		Messaggi messaggio = new Messaggi();
		if (!utenteLoggato.isNull(req) && Database.getInstance().getFactory().getOrdineDao().deleteProduct(nome_prodotto, utenteLoggato.getUtente(req).getMail())) {
			messaggio.setStatus("OK");
			messaggio.setMessaggio("Prodotto eliminato dall'ordine con successo");
		}else {
			resp.setStatus(500);
			messaggio.setStatus("Fail");
			messaggio.setMessaggio("Prodotto non eliminato dall'ordine");
		}
		return messaggio;
	}
}
