package application.controller;

import javax.servlet.http.HttpServletRequest;

import application.model.Utente;

public class UtenteControlloLog {
	
	public boolean isNull(HttpServletRequest req) {
		Utente utente = (Utente) req.getSession().getAttribute("utente");
		return utente == null;
	}
	
	public Utente getUtente(HttpServletRequest req) {
		return (Utente) req.getSession().getAttribute("utente");
	}
}
