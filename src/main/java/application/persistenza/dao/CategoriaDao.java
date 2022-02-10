package application.persistenza.dao;

import java.util.ArrayList;
import java.util.List;

import application.model.Categoria;

public interface CategoriaDao {
	
	public List<Categoria> findAll();
	public Categoria findByPrimaryKey(int id);
	public boolean saveOrUpdate(Categoria prodotto);
	public boolean delete(Categoria prodotto);
	public ArrayList<Categoria> findByResearchKey(String researchKey);
	
	public boolean saveCategoriesOfRecipe(int idRicetta, ArrayList<String> categorieRicetta);
	public int findIdCategoriaByName(String nome);
}
