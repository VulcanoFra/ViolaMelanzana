package application.persistenza.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import application.model.Ingrediente;
import application.model.NomiSequenze;
import application.model.Ordine;
import application.model.Prodotto;
import application.persistenza.Database;
import application.persistenza.dao.OrdineDao;
import application.utilities.StatoOrdine;

public class OrdineDaoJDBC implements OrdineDao{
	
	private Connection conn;
	
	public OrdineDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Ordine> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ordine findByPrimaryKey(int id) {
		Ordine ordine = null;
		String query = "SELECT * from ordine where id=?";
		
		try {
			ordine = new Ordine();
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				HashMap<Prodotto, Integer> prodotti = new HashMap<Prodotto, Integer>();
				ordine.setId(rs.getInt("id"));
				ordine.setUtenteOrdine(Database.getInstance().getFactory().getUtenteDao().findByPrimaryKey(rs.getString("utente")));
				ordine.setData(rs.getDate("data_completamento"));
				ordine.setTotale(rs.getFloat("totale"));
				ordine.setIndirizzo(Database.getInstance().getFactory().getIndirizzoDao().findByPrimaryKey(rs.getInt("id_indirizzo")));
				ordine.setStato(rs.getString("stato"));
				String queryProdotti = "SELECT * "
				+ "FROM incluso as i, prodotto as p "
				+ "WHERE p.nome = i.nome_prodotto and i.id_ordine=?";
				PreparedStatement stProdotti = conn.prepareStatement(queryProdotti);
				stProdotti.setInt(1, id);
				ResultSet rsProdotti = stProdotti.executeQuery();
				
				while(rsProdotti.next()) {
					Prodotto prodotto = new Prodotto();
					prodotto.setNome(rsProdotti.getString("nome"));
					prodotto.setQuantitaDisponibile(rsProdotti.getInt("quantita_disponibile"));
					prodotto.setPrezzo(rsProdotti.getDouble("prezzo"));
					prodotto.setIngrediente(new Ingrediente(rsProdotti.getString("ingrediente")));
					prodotto.setTaglio(rsProdotti.getInt("taglio"));
					prodotto.setUnitaDiMisura(rsProdotti.getString("unita_di_misura"));
					prodotti.put(prodotto, rsProdotti.getInt("quantita"));
				}
				ordine.setProdottiInOrder(prodotti);
			}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return ordine;
	}
	
