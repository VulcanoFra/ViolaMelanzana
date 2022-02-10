package application.persistenza.dao;

import java.util.List;

import application.model.Chef;

public interface ChefDao {
	
	public List<Chef> findAll();
	public Chef findByPrimaryKey(int id);
	public boolean saveOrUpdate(Chef chef);
	public boolean delete(Chef chef);
	public List<Chef> findOrderBy(int limit, int offset, boolean admin);
}
