package application.model;

import java.util.Map;

public class ProdottoQuantita {
	private Map<String, Integer> prodotto_quantita;
	private Float prezzo;
	
	public ProdottoQuantita() {

	}
	
	public ProdottoQuantita(Map<String, Integer> prodotto_quantita, Float prezzo) {
		this.prodotto_quantita = prodotto_quantita;
		this.prezzo = prezzo;
	}
	
	public Map<String, Integer> getProdotto_quantita(){
		return prodotto_quantita;
	}

	public Float getPrezzo() {
		return prezzo;
	}
}
