package application.persistenza.dao;

import java.util.List;

import application.model.Indirizzo;

public interface IndirizzoDao {
	public List<Indirizzo> findAll();
	public Indirizzo findByPrimaryKey(int nome);
	public boolean saveOrUpdate(Indirizzo indirizzo);
	public boolean delete(int id);
	public List<Indirizzo> findAllFromUserEnable(String mail);
	
	public boolean deleteAddress(int mail);
}