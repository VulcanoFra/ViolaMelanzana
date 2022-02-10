package application.model;

import java.util.Objects;

public class Quantita {
	
	private int quantita;
	private String unitaDiMisura;
	
	public Quantita() {}
	
	public Quantita(int quantita, String unitaDiMisura) {
		super();
		this.quantita = quantita;
		this.unitaDiMisura = unitaDiMisura;
	}
	
	public int getQuantita() {
		return quantita;
	}
	
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	public String getUnitaDiMisura() {
		return unitaDiMisura;
	}
	
	public void setUnitaDiMisura(String unitaDiMisura) {
		this.unitaDiMisura = unitaDiMisura;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(quantita, unitaDiMisura);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quantita other = (Quantita) obj;
		return quantita == other.quantita && Objects.equals(unitaDiMisura, other.unitaDiMisura);
	}
	
	@Override
	public String toString() {
		return quantita + "  " + unitaDiMisura;
	}
}
