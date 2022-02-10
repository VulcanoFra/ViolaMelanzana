package application.model;

import java.util.Objects;

public class Prodotto {
	
	private String nome;
	private int quantitaDisponibile;
	private double prezzo;
	private Ingrediente ingrediente;
	private int taglio;
	private String unitaDiMisura;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getUnitaDiMisura() {
		return unitaDiMisura;
	}
	
	public void setUnitaDiMisura(String unitaDiMisura) {
		this.unitaDiMisura = unitaDiMisura;
	}
	

	public int getTaglio() {
		return taglio;
	}
	
	public void setTaglio(int taglio) {
		this.taglio = taglio;
	}
	
	public int getQuantitaDisponibile() {
		return quantitaDisponibile;
	}
	
	public void setQuantitaDisponibile(int quantita_disponibile) {
		this.quantitaDisponibile = quantita_disponibile;
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prodotto other = (Prodotto) obj;
		return Objects.equals(nome, other.nome);
	}
	
	
	
}
