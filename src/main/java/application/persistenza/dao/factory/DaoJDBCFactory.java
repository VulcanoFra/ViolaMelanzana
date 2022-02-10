package application.persistenza.dao.factory;

import application.persistenza.Database;
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
import application.persistenza.dao.jdbc.AmministratoreDaoJDBC;
import application.persistenza.dao.jdbc.CategoriaDaoJDBC;
import application.persistenza.dao.jdbc.ChefDaoJDBC;
import application.persistenza.dao.jdbc.CommentoDaoJDBC;
import application.persistenza.dao.jdbc.IdBroker;
import application.persistenza.dao.jdbc.IndirizzoDaoJDBC;
import application.persistenza.dao.jdbc.IngredienteDaoJDBC;
import application.persistenza.dao.jdbc.LikesRicettaDaoJDBC;
import application.persistenza.dao.jdbc.OrdineDaoJDBC;
import application.persistenza.dao.jdbc.ProdottoDaoJDBC;
import application.persistenza.dao.jdbc.RaccoltaDaoJDBC;
import application.persistenza.dao.jdbc.RicettaDaoJDBC;
import application.persistenza.dao.jdbc.SegnalazioniRicettaDaoJDBC;
import application.persistenza.dao.jdbc.UtenteDaoJDBC;

public class DaoJDBCFactory implements DaoAbstractFactory{

	@Override
	public UtenteDao getUtenteDao() {
		return new UtenteDaoJDBC(Database.getInstance().getConn());
	}

	@Override
	public ProdottoDao getProdottoDao() {
		return new ProdottoDaoJDBC(Database.getInstance().getConn());
	}

	@Override
	public RicettaDao getRicettaDao() {
		return new RicettaDaoJDBC(Database.getInstance().getConn());
	}

	@Override
	public CommentoDao getCommentoDao() {
		return new CommentoDaoJDBC(Database.getInstance().getConn());
	}

	@Override
	public LikesRicettaDao getLikesRicettaDao() {
		return new LikesRicettaDaoJDBC(Database.getInstance().getConn());
	}

	@Override
	public RaccoltaDao getRaccoltaDao() {
		return new RaccoltaDaoJDBC(Database.getInstance().getConn());

	}

	@Override
	public OrdineDao getOrdineDao() {
		return new OrdineDaoJDBC(Database.getInstance().getConn());

	}
	@Override
	public IndirizzoDao getIndirizzoDao() {
		return new IndirizzoDaoJDBC(Database.getInstance().getConn());

	}

	@Override
	public SegnalazioniRicettaDao getSegnalazioniRicettaDao() {
		return new SegnalazioniRicettaDaoJDBC(Database.getInstance().getConn());
	}

	@Override
	public ChefDao getChefDao() {
		return new ChefDaoJDBC(Database.getInstance().getConn());
	}

	@Override
	public CategoriaDao getCategoriaDao() {
		return new CategoriaDaoJDBC(Database.getInstance().getConn());

	}

	@Override
	public IngredienteDao getIngredienteDao() {
		return new IngredienteDaoJDBC(Database.getInstance().getConn());
	}

	@Override
	public AmministratoreDao getAmministratoreDao() {
		return new AmministratoreDaoJDBC(Database.getInstance().getConn());	}

}