	@Override
	public boolean saveOrUpdate(Ordine ordine) {
		if(ordine.getId() == 0) {
			String query = "INSERT INTO ordine "
					+ "VALUES(?,?,?,?,?,?)";
			try {
				PreparedStatement st = conn.prepareStatement(query);
				st.setInt(1, ordine.getId());
				if(ordine.getData() != null)
					st.setDate(2,  new java.sql.Date(ordine.getData().getTime()));
				else st.setDate(2,  null);
				if(ordine.getUtenteOrdine() != null)
					st.setString(3, ordine.getUtenteOrdine().getMail());
				st.setString(4, ordine.getStato());
				if(ordine.getIndirizzo() != null)
					st.setInt(5, ordine.getIndirizzo().getId());
				st.setDouble(6, ordine.getTotale());
				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		else {
			try {
				String query = "UPDATE ordine "
						+ "SET data_completamento=?, utente=?, stato=?, id_indirizzo=?, totale=? "
						+ "WHERE id=?";
				PreparedStatement st = conn.prepareStatement(query);
				
				if(ordine.getData() != null)
					st.setDate(1,  new java.sql.Date(ordine.getData().getTime()));
				else st.setDate(1,  null);
				if(ordine.getUtenteOrdine() != null)
					st.setString(2, ordine.getUtenteOrdine().getMail());
				st.setString(3, ordine.getStato());
				if(ordine.getIndirizzo() != null)
					st.setInt(4, ordine.getIndirizzo().getId());
				else st.setNull(4, java.sql.Types.INTEGER);
				st.setDouble(5, ordine.getTotale());
				st.setInt(6, ordine.getId());
				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}


	@Override
	public boolean delete(Ordine ordine) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean updateQuantita(int id_ordine, String nome_prodotto, int nuova_quantita) {
		String query = "UPDATE incluso SET quantita=? WHERE id_ordine=? AND nome_prodotto=?";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, nuova_quantita);
			st.setInt(2, id_ordine);
			st.setString(3, nome_prodotto);
			st.executeUpdate();
				 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public List<Ordine> findAllOrdersFromUser(String key){
		
		ArrayList<Ordine> ordini = null;
		
		String query ="select * from ordine where utente=? and data_completamento is not null order by data_completamento desc;";
		PreparedStatement ps;
		try {
			
			ordini = new ArrayList<Ordine>();
			
			ps = conn.prepareStatement(query);
			ps.setString(1, key);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Ordine o = new Ordine();
				
				o.setId(rs.getInt("id"));
				o.setData(rs.getDate("data_completamento"));
				o.setStato(rs.getString("stato"));
				o.setTotale(rs.getFloat("totale"));
				ordini.add(o);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ordini;
	}

	@Override
	public Ordine findCurrentFromUser(String mail) {
		return findByPrimaryKey(findCurrentIdFromUser(mail));
	}
	

	@Override
	public int findCurrentIdFromUser(String mail) {
		String query = "SELECT * from ordine where utente=? and data_completamento IS NULL";
		int id = 0;
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, mail);
			ResultSet rs =st.executeQuery();
			if(rs.next()) {
				id = rs.getInt("id");
			}
			else {
				id=this.createOrderId(mail);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return id;
	}
	
	@Override 
	public int createOrderId(String mail) {
		int id=0;
		try {
			id = IdBroker.getId(conn, NomiSequenze.ORDINE);
			String insert = "INSERT INTO ordine(id,utente,stato) VALUES(?,?,?);";
			PreparedStatement prst = conn.prepareStatement(insert);
			prst.setInt(1,id);
			prst.setString(2, mail);
			prst.setString(3, "non ancora inviato");
			prst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	@Override
	public boolean newOrder (String mail,List<Prodotto> prodotti) {
		try {
			int idOrdine= this.findCurrentIdFromUser(mail);
			for (Prodotto prod: prodotti) {	
				String quantita = "SELECT * from incluso WHERE id_ordine=? AND nome_prodotto=?;";
				PreparedStatement prs = conn.prepareStatement(quantita);
				prs.setInt(1,idOrdine);
				prs.setString(2,prod.getNome());
				ResultSet rs =prs.executeQuery();
				
				if(rs.next()) {
					this.updateQuantita(idOrdine,prod.getNome(), (rs.getInt("quantita"))+1);
				}
				
				else {
					
					String insert = "INSERT INTO incluso VALUES(?,?,?);";
					PreparedStatement prst = conn.prepareStatement(insert);
					prst.setInt(1,idOrdine);
					prst.setString(2,prod.getNome());
					prst.setInt(3,1);
					prst.executeUpdate();
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean changeState(String mail, String status) {
		String query = "update ordine "
				+ "set stato=? "
				+ "where id=?";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, status);
			st.setInt(2, findCurrentIdFromUser(mail));
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean addDate(String mail) {
		String query = "update ordine "
						+ "set data_completamento=? "
						+ "where id=?";
		PreparedStatement st;
		try {
			st = conn.prepareStatement(query);
			st.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			st.setInt(2, findCurrentIdFromUser(mail));
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean deleteProduct(String name, String mail) {
		System.out.println(name);
		System.out.println(findCurrentIdFromUser(mail));
		String query = "DELETE FROM incluso "
				+"WHERE id_ordine=? and nome_prodotto=?";
		PreparedStatement st;
		try {
			st = conn.prepareStatement(query);
			st.setInt(1, findCurrentIdFromUser(mail));
			st.setString(2, name);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public HashMap<Prodotto, Integer> findProductsOfOrder(int id) {
		
		HashMap<Prodotto, Integer> prod = null;
		
		String query = "select * from incluso inner join prodotto on nome=nome_prodotto where id_ordine=?";
		
		PreparedStatement st;
		try {
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			
			ResultSet rs =st.executeQuery();
			
			prod = new HashMap<Prodotto, Integer>();
			
			while (rs.next()) {
				
				Prodotto p = new Prodotto();
				
				p.setNome(rs.getString("nome"));
				p.setQuantitaDisponibile(rs.getInt("quantita_disponibile"));
				p.setPrezzo(rs.getDouble("prezzo"));
				p.setTaglio(rs.getInt("taglio"));
				p.setUnitaDiMisura(rs.getString("unita_di_misura"));
				
				Integer quant = rs.getInt("quantita");
				
				prod.put(p, quant);					
				
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prod;	
	}

	
	@Override
	public List<Ordine> OrdiniInConsegna() {
		List<Ordine> ordini = null;
		try {
			ordini = new ArrayList<Ordine>();
			String query = "SELECT * FROM ordine WHERE stato=? OR stato=? OR stato=? order by id";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, StatoOrdine.IN_ATTESA_DELLA_CONSEGNA_ALLACONSEGNA);
			st.setString(2, StatoOrdine.IN_ATTESA_DELLA_CONSEGNA_PAYPAL);
			st.setString(3, StatoOrdine.SPEDITO);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				Ordine ordine = new Ordine();
				ordine.setId(rs.getInt("id"));
				ordine.setData(rs.getDate("data_completamento"));
				ordine.setUtenteOrdine(Database.getInstance().getFactory().getUtenteDao().findByPrimaryKey(rs.getString("utente")));
				ordine.setStato(rs.getString("stato"));
				ordine.setIndirizzo(Database.getInstance().getFactory().getIndirizzoDao().findByPrimaryKey(rs.getInt("id_indirizzo")));
				ordine.setTotale(rs.getInt("totale"));
				ordini.add(ordine);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ordini;
	}
}