package application.model;

public class IngredienteQuantita {
	private Ingrediente ingrediente;
	private Quantita quantita;
	
	public IngredienteQuantita() {}
	
	public IngredienteQuantita(Ingrediente ingrediente, Quantita quantita) {
		super();
		this.ingrediente = ingrediente;
		this.quantita = quantita;
	}
	
	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	public Quantita getQuantita() {
		return quantita;
	}
	public void setQuantita(Quantita quantita) {
		this.quantita = quantita;
	}
	
	@Override
	public String toString() {
		return ingrediente + "  " + quantita;
	}
	
}
