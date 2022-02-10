package application.persistenza.dao;

import java.util.List;

import application.model.Prodotto;

public interface ProdottoDao {

	public List<Prodotto> findAll();
	public Prodotto findByPrimaryKey(String nome);
	public boolean saveOrUpdate(Prodotto prodotto);
	public boolean delete(Prodotto prodotto);
	public List<Prodotto> findProductByRecipeId(int idRicetta) ;
}
