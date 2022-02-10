package application.persistenza.dao.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.model.Indirizzo;
import application.model.NomiSequenze;
import application.persistenza.dao.IndirizzoDao;

public class IndirizzoDaoJDBC implements IndirizzoDao{

	private Connection conn;
	
	public IndirizzoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public List<Indirizzo> findAll() {
		List<Indirizzo> indirizzi = null;
		
		String query = "SELECT * from indirizzo";
		try {
			indirizzi =new ArrayList<Indirizzo>();
			Statement st = conn.createStatement();
			ResultSet rs =st.executeQuery(query);
			while(rs.next()) {
				Indirizzo indirizzo = new Indirizzo();
				indirizzo.setId(rs.getInt("id"));
				indirizzo.setIndirizzo(rs.getString("indirizzo"));
				indirizzo.setN_civico(rs.getString("n_civico"));
				indirizzo.setCap(rs.getString("cap"));
				indirizzo.setProvincia(rs.getString("provincia"));
				indirizzo.setCitta(rs.getString("citta"));
				indirizzo.setTelefono(rs.getString("telefono"));
				indirizzo.setMail(rs.getString("mail_utente"));
				indirizzo.setAttivo(rs.getBoolean("attivo"));
				
				indirizzi.add(indirizzo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return indirizzi;
	}

	
	@Override
	public Indirizzo findByPrimaryKey(int id) {
		Indirizzo indirizzo = null;
		String query = "SELECT * from indirizzo where id=?";
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			if(rs.next()) {
				indirizzo = new Indirizzo();
				indirizzo.setId(rs.getInt("id"));
				indirizzo.setIndirizzo(rs.getString("via"));
				indirizzo.setN_civico(rs.getString("n_civico"));
				indirizzo.setCap(rs.getString("cap"));
				indirizzo.setProvincia(rs.getString("provincia"));
				indirizzo.setCitta(rs.getString("citta"));
				indirizzo.setTelefono(rs.getString("numero_telefono"));
				indirizzo.setMail((rs.getString("mail_utente")));
				indirizzo.setAttivo(rs.getBoolean("attivo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return indirizzo;
	}

	
	@Override
	public boolean saveOrUpdate(Indirizzo indirizzo) {
		String query;
		if(indirizzo.getId() == 0) {
			//INSERT
			try {
				indirizzo.setId(IdBroker.getId(conn,NomiSequenze.INDIRIZZO));
				query = "insert into indirizzo "
						+ "values(?,?,?,?,?,?,?,?,?)";
				PreparedStatement st = conn.prepareStatement(query);
				st.setInt(1, indirizzo.getId());
				st.setString(2, indirizzo.getIndirizzo());
				st.setString(3, indirizzo.getN_civico());
				st.setString(4, indirizzo.getCap());
				st.setString(5, indirizzo.getTelefono());
				st.setString(6, indirizzo.getMail());
				st.setBoolean(7, indirizzo.getAttivo());
				st.setString(8, indirizzo.getCitta());
				st.setString(9, indirizzo.getProvincia());
				st.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		else {
			//UPDATE
			try {
				query = "update indirizzo "
						+ "set via=?, n_civico=?, cap=?, provincia=?, citta=?, "
						+ "numero_telefono=?, mail_utente=?, attivo=? "
						+ "where id=?";
				PreparedStatement st = conn.prepareStatement(query);
				
				st.setString(1, indirizzo.getIndirizzo());
				st.setString(2, indirizzo.getN_civico());
				st.setString(3, indirizzo.getCap());
				st.setString(4, indirizzo.getProvincia());
				st.setString(5, indirizzo.getCitta());
				st.setString(6, indirizzo.getTelefono());
				st.setString(7, indirizzo.getMail());
				st.setBoolean(8, indirizzo.getAttivo());
				st.setInt(9, indirizzo.getId());
				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	
	@Override
	public boolean delete(int id) {
		try {
			String queryContiene = "Select count(*) as numero from ordine where id_indirizzo=?";
			PreparedStatement stContiene = conn.prepareStatement(queryContiene);
			stContiene.setInt(1, id);
			ResultSet rs = stContiene.executeQuery();
			
			rs.next();
			if(rs.getInt("numero") == 0) {
				String query = "DELETE FROM indirizzo "
						+ "WHERE id=?";
				//inserendo nella query returning*; ritorna la tupla";
				PreparedStatement st = conn.prepareStatement(query);
				st.setInt(1, id);
				st.executeUpdate();
			}
			else {
				String query = "update indirizzo "
						+ "set attivo=? "
						+ "WHERE id=?";
				PreparedStatement st = conn.prepareStatement(query);
				st.setBoolean(1, false);
				st.setInt(2, id);
				st.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public List<Indirizzo> findAllFromUserEnable(String mail) {
		List<Indirizzo> indirizzi = null;
		
		String query = "SELECT * from indirizzo where mail_utente=? and attivo=?";
		try {
			indirizzi =new ArrayList<Indirizzo>();
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, mail);
			st.setBoolean(2, true);
			ResultSet rs =st.executeQuery();
			while(rs.next()) {
				Indirizzo indirizzo = new Indirizzo();
				indirizzo.setId(rs.getInt("id"));
				indirizzo.setIndirizzo(rs.getString("via"));
				indirizzo.setN_civico(rs.getString("n_civico"));
				indirizzo.setCap(rs.getString("cap"));
				indirizzo.setProvincia(rs.getString("provincia"));
				indirizzo.setCitta(rs.getString("citta"));
				indirizzo.setTelefono(rs.getString("numero_telefono"));
				indirizzo.setMail(rs.getString("mail_utente"));
				indirizzo.setAttivo(rs.getBoolean("attivo"));
				
				indirizzi.add(indirizzo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return indirizzi;
	}

	@Override
	public boolean deleteAddress(int id) {
		try {
			String update = "update indirizzo set attivo=false where id=?";
			PreparedStatement prst = conn.prepareStatement(update);
	
			prst.setInt(1, id);
			
			if ( prst.executeUpdate() == 0 ) {
				return false;
			}
			
		
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return true;
	}
}