package application.persistenza.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.model.Commento;
import application.model.NomiSequenze;
import application.persistenza.dao.CommentoDao;

public class CommentoDaoJDBC implements CommentoDao{
private Connection conn;
	
	public CommentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	

	@Override
	public synchronized boolean save(Commento commento) {
		if(commento== null)
			return false;
		
		try {
			String insert = "INSERT INTO commento VALUES(?,?,?,?,?);";
			PreparedStatement prst = conn.prepareStatement(insert);
			int id_comm= IdBroker.getId(conn, NomiSequenze.COMMENTO);
			prst.setLong(1,id_comm);
			prst.setString(2,commento.getPubblicatore().getMail());
			prst.setLong(3, commento.getIdRicetta());
			prst.setString (4, commento.getContenuto());
			prst.setTimestamp(5,commento.getData());
			
			prst.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public synchronized boolean update(Commento commento) {
		if(commento == null)
			return false;
		
		try {
			String update = "UPDATE commento SET contenuto=? ";
			PreparedStatement prst2 = conn.prepareStatement(update);
			
			prst2.setString(1, commento.getContenuto());
			
			
			prst2.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public synchronized boolean delete(Commento commento) {
		if(commento == null)
			return false;
		try {
			String query = "DELETE FROM commento where id_commento=?";
			PreparedStatement pr = conn.prepareStatement(query);
			
			pr.setString(1, String.valueOf(commento.getIdCommento()));
			pr.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			return false;
		}
	}




	@Override
	public synchronized ArrayList<Commento> findAll() {
		ArrayList<Commento> commenti = null;
		String query ="SELECT * from commento;";
		try {
			commenti = new ArrayList<Commento>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				
				Commento commento=new Commento();
				commento.setContenuto(rs.getString("contenuto"));
				commento.setIdCommento(rs.getInt("id_commento"));
				commento.setIdRicetta(rs.getInt("id_ricetta"));
				UtenteDaoJDBC ut= new UtenteDaoJDBC(conn);
				commento.setPubblicatore(ut.findByPrimaryKey(rs.getString("utente")));
				commenti.add(commento);
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return commenti;
	}
	
	@Override
	public synchronized ArrayList<Commento> findForRecipe(int idRicetta){
		ArrayList<Commento> commenti = null;
		String query ="SELECT * from commento WHERE id_ricetta=? order by data_pubblicazione desc;";
		try {
			
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setLong(1, idRicetta);
			ResultSet rs = pr.executeQuery();
			commenti = new ArrayList<Commento>();
			while(rs.next()) {
				Commento commento=new Commento();
				commento.setContenuto(rs.getString("contenuto"));
				commento.setIdCommento(rs.getInt("id_commento"));
				commento.setIdRicetta(rs.getInt("id_ricetta"));
				UtenteDaoJDBC ut= new UtenteDaoJDBC(conn);
				commento.setPubblicatore(ut.findByPrimaryKey(rs.getString("mail_utente")));
				commento.setData(rs.getTimestamp("data_pubblicazione"));
				commenti.add(commento);
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return commenti;
		
	}

}
