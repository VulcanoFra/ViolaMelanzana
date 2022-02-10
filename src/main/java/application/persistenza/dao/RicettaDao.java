package application.persistenza.dao;

import java.util.ArrayList;
import java.util.List;

import application.model.IngredienteQuantita;
import application.model.Ricetta;
import application.model.RicettaProxy;
import application.model.Utente;

public interface RicettaDao {

	public Ricetta findByPrimaryKey(int id);

	public ArrayList<IngredienteQuantita> findIngredientByRecipeId(int id);
	public List<RicettaProxy> findAll();
	public boolean deleteRequest(int idRicetta,String mailUtente,String motivazione);

	
	public List<RicettaProxy> findByPublisher(String key);
	public List<RicettaProxy> findByCategory(String key);
	public List<RicettaProxy> findOrderBy(String expression, int limit, int offset);
	public List<RicettaProxy> findByPublisher(String key, int limit, int offset);	
	public List<RicettaProxy> findPublishedBy(String key);
	public List<RicettaProxy> search(String query, String filter);
	public boolean save(Ricetta ricetta);
	public boolean update(Ricetta ricetta);

	public boolean deleteRecipeFormChef(int ricetta, int chef);
	
	public boolean updateDeleteRequest(int id_ricetta,boolean accettata);
	public List<Ricetta> getWithBan();
	public boolean delete(int id_ricetta);
	public boolean update(int id_ricetta,boolean approvazione);

	
}
