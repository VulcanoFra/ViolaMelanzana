package application.persistenza.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.model.Chef;
import application.persistenza.dao.ChefDao;

public class ChefDaoJDBC implements ChefDao {
	
	private Connection conn;
	
	public ChefDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Chef> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chef findByPrimaryKey(int id) {
		Chef chef = null;
		
		String query = "select * from chef where id=?";
		
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			
			if(rs.next()) {
				chef = new Chef();
				chef.setId(rs.getInt("id"));
				chef.setNome(rs.getString("nome"));
				chef.setCognome(rs.getString("cognome"));
				chef.setData(rs.getDate("data"));
				chef.setDescrizione(rs.getString("descrizione"));
				chef.setImg_link(rs.getString("img_link"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chef;
	}

	@Override
	public boolean saveOrUpdate(Chef chef) {
		String query = "insert into chef values(?, ?, ?, now(), ?, ?)";
	
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setInt(1, chef.getId());
			pr.setString(2, chef.getNome());
			pr.setString(3, chef.getCognome());
		
			pr.setString(4, chef.getDescrizione());
			pr.setString(5, chef.getImg_link());
		
			pr.executeUpdate();
		
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Chef chef) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<Chef> findOrderBy(int limit, int offset, boolean admin) {
		ArrayList<Chef> chef = null;
		
		String query;
		
		if ( admin ) {
			query ="select chef.id, chef.nome, chef.cognome, chef.data, chef.descrizione, chef.img_link "
				+ "from chef order by data desc limit ? offset ?;";
		} else {
			query ="select chef.id, chef.nome, chef.cognome, chef.data, chef.descrizione, chef.img_link "
				+ "from chef inner join ricetta on chef.id = chef order by data desc limit ? offset ?;";
		}
		
		PreparedStatement ps;
		try {
			
			chef = new ArrayList<Chef>();
			
			ps = conn.prepareStatement(query);
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Chef c = new Chef();
				
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setCognome(rs.getString("cognome"));
				c.setData(rs.getDate("data"));
				c.setDescrizione(rs.getString("descrizione"));
				c.setImg_link(rs.getString("img_link"));
				
				chef.add(c);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return chef;
	}

}
