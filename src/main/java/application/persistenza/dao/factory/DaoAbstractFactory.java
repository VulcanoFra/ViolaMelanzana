package application.persistenza.dao.factory;

import application.persistenza.dao.AmministratoreDao;
import application.persistenza.dao.CategoriaDao;
import application.persistenza.dao.ChefDao;
import application.persistenza.dao.CommentoDao;
import application.persistenza.dao.IndirizzoDao;
import application.persistenza.dao.IngredienteDao;
import application.persistenza.dao.LikesRicettaDao;
import application.persistenza.dao.OrdineDao;
import application.persistenza.dao.ProdottoDao;
import application.persistenza.dao.RaccoltaDao;
import application.persistenza.dao.RicettaDao;
import application.persistenza.dao.SegnalazioniRicettaDao;
import application.persistenza.dao.UtenteDao;

public interface DaoAbstractFactory {

	public UtenteDao getUtenteDao(); 
	public ProdottoDao getProdottoDao();
	public RicettaDao getRicettaDao();
	public CommentoDao getCommentoDao();
	public LikesRicettaDao getLikesRicettaDao();
	public RaccoltaDao getRaccoltaDao();
	public OrdineDao getOrdineDao();
	public IndirizzoDao getIndirizzoDao();
	public SegnalazioniRicettaDao getSegnalazioniRicettaDao();
	public ChefDao getChefDao();
	public IngredienteDao getIngredienteDao();
	public AmministratoreDao getAmministratoreDao();
	public CategoriaDao getCategoriaDao();

}
