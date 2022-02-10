package application.persistenza.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.model.Categoria;
import application.persistenza.dao.CategoriaDao;

public class CategoriaDaoJDBC implements CategoriaDao {
	
	private Connection conn;
	
	public CategoriaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Categoria> findAll() {
		ArrayList<Categoria> categorie = null;

		String query ="select * from categoria order by id";
		
		try {
			categorie = new ArrayList<Categoria>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Categoria c = new Categoria();
				
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				
				categorie.add(c);
				
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return categorie;
	}
	
	@Override
	public ArrayList<Categoria> findByResearchKey(String researchKey) {
		String query = "select * from categoria where nome LIKE ?";
		ArrayList<Categoria> categorie = new ArrayList<Categoria>();
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, "%" + researchKey + "%");
			
			ResultSet rs = pr.executeQuery();
			
			while(rs.next()) {
				
				Categoria c = new Categoria();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				
				categorie.add(c);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}

	@Override
	public Categoria findByPrimaryKey(int id) {
		
		Categoria cat = null;
		
		String query = "select * from categoria where id=?";
		
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setInt(1, id);
			
			ResultSet rs = pr.executeQuery();
			
			while(rs.next()) {
				
				cat = new Categoria();
				cat.setId(rs.getInt("id"));
				cat.setNome(rs.getString("nome"));
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return cat;
	}

	@Override
	public boolean saveOrUpdate(Categoria prodotto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Categoria prodotto) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int findIdCategoriaByName(String nome) {
		String query = "select * from categoria where nome=?";
		
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, nome);
			ResultSet rs = pr.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("id");
				return id;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public boolean saveCategoriesOfRecipe(int idRicetta, ArrayList<String> categorieRicetta) {
		if (categorieRicetta == null)
			return false;
		
		for (String categoria : categorieRicetta) {
			int idCategoria = findIdCategoriaByName(categoria);
			if (idCategoria == 0)
				return false;
			String query = "insert into ricetta_categoria values(?, ?)";
			try {
				PreparedStatement pr = conn.prepareStatement(query);
				pr.setInt(1, idRicetta);
				pr.setInt(2, idCategoria);
				pr.executeUpdate();
			} catch (SQLException e) {
				// TODO
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}
