package application.model;

import java.util.ArrayList;

public class Raccolta {
	private String nome;
	private ArrayList<Ricetta> ricetteRaccolta;
	private String mail_utente;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ArrayList<Ricetta> getRicetteRaccolta() {
		return ricetteRaccolta;
	}
	public void setRicetteRaccolta(ArrayList<Ricetta> ricetteRaccolta) {
		this.ricetteRaccolta = ricetteRaccolta;
	}
	
	public String getMailUtente() {
		return mail_utente;
	}
	public void setMailUtente(String mail_utente) {
		this.mail_utente = mail_utente;
	}
	
	
}
