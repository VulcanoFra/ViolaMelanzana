package application.rest;

import application.model.Indirizzo;

public class prendiIndirizzo {
	private String status;
	private Indirizzo indirizzo;
	private String messaggio;
	
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	public Indirizzo getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
