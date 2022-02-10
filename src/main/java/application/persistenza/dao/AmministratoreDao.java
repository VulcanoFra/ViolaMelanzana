package application.persistenza.dao;

import java.util.List;

import application.model.Amministratore;
import application.model.Ingrediente;

public interface AmministratoreDao {
	
	public List<Amministratore> findAll();
	public Ingrediente findByPrimaryKey(String mail);
	public boolean saveOrUpdate(Amministratore admin);
	public boolean delete(Amministratore admin);

	public Amministratore findByUnique(String username);
	public Amministratore checkByUniqueAndPassword(String username, String password);
	
}
