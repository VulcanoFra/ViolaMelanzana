package application.persistenza.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

import application.model.Amministratore;
import application.model.Ingrediente;
import application.persistenza.dao.AmministratoreDao;

public class AmministratoreDaoJDBC implements AmministratoreDao {

	private Connection conn;
	
	public AmministratoreDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public List<Amministratore> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ingrediente findByPrimaryKey(String mail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveOrUpdate(Amministratore admin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Amministratore admin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Amministratore findByUnique(String username) {
		String query = "select * from amministratore where username=?";
		Amministratore admin = null;
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			
			pr.setString(1, username);
			
			ResultSet rs = pr.executeQuery();
			
			if(rs.next()) {
				admin = new Amministratore();
				admin.setMail(rs.getString("mail"));
				admin.setUsername(rs.getString("username"));
				admin.setNome(rs.getString("nome"));
				admin.setCognome(rs.getString("cognome"));
				admin.setPassword(rs.getString("password"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public Amministratore checkByUniqueAndPassword(String username, String password) {
		String query = "select * from amministratore where username=?";
		Amministratore admin = null;
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			
			pr.setString(1, username);
			
			ResultSet rs = pr.executeQuery();
			
			if(rs.next()) {
				if(BCrypt.checkpw(password, rs.getString("password"))) {
					admin = new Amministratore();
					admin.setMail(rs.getString("mail"));
					admin.setUsername(rs.getString("username"));
					admin.setNome(rs.getString("nome"));
					admin.setCognome(rs.getString("cognome"));
					admin.setPassword(rs.getString("password"));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

}
