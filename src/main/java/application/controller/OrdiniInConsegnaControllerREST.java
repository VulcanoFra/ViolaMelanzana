package application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.model.Ordine;
import application.persistenza.Database;
import application.utilities.Messaggi;

@RestController
public class OrdiniInConsegnaControllerREST {
	@PostMapping("getOrdineDaVisualizzare")
	public Messaggi getOrdineDaVisualizzare(@RequestBody int id,HttpServletRequest req, HttpServletResponse resp) {
		Ordine ordine = Database.getInstance().getFactory().getOrdineDao().findByPrimaryKey(id);
		Messaggi messaggio = new Messaggi();
		if(ordine != null) {
			req.getSession().setAttribute("ordine_visual", ordine);
			messaggio.setStatus("OK");
			messaggio.setMessaggio("Ordine recuperato");
		}else {
			resp.setStatus(500);
			messaggio.setStatus("Fail");
			messaggio.setMessaggio("Ordine non recuperato");
		}
		return messaggio;
	}
}
