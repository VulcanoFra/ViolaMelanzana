package application.persistenza.dao;

import java.util.HashMap;
import java.util.List;

import application.model.Ordine;
import application.model.Prodotto;

public interface OrdineDao {
	public List<Ordine> findAll();
	public Ordine findByPrimaryKey(int nome);
	public boolean saveOrUpdate(Ordine ordine);
	public boolean delete(Ordine ordine);
	
	public Ordine findCurrentFromUser(String mail);
	public int findCurrentIdFromUser(String mail);	//CurrentId sarebbe l'id dell'ordine che non è stato ancora processato, che sarà unico
	public int createOrderId(String mail);
	public boolean newOrder (String mail,List<Prodotto> prodotti) ;
	public boolean updateQuantita(int id_ordine, String nome_prodotto, int nuova_quantita);
	public boolean changeState(String mail, String status);
	public boolean addDate(String mail);
	public boolean deleteProduct(String name, String mail);
	public List<Ordine> findAllOrdersFromUser(String key);
	
	public HashMap<Prodotto, Integer>findProductsOfOrder(int id);
	
	public List<Ordine> OrdiniInConsegna();

}