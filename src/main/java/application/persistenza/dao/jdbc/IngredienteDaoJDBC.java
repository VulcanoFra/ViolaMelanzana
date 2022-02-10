package application.persistenza.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.model.Ingrediente;
import application.model.IngredienteQuantita;
import application.model.Quantita;
import application.persistenza.dao.IngredienteDao;

public class IngredienteDaoJDBC implements IngredienteDao{

	private Connection conn;
	
	public IngredienteDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public List<Ingrediente> findAll() {
		String query = "select * from ingrediente";
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			ArrayList<Ingrediente> ingredienti = null;
			
			if(rs.next()) {
				 ingredienti = new ArrayList<Ingrediente>();
				 ingredienti.add(new Ingrediente(rs.getString("nome")));
			}
			
			while(rs.next()) {
				ingredienti.add(new Ingrediente(rs.getString("nome")));
			}
			
			return ingredienti;
			
		} catch(SQLException e) {
			
		}
		return null;

	}

	@Override
	public Ingrediente findByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveOrUpdate(Ingrediente ingrediente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Ingrediente ingrediente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<IngredienteQuantita> findByRecipe(int idRicetta) {
		String queryIngredienti = "select * from contiene where id_ricetta=?";
		ArrayList<IngredienteQuantita> lista = new ArrayList<IngredienteQuantita>();
		
		try {
			PreparedStatement pr = conn.prepareStatement(queryIngredienti);
			pr.setInt(1, idRicetta);
			
			ResultSet rs = pr.executeQuery();
			while(rs.next()) {
				Ingrediente i = new Ingrediente(rs.getString("nome_ingrediente"));
				Quantita q = new Quantita(rs.getInt("quantita"), rs.getString("unita_di_misura"));
				
				IngredienteQuantita ingrQua = new IngredienteQuantita(i, q);
				
				lista.add(ingrQua);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public boolean saveIngredientOfRecipe(int idRicetta, ArrayList<IngredienteQuantita> listaIngredientiConQuantita) {
		String query = "INSERT INTO contiene VALUES(?, ?, ?, ?)";
		try {
			for (IngredienteQuantita ing : listaIngredientiConQuantita) {
				PreparedStatement pr = conn.prepareStatement(query);
				pr.setInt(1, idRicetta);
				pr.setString(2, ing.getIngrediente().getNome());
				pr.setInt(3, ing.getQuantita().getQuantita());
				pr.setString(4, ing.getQuantita().getUnitaDiMisura());
				pr.executeUpdate();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
