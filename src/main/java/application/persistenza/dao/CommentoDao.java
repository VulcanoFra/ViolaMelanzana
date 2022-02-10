package application.persistenza.dao;

import java.util.ArrayList;
import java.util.List;

import application.model.Commento;

public interface CommentoDao {
	public List<Commento> findAll();
	public boolean save(Commento commento);
	public boolean update(Commento commento);
	public boolean delete(Commento commento);
	ArrayList<Commento> findForRecipe(int id_ricetta);
}
