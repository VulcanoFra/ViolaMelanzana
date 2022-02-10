package application.persistenza.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

import application.model.Ricetta;
import application.model.Utente;
import application.persistenza.Database;
import application.persistenza.dao.UtenteDao;

public class UtenteDaoJDBC implements UtenteDao{

	private Connection conn;
	
	public UtenteDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public synchronized List<Utente> findAll() {
		ArrayList<Utente> utenti = null;
		String query ="SELECT * from utente;";
		try {
			utenti = new ArrayList<Utente>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				
				Utente utente = new Utente();
				utente.setMail(rs.getString("mail"));
				utente.setUsername(rs.getString("username"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setMaster(rs.getBoolean("master"));
				utente.setPassword(rs.getString("password"));

				utenti.add(utente);
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return utenti;
	}

	@Override
	public synchronized Utente findByPrimaryKey(String mail) {
		Utente utente = null;
		String query ="SELECT * from utente WHERE mail=?;";
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, mail);
			ResultSet rs = pr.executeQuery();
			
			if(rs.next()) {
				
				utente = new Utente();
				utente.setMail(rs.getString("mail"));
				utente.setUsername(rs.getString("username"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setMaster(rs.getBoolean("master"));	
				utente.setPassword(rs.getString("password"));
			}	
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return utente;
	}

	@Override
	public boolean save(Utente utente, boolean googleLogin) {
		//Se l'utente è null o se esiste un utente già registrato con quella mail allora return falso, non si può inserire
		if(utente == null || findByPrimaryKey(utente.getMail()) != null)
			return false;
		
		if(!googleLogin) {
			String cryptPassword = BCrypt.hashpw(utente.getPassword(), BCrypt.gensalt(12)); 
			
			try {
				String insert = "INSERT INTO utente VALUES(?, ?, ?, ?, ?, ?, ?, ?, null);";
				PreparedStatement prst = conn.prepareStatement(insert);
				prst.setString(1, utente.getMail());
				prst.setString(2, utente.getNome());
				prst.setString(3, utente.getCognome());
				prst.setString(4, utente.getUsername());
				prst.setString(5, cryptPassword);
				prst.setBoolean(6, utente.isEnable());
				prst.setString(7, utente.getVerificationCode());
				prst.setBoolean(8, utente.isMaster()); prst.executeUpdate();
				return true;
			} catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else { 
			try {
				String insert = "INSERT INTO utente VALUES(?, ?, ?, ?, null, ?, null, ?, null);";
				PreparedStatement prst = conn.prepareStatement(insert);
				prst.setString(1, utente.getMail());
				prst.setString(2, utente.getNome());
				prst.setString(3, utente.getCognome());
				prst.setString(4, utente.getUsername());
				prst.setBoolean(5, utente.isEnable());
				prst.setBoolean(6, utente.isMaster());
				prst.executeUpdate();
				return true;
			} catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	@Override
	public synchronized boolean update(Utente utente) {
		if(utente == null)
			return false;
		
		String cryptPassword = BCrypt.hashpw(utente.getPassword(), BCrypt.gensalt(12));

		try {
			String update = "UPDATE utente SET mail=?, nome=?, cognome=?, master=?, username=?, password=? WHERE mail=?";
			PreparedStatement prst2 = conn.prepareStatement(update);
			
			prst2.setString(1, utente.getMail());
			prst2.setString(2, utente.getNome());
			prst2.setString(3, utente.getCognome());
			prst2.setBoolean(4, utente.isMaster());
			prst2.setString(5, utente.getUsername());
			prst2.setString(6, cryptPassword);
			
			prst2.setString(7, utente.getMail());
			
			prst2.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public synchronized boolean delete(Utente utente) {
		if(utente == null)
			return false;
		try {
			String query = "DELETE FROM utente where mail=?";
			PreparedStatement pr = conn.prepareStatement(query);
			
			pr.setString(1, utente.getMail());
			pr.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			return false;
		}
	}

	@Override
	public synchronized Utente findByUnique(String username) {
		Utente utente = null;
		String query ="SELECT * from utente WHERE username=?;";
		
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, username);
			ResultSet rs = pr.executeQuery();
			
			if(rs.next()) {
				utente = new Utente();
				utente.setMail(rs.getString("mail"));
				utente.setUsername(rs.getString("username"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setMaster(rs.getBoolean("master"));	
			}	
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return utente;
		
	}

	@Override
	public synchronized Utente checkByUniqueAndPassword(String username, String password) {
		Utente utente = null;
		String query ="SELECT * from utente WHERE username=?;";
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, username);
			ResultSet rs = pr.executeQuery();
			
			if(rs.next()) {
				String cryptPassword = rs.getString("password");
				if(BCrypt.checkpw(password, cryptPassword)) {
					utente = new Utente();
					utente.setMail(rs.getString("mail"));
					utente.setUsername(rs.getString("username"));
					utente.setPassword(rs.getString("password"));
					utente.setNome(rs.getString("nome"));
					utente.setCognome(rs.getString("cognome"));
					utente.setMaster(rs.getBoolean("master"));	
					utente.setEnable(rs.getBoolean("enable"));
				}
			}	
		} catch(SQLException e) {
			e.getStackTrace();
		}
		return utente;
	}

	@Override
	public boolean enableUser(String username) {
		String query = "update utente set enable=true, verification_code=null where username=?";
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, username);
		
			pr.executeUpdate();
		
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Utente findByToken(String token) {
		String query = "select * from utente where token=?";
		Utente utente = null;
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, token);
			
			ResultSet rs = pr.executeQuery();
			
			if(rs.next()) {
				utente = new Utente();
				utente.setMail(rs.getString("mail"));
				utente.setUsername(rs.getString("username"));
				utente.setPassword(rs.getString("password"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setMaster(rs.getBoolean("master"));	
				utente.setEnable(rs.getBoolean("enable"));
				utente.setTokenResetPassword(rs.getString("token"));
			}
		} catch(SQLException e) {
			
		}
		
		return utente;
	}

	@Override
	public boolean setToken(Utente user, String token) {
		String query = "update utente set token=? where mail=?";
		
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, token);
			pr.setString(2, user.getMail());
			
			pr.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean resetPassword(Utente u, String password) {
		String query = "update utente set password=?, token=null where mail=?";
		
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
			
			pr.setString(1, hashedPassword);
			pr.setString(2, u.getMail());
			
			pr.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	

	@Override
	public boolean updateData(Utente utente) {
		if(utente == null)
			return false;

		try {
			String update = "update utente set nome=?, cognome=? where mail=?";
			PreparedStatement prst = conn.prepareStatement(update);
	
			prst.setString(1, utente.getNome());
			prst.setString(2, utente.getCognome());
			prst.setString(3, utente.getMail());
			
			prst.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updatePassword(Utente utente) {
		if(utente == null)
			return false;

		try {
			
			String cryptPassword = BCrypt.hashpw(utente.getPassword(), BCrypt.gensalt(12));
			
			String update = "update utente set password=? where mail=?";
			PreparedStatement prst = conn.prepareStatement(update);
	
			prst.setString(1, cryptPassword);
			prst.setString(2, utente.getMail());
			
			prst.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUsername(Utente utente) {
		if(utente == null)
			return false;

		try {
			String update = "update utente set username=? where mail=?";
			PreparedStatement prst = conn.prepareStatement(update);
	
			prst.setString(1, utente.getUsername());
			prst.setString(2, utente.getMail());
			
			prst.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Utente findByVerificationCode(String verificationCode) {
		String query = "select * from utente where verification_code=?";
		Utente utente = null;
		
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, verificationCode);
			
			ResultSet rs = pr.executeQuery();
		
			if(rs.next()) {
				utente = new Utente();
				utente.setMail(rs.getString("mail"));
				utente.setUsername(rs.getString("username"));
				utente.setPassword(rs.getString("password"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setMaster(rs.getBoolean("master"));
				utente.setEnable(rs.getBoolean("enable"));
				utente.setTokenResetPassword(rs.getString("token"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return utente;
	}


	

	@Override
	public synchronized List<Utente> findAllWithRequest() {
		List<Utente> utenti = null;
		List<Utente> utentiNo = null;
		String query = "SELECT * from utente WHERE enable is TRUE;";
		try {
			utenti = new ArrayList<Utente>();
			utentiNo = new ArrayList<Utente>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Utente utente = new Utente();
				utente.setMail(rs.getString("mail"));
				utente.setUsername(rs.getString("username"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setMaster(rs.getBoolean("master"));
				if (this.getUserRequest(utente.getMail()).size() > 0) {
					utente.setRequest(true);
					utenti.add(utente);
				} else {
					utente.setRequest(false);
					utentiNo.add(utente);
				}
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}
		utenti.addAll(utentiNo);
		return utenti;
	}

	@Override
	public synchronized List<List<String>> getUserRequest(String mail) {
		List<List<String>> userRequest = null;
		List<String> oneRequest = null;
		String haveRequest = "SELECT * FROM ricetta WHERE mail_utente_pubblicatore=? AND approvazione is NULL;";
		try {
			userRequest = new ArrayList<List<String>>();
			PreparedStatement pr = conn.prepareStatement(haveRequest);
			pr.setString(1, mail);
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				oneRequest = new LinkedList<String>();
				oneRequest.add("aggiunta ricetta");
				oneRequest.add(rs.getString("titolo"));
				oneRequest.add(String.valueOf(rs.getInt("id")));
				oneRequest.add("/");
				userRequest.add(oneRequest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String haveRequest1 = "SELECT * FROM richiesta_rimozione_ricetta WHERE mail_utente=? AND accettata is NULL;";
		try {
			PreparedStatement pr1 = conn.prepareStatement(haveRequest1);
			pr1.setString(1, mail);
			ResultSet rs1 = pr1.executeQuery();
			while (rs1.next()) {
				Ricetta ricetta = Database.getInstance().getFactory().getRicettaDao()
						.findByPrimaryKey(rs1.getInt("id_ricetta"));
				oneRequest = new LinkedList<String>();
				oneRequest.add("rimozione ricetta");
				oneRequest.add(ricetta.getTitolo());
				oneRequest.add(String.valueOf(ricetta.getId()));
				oneRequest.add(rs1.getString("motivazione"));
				userRequest.add(oneRequest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (this.findByPrimaryKey(mail).isMaster() == false) {
			String master = "SELECT * FROM ricetta WHERE mail_utente_pubblicatore=? AND approvazione is TRUE;";
			try {
				int cont = 0;
				PreparedStatement pr2 = conn.prepareStatement(master, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				pr2.setString(1, mail);
				ResultSet rs2 = pr2.executeQuery();
				if (rs2.next()) {
					rs2.last();
					if ((rs2.getRow()) >= 15) {
						rs2.beforeFirst();
						while (rs2.next()) {
							Ricetta ricetta = Database.getInstance().getFactory().getRicettaDao()
									.findByPrimaryKey(rs2.getInt("id"));
							if (ricetta.getLikes() >= 100)
								cont++;
						}
					}
				}
				if (cont >= 15) {
					oneRequest = new LinkedList<String>();
					oneRequest.add("promozione a master");
					oneRequest.add("utente idoneo");
					oneRequest.add("/");
					oneRequest.add(cont + " ricette con likes>=100");
					userRequest.add(oneRequest);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (this.findByPrimaryKey(mail).isMaster() == true) {
			String master = "SELECT * FROM ricetta WHERE mail_utente_pubblicatore=? AND approvazione is TRUE;";
			try {
				int cont = 0;
				PreparedStatement pr2 = conn.prepareStatement(master, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				pr2.setString(1, mail);
				ResultSet rs2 = pr2.executeQuery();
				if (rs2.next()) {
					rs2.last();
					if ((rs2.getRow()) >= 15) {
						rs2.beforeFirst();
						while (rs2.next()) {
							Ricetta ricetta = Database.getInstance().getFactory().getRicettaDao()
									.findByPrimaryKey(rs2.getInt("id"));
							if (ricetta.getLikes() >= 100)
								cont++;
						}
					}
				}
				if (cont < 15) {
					oneRequest = new LinkedList<String>();
					oneRequest.add("declassare utente master");
					oneRequest.add("utente non più idoneo");
					oneRequest.add("/");
					oneRequest.add("condizioni non soddisfatte");
					userRequest.add(oneRequest);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userRequest;
	}

	@Override
	public boolean isMaster(String mail_utente, boolean master) {
		try {
			String update = "update utente set master=? where mail=?";
			PreparedStatement prst = conn.prepareStatement(update);
			prst.setBoolean(1, master);
			prst.setString(2, mail_utente);
			prst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	
}
