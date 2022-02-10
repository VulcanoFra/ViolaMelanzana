package application.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Commento {

	private int id_commento;
	private String contenuto;
	private Utente pubblicatore;
	private int id_ricetta;
	private Timestamp data_pubblicazione;
	
	public Commento(int id_commento,int id, Utente pubblicatore , String contenuto,Timestamp data_pubblicazione) {
		this.id_commento=id_commento;
		this.id_ricetta=id;
		this.pubblicatore=pubblicatore;
		this.contenuto=contenuto;
		this.data_pubblicazione=data_pubblicazione;
	}
	public Commento() {;}
	
	public int getIdCommento() {
		return id_commento;
	}
	
	public void setIdCommento(int id_commento) {
		this.id_commento = id_commento;
	}
	
	public Timestamp getData() {
		return data_pubblicazione;
	}
	
	public void setData(Timestamp timestamp) {
		this.data_pubblicazione= timestamp;
	}
	
	
	public int getIdRicetta() {
		return id_ricetta;
	}
	
	public void setIdRicetta(int id_ricetta) {
		this.id_ricetta =id_ricetta;
	}
	
	public String getContenuto() {
		return contenuto;
	}
	
	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}
	
	public Utente getPubblicatore() {
		return pubblicatore;
	}
	
	public void setPubblicatore(Utente pubblicatore) {
		this.pubblicatore = pubblicatore;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_commento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commento other = (Commento) obj;
		return id_commento == other.id_commento;
	}
	
	
}
