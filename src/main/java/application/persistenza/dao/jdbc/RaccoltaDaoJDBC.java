package application.persistenza.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.model.Raccolta;
import application.model.Ricetta;
import application.persistenza.Database;
import application.persistenza.dao.RaccoltaDao;

public class RaccoltaDaoJDBC implements RaccoltaDao {
private Connection conn;
	
	public RaccoltaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public synchronized boolean newFolder(String nome,String mail_utente) {
		if(mail_utente==null)
			return false;
		try {
			String insert = "INSERT INTO raccolta VALUES(?,?,?);";
			PreparedStatement prst = conn.prepareStatement(insert);
			
			prst.setString(1,mail_utente);
			prst.setInt(2, 1);
			prst.setString (3, nome);

			prst.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public synchronized boolean addRecipeToFolder(String nome,String mail_utente, int idRicetta) {
		try {
			String find = "SELECT * from raccolta WHERE nome=? AND mail_utente=?;";
			PreparedStatement prst = conn.prepareStatement(find);
			prst.setString(1,nome);
			prst.setString(2,mail_utente);
			ResultSet rs= prst.executeQuery();
			
			if(!rs.next()) {
				return false;
			}
			
			String insert = "INSERT INTO raccolta VALUES(?,?,?);";
			PreparedStatement prst1 = conn.prepareStatement(insert);

			prst1.setString(1,mail_utente);
			prst1.setInt(2,idRicetta);
			prst1.setString (3, nome);

			prst1.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public synchronized List<Raccolta> getFolderForUser(String mail_utente){
		if(mail_utente==null)
			return null;
		List<Raccolta> raccolte = null;
		String query ="SELECT * from raccolta WHERE mail_utente=?;";
		try {
			
			PreparedStatement pr = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pr.setString (1, mail_utente);
			ResultSet rs = pr.executeQuery();
			raccolte = new ArrayList<Raccolta>();
			List<String> nomeRaccolta=new ArrayList<String>();
			while(rs.next()) {
				if (!nomeRaccolta.contains(rs.getString("nome")))
						nomeRaccolta.add(rs.getString("nome"));
				
			}
			
			for(String a : nomeRaccolta ) {
				rs.beforeFirst();
				ArrayList<Ricetta> ricette=new ArrayList<Ricetta>();
				Raccolta raccolta=new Raccolta();
				while(rs.next()) {
					if (rs.getString("nome").equals(a)){
						Ricetta ricetta=Database.getInstance().getFactory().getRicettaDao().findByPrimaryKey(rs.getInt("id_ricetta"));
						ricette.add(ricetta);

					}
				}
				raccolta.setNome(a);
				raccolta.setRicetteRaccolta(ricette);
				raccolte.add(raccolta);
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return raccolte;
		
	}
	
	@Override
	public synchronized List<String> getFolderWithNoRecipe(String mail_utente,Ricetta ricetta){
		if(mail_utente==null)
			return null;
		List<String> nomeRaccolta=new ArrayList<String>();
		List<String> raccolteOk=new ArrayList<String>();
		String query ="SELECT * from raccolta WHERE mail_utente=?;";
		try {
			
			PreparedStatement pr = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pr.setString (1, mail_utente);
			ResultSet rs = pr.executeQuery();
			while(rs.next()) {
				if (!nomeRaccolta.contains(rs.getString("nome"))) {
						nomeRaccolta.add(rs.getString("nome"));

				}
			}
			for(String a : nomeRaccolta ) {
				List<Ricetta> ricette=this.getRecipeForFolder(mail_utente,a);
				if (!ricette.contains(ricetta)) {
					raccolteOk.add(a);
				}
			}
		}
			
		 catch(SQLException e) {
			e.getStackTrace();
		}
		return raccolteOk;
		
	}
	
	@Override
	public synchronized List<Ricetta> getRecipeForFolder(String mail_utente,String nome){
		if(mail_utente==null)
			return null;
		List<Ricetta> ricette = null;
		String query ="SELECT * from raccolta WHERE mail_utente=? AND nome=?;";
		try {
			
			PreparedStatement pr = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pr.setString (1, mail_utente);
			pr.setString (2, nome);
			ResultSet rs = pr.executeQuery();
			ricette = new ArrayList<Ricetta>();
			
			while(rs.next()) {
					Ricetta ricetta=Database.getInstance().getFactory().getRicettaDao().findByPrimaryKey(rs.getInt("id_ricetta"));
					ricette.add(ricetta);

			}
		}
		 catch(SQLException e) {
			e.getStackTrace();
		}
		return ricette;
	
	}

	@Override
	public synchronized boolean deleteRecipeFromFolder(String nome, String mail_utente, int idRicetta) {
		if(mail_utente==null)
			return false;
		try {
			String query = "DELETE FROM raccolta WHERE mail_utente=? AND id_ricetta=? AND nome=? ;";
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, mail_utente);
			pr.setInt(2, idRicetta);
			pr.setString(3, nome);
			pr.executeUpdate();

			return true;
		} catch(SQLException e) {
			return false;
		}
	}
	
	@Override
	public synchronized boolean deleteFolder(String nome, String mail_utente) {
		if(mail_utente==null)
			return false;
		
		try {
			String query = "DELETE FROM raccolta WHERE mail_utente=? AND nome=? ;";
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, mail_utente);
			pr.setString(2, nome);
			pr.executeUpdate();

			return true;
		} catch(SQLException e) {
			return false;
		}
	}
	
}
