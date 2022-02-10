package application.persistenza.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.model.Ingrediente;
import application.model.Prodotto;
import application.persistenza.dao.ProdottoDao;

public class ProdottoDaoJDBC implements ProdottoDao{

	private Connection conn;
	
	public ProdottoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public List<Prodotto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prodotto findByPrimaryKey(String nome) {
		String query ="SELECT * from prodotto WHERE nome=?;";
		Prodotto prodotto= new Prodotto();
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, nome);
			ResultSet rs = pr.executeQuery();
			if(rs.next()) {
				Ingrediente ingrediente= new Ingrediente(rs.getString("ingrediente"));
				prodotto.setNome(nome);
				prodotto.setIngrediente(ingrediente);
				prodotto.setPrezzo(rs.getDouble("prezzo"));
				prodotto.setQuantitaDisponibile(rs.getInt("quantita_disponibile"));
				prodotto.setTaglio(rs.getInt("taglio"));
				prodotto.setUnitaDiMisura(rs.getString("unita_di_misura"));
			}
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return prodotto;
	}

	@Override
	public boolean saveOrUpdate(Prodotto prodotto) {
		String query;
			try {
				query = "UPDATE prodotto "
						+ "SET quantita_disponibile=?, prezzo=?, ingrediente=? "
						+ "WHERE nome=?";
				PreparedStatement st = conn.prepareStatement(query);
				
				st.setInt(1, prodotto.getQuantitaDisponibile());
				st.setDouble(2, prodotto.getPrezzo());
				if(prodotto.getIngrediente() != null)
					st.setString(3, prodotto.getIngrediente().getNome());
				else st.setString(3, null);
				st.setString(4, prodotto.getNome());
				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		return true;
	}

	@Override
	public boolean delete(Prodotto prodotto) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<Prodotto> findProductByRecipeId(int id) {
		List<Prodotto> prodotti=new ArrayList<Prodotto>();
		String query ="SELECT * from contiene WHERE id_ricetta=?;";
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			while(rs.next()) {
				Prodotto prodotto= this.findByPrimaryKey(rs.getString("nome_ingrediente"));
				if ( prodotto!= null) {
					int quantita=rs.getInt("quantita");
					int quantitaAcquistata=0;
					while(quantitaAcquistata < quantita && prodotto.getQuantitaDisponibile()>0) {					
							prodotti.add(prodotto);
							quantitaAcquistata+= prodotto.getTaglio();
					}
				}
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return prodotti;
		
	}

}
