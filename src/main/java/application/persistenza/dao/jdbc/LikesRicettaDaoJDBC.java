package application.persistenza.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.model.Ricetta;
import application.persistenza.Database;
import application.persistenza.dao.LikesRicettaDao;

public class LikesRicettaDaoJDBC implements LikesRicettaDao {

		private Connection conn;
		
		public LikesRicettaDaoJDBC(Connection conn) {
			this.conn = conn;
		}

		@Override
		public synchronized String handleLike(int idRicetta, String mail_utente) {
			if (mail_utente == null)
				return "NO";

			String check = "SELECT * FROM likes_ricetta WHERE mail_utente=? AND id_ricetta=?;";
			String query = null;
			String res = null;
			try {

				PreparedStatement prst = conn.prepareStatement(check);
				prst.setString(1, mail_utente);
				prst.setInt(2, idRicetta);

				ResultSet rs = prst.executeQuery();
				if (rs.next()) {
					query = "DELETE from likes_ricetta WHERE mail_utente=? AND id_ricetta=?;";
					res = "LIKEOFF";
				} else {
					query = "INSERT INTO likes_ricetta VALUES(?,?);";
					res = "LIKEON";
				}

				PreparedStatement prst1 = conn.prepareStatement(query);
				prst1.setString(1, mail_utente);
				prst1.setInt(2, idRicetta);

				prst1.executeUpdate();
				return res;

			} catch (SQLException e) {
				e.printStackTrace();
				return "NO";
			}

		}


		@Override
		public synchronized List<Ricetta> findForUser(String mail_utente) {
			List<Ricetta> ricette = null;
			String query ="SELECT * from likes_ricetta WHERE mail_utente=?;";
			try {
				
				PreparedStatement pr = conn.prepareStatement(query);
				pr.setString(1, mail_utente);
				ResultSet rs = pr.executeQuery();
				ricette = new ArrayList<Ricetta>();
				while(rs.next()) {
					Ricetta ricetta=new Ricetta();
					ricetta=Database.getInstance().getFactory().getRicettaDao().findByPrimaryKey(rs.getInt("id_ricetta"));
					ricette.add(ricetta);
				}
				
			} catch(SQLException e) {
				e.getStackTrace();
			}
			
			return ricette;
		}
		
		@Override
		public synchronized boolean findForRecipeAndUser(String mail_utente,int idRicetta) {
			String query ="SELECT * from likes_ricetta WHERE mail_utente=? AND id_ricetta=?;";
			try {
				
				PreparedStatement pr = conn.prepareStatement(query);
				pr.setString(1, mail_utente);
				pr.setInt(2, idRicetta);
				ResultSet rs = pr.executeQuery();
				if(rs.next()) 
					return true;			
				
			} catch(SQLException e) {
				e.getStackTrace();
			}
			
			return false;
		}
}
