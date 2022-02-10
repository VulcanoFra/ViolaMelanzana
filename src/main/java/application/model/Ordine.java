package application.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.Objects;

public class Ordine {

	private int id;
	private Utente utenteOrdine;
	private Date data;
	private String stato;
	private float totale;
	private Indirizzo indirizzo;
	//Associa ad ogni prodotto nell'ordine, la quantità voluta
	private HashMap<Prodotto, Integer> prodottiInOrder;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Utente getUtenteOrdine() {
		return utenteOrdine;
	}
	
	public void setUtenteOrdine(Utente utenteOrdine) {
		this.utenteOrdine = utenteOrdine;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getStato() {
		return stato;
	}
	
	public void setStato(String stato) {
		this.stato = stato;
	}

	public HashMap<Prodotto, Integer> getProdottiInOrder() {
		return prodottiInOrder;
	}
	
	public void setProdottiInOrder(HashMap<Prodotto, Integer> prodottiInOrder) {
		this.prodottiInOrder = prodottiInOrder;
	}
	
	public void setTotale(float totale) {
		this.totale = totale;
	}
	
	public float getTotale() {
		return totale;
	}
	
	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordine other = (Ordine) obj;
		return id == other.id;
	}
	
	
	
}
