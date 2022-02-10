package application.rest;

import application.model.Ordine;

public class prendiOrdine {
	private String status;
	private Ordine ordine;
	private String messaggio;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Ordine getOrdine() {
		return ordine;
	}
	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
}
