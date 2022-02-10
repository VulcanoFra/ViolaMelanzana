package application.persistenza.dao;

import java.util.ArrayList;
import java.util.List;

import application.model.Ingrediente;
import application.model.IngredienteQuantita;


public interface IngredienteDao {

	public List<Ingrediente> findAll();
	public Ingrediente findByPrimaryKey(int id);
	public boolean saveOrUpdate(Ingrediente ingrediente);
	public boolean delete(Ingrediente ingrediente);

	public ArrayList<IngredienteQuantita> findByRecipe(int idRicetta);
	public boolean saveIngredientOfRecipe(int id, ArrayList<IngredienteQuantita> listaIngredientiConQuantita);
}
