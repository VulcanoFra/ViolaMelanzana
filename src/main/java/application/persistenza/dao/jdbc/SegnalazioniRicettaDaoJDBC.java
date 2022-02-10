package application.persistenza.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.persistenza.dao.SegnalazioniRicettaDao;

public class SegnalazioniRicettaDaoJDBC implements SegnalazioniRicettaDao {

	private Connection conn;
	
	public SegnalazioniRicettaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean add(int id_ricetta, String mail_utente,String motivazione) {
			if (mail_utente==null)
				return false;
			String query = "INSERT INTO segnalazioni_ricetta VALUES(?,?, ?);";
	
			try {
				
				PreparedStatement prst = conn.prepareStatement(query);
				prst.setString(1,mail_utente);
				prst.setInt(2, id_ricetta);
				prst.setString(3, motivazione);
				prst.executeUpdate();
				
				String update = "UPDATE ricetta SET segnalazioni=segnalazioni+1 WHERE id=?;";
				
				PreparedStatement prstup = conn.prepareStatement(update);
				prstup.setInt(1,id_ricetta);
				
				prstup.executeUpdate();
				
				return true;
	
					
			} catch(SQLException e) {
				e.printStackTrace();
				return false;
			}

		}

	@Override
	public boolean findForRecipeAndUser(String mail_utente, int id_ricetta) {
		String check = "SELECT * FROM segnalazioni_ricetta WHERE mail_utente=? AND id_ricetta=?;";

		try {
			
			PreparedStatement prst = conn.prepareStatement(check);
			prst.setString(1,mail_utente);
			prst.setInt(2, id_ricetta);
			ResultSet rs= prst.executeQuery();
			if (rs.next())
				return true;
			else
				return false;
				
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
