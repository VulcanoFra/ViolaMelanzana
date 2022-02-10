package application.persistenza.dao.jdbc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import application.model.Ingrediente;
import application.model.IngredienteQuantita;
import application.model.Quantita;
import application.model.Ricetta;
import application.model.RicettaProxy;
import application.model.Utente;
import application.persistenza.Database;
import application.persistenza.dao.RicettaDao;

public class RicettaDaoJDBC implements RicettaDao {

	private Connection conn;
	
	public RicettaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	

	@Override
	public List<RicettaProxy> findAll() {
		
		ArrayList<RicettaProxy> ricettaProxy = null;
		
		String query ="select id, titolo, descrizione, likes, image_ricetta, difficolta, tempo_preparazione from ricetta where approvazione=true order by likes desc;";
		
		try {
			ricettaProxy = new ArrayList<RicettaProxy>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				RicettaProxy r = new RicettaProxy();
				
				r.setId(rs.getInt("id"));
				r.setTitolo(rs.getString("titolo"));
				r.setDescrizione(rs.getString("descrizione"));
				r.setLikes(rs.getInt("likes"));
				r.setImg(rs.getString("image_ricetta"));
				r.setDifficolta(rs.getInt("difficolta"));
				r.setTempoPreparazione(rs.getInt("tempo_preparazione"));
				
				if(r.getImg() != null) {
					BufferedReader read;
					try {
						read = new BufferedReader(new FileReader(r.getImg()));
						if(read.ready()) {
							String base64 = read.readLine();
							r.setBase64Image(base64);
						}
						read.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				ricettaProxy.add(r);
				
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return ricettaProxy;
	}

	
	@Override
	public List<RicettaProxy> findByPublisher(String key) {
		
		ArrayList<RicettaProxy> ricettaProxy = null;
		
		String query ="select id, titolo, likes, image_ricetta from ricetta where approvazione=true and mail_utente_pubblicatore='" + key + "';";
		
		try {
			ricettaProxy = new ArrayList<RicettaProxy>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				RicettaProxy r = new RicettaProxy();
				
				r.setId(rs.getInt("id"));
				r.setTitolo(rs.getString("titolo"));
				r.setLikes(rs.getInt("likes"));
				r.setImg(rs.getString("image_ricetta"));
				
				if(r.getImg() != null) {
					BufferedReader read;
					try {
						read = new BufferedReader(new FileReader(r.getImg()));
						if(read.ready()) {
							String base64 = read.readLine();
							r.setBase64Image(base64);
						}
						read.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				ricettaProxy.add(r);
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return ricettaProxy;
	}
	
	@Override
	public List<RicettaProxy> findByPublisher(String key, int limit, int offset) {
		
		ArrayList<RicettaProxy> ricettaProxy = null;
		
		String query ="select id, titolo, likes, image_ricetta from ricetta where approvazione=true and mail_utente_pubblicatore=? order by data_pubblicazione limit ? offset ? ;";
		
		try {
			ricettaProxy = new ArrayList<RicettaProxy>();
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, key);
			st.setInt(2, limit);
			st.setInt(3, offset);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				RicettaProxy r = new RicettaProxy();
				
				r.setId(rs.getInt("id"));
				r.setTitolo(rs.getString("titolo"));
				r.setLikes(rs.getInt("likes"));
				r.setImg(rs.getString("image_ricetta"));
				
				if(r.getImg() != null) {
					BufferedReader read;
					try {
						read = new BufferedReader(new FileReader(r.getImg()));
						if(read.ready()) {
							String base64 = read.readLine();
							r.setBase64Image(base64);
						}
						read.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				ricettaProxy.add(r);
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return ricettaProxy;
	}

	@Override
	public List<RicettaProxy> findByCategory(String key) {
		ArrayList<RicettaProxy> ricettaProxy = null;
				
		String query ="select id, titolo, descrizione, likes, image_ricetta, id_categoria from ricetta inner join ricetta_categoria on id = id_ricetta and id_categoria='" + key + "'  where approvazione=true;";

		try {
			ricettaProxy = new ArrayList<RicettaProxy>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				RicettaProxy r = new RicettaProxy();
				
				r.setId(rs.getInt("id"));
				r.setTitolo(rs.getString("titolo"));
				r.setDescrizione(rs.getString("descrizione"));
				r.setLikes(rs.getInt("likes"));
				r.setImg(rs.getString("image_ricetta"));
				
				if(r.getImg() != null) {
					BufferedReader read;
					try {
						read = new BufferedReader(new FileReader(r.getImg()));
						if(read.ready()) {
							String base64 = read.readLine();
							r.setBase64Image(base64);
						}
						read.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				ricettaProxy.add(r);
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return ricettaProxy;
	}
	
	@Override
	public Ricetta findByPrimaryKey(int id) {
		Ricetta ricetta = null;
		String query = "SELECT * from ricetta WHERE id=?;";

		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setInt(1, id);

			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				ricetta = new Ricetta();
				ArrayList<IngredienteQuantita> ingredienti = new ArrayList<IngredienteQuantita>();
				ingredienti = this.findIngredientByRecipeId(id);
				ricetta.setListaIngredientiConQuantita(ingredienti);
				ricetta.setApprovazione(rs.getBoolean("approvazione"));
				;
				ricetta.setCommenti(Database.getInstance().getFactory().getCommentoDao().findForRecipe(id));
				ricetta.setConsiglio(rs.getString("consiglio"));
				ricetta.setDescrizione(rs.getString("descrizione"));
				ricetta.setId(rs.getInt("id"));
				ricetta.setLikes(rs.getInt("likes"));
				ricetta.setPreparazione(rs.getString("preparazione"));
				ricetta.setSegnalazioni(rs.getInt("segnalazioni"));
				ricetta.setTitolo(rs.getString("titolo"));
				ricetta.setDifficolta(rs.getInt("difficolta"));
				ricetta.setTempoCottura(rs.getInt("tempo_cottura"));
				ricetta.setTempoPreparazione(rs.getInt("tempo_preparazione"));
				ricetta.setDosi(rs.getString("dosi"));
				ricetta.setImg(rs.getString("image_ricetta"));
				ricetta.setVideo(rs.getString("video_preparazione"));
				ricetta.setChefPubblicatore(rs.getInt("chef"));
				;

				if (ricetta.getImg() != null) {
					BufferedReader read;
					try {
						read = new BufferedReader(new FileReader(ricetta.getImg()));
						if (read.ready()) {
							String base64 = read.readLine();
							ricetta.setBase64Image(base64);
						}
						read.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				Utente ut = Database.getInstance().getFactory().getUtenteDao()
						.findByPrimaryKey(rs.getString("mail_utente_pubblicatore"));
				ricetta.setUtentePubblicatore(ut);
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}

		return ricetta;
	}

	@Override
	public ArrayList<IngredienteQuantita> findIngredientByRecipeId(int id) {
		ArrayList<IngredienteQuantita> ingredienti = new ArrayList<IngredienteQuantita>();
		String query = "SELECT * from contiene WHERE id_ricetta=?;";
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				Ingrediente i = new Ingrediente();
				Quantita q = new Quantita();
				i.setNome(rs.getString("nome_ingrediente"));
				q.setQuantita(rs.getInt("quantita"));
				q.setUnitaDiMisura(rs.getString("unita_di_misura"));
				IngredienteQuantita ing = new IngredienteQuantita();
				ing.setIngrediente(i);
				ing.setQuantita(q);
				ingredienti.add(ing);
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}

		return ingredienti;

	}
	
	@Override
	public List<RicettaProxy> findOrderBy(String expression, int limit, int offset) {
		ArrayList<RicettaProxy> ricettaProxy = null;
		
		String query ="select id, titolo, likes, image_ricetta, chef from ricetta where approvazione=true order by " + expression + " desc limit ? offset ?;";
		PreparedStatement ps;
		try {
			
			ricettaProxy = new ArrayList<RicettaProxy>();
			
			ps = conn.prepareStatement(query);
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				RicettaProxy r = new RicettaProxy();
				
				r.setId(rs.getInt("id"));
				r.setTitolo(rs.getString("titolo"));
				r.setLikes(rs.getInt("likes"));
				r.setImg(rs.getString("image_ricetta"));
				r.setChefPubblicatore(rs.getInt("chef"));
				
				if(r.getImg() != null) {
					BufferedReader read;
					try {
						read = new BufferedReader(new FileReader(r.getImg()));
						if(read.ready()) {
							String base64 = read.readLine();
							r.setBase64Image(base64);
						}
						read.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				ricettaProxy.add(r);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ricettaProxy;
	}
	
	@Override
	public List<RicettaProxy> findPublishedBy(String key) {
		ArrayList<RicettaProxy> ricettaProxy = null;
		
		String query ="select id, titolo, descrizione, likes, image_ricetta, difficolta, tempo_preparazione from ricetta where approvazione=true and chef='" + key + "' order by data_pubblicazione;";
		
		try {
			ricettaProxy = new ArrayList<RicettaProxy>();
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				RicettaProxy r = new RicettaProxy();
				
				r.setId(rs.getInt("id"));
				r.setTitolo(rs.getString("titolo"));
				r.setDescrizione(rs.getString("descrizione"));
				r.setLikes(rs.getInt("likes"));
				r.setImg(rs.getString("image_ricetta"));
				r.setDifficolta(rs.getInt("difficolta"));
				r.setTempoPreparazione(rs.getInt("tempo_preparazione"));
				
				if(r.getImg() != null) {
					BufferedReader read;
					try {
						read = new BufferedReader(new FileReader(r.getImg()));
						if(read.ready()) {
							String base64 = read.readLine();
							r.setBase64Image(base64);
						}
						read.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				ricettaProxy.add(r);
			}
			
		} catch(SQLException e) {
			e.getStackTrace();
		}
		
		return ricettaProxy;
	}
	
	@Override
	public List<RicettaProxy> search(String query, String filter) {
		
		ArrayList<RicettaProxy> ricettaProxy = null;
		
		// TODO ordinare per utente master quando la ricetta e' pubblicata dal master.
		
		String querySql = null;
		
//		if ( filter.equals("all") ) {
//			querySql ="select id, titolo, descrizione, likes, image_ricetta, difficolta, tempo_preparazione, master from ricetta inner join utente on mail = mail_utente_pubblicatore where data_pubblicazione is not null and titolo like '%" + query + "%' or descrizione like '%" + query + "%' order by master desc;";
//		} else if (query.equals("")) {
//			querySql = "select id, titolo, descrizione, likes, image_ricetta, difficolta, tempo_preparazione, master , id_categoria from ricetta inner join utente on mail = mail_utente_pubblicatore inner join ricetta_categoria on id_ricetta = id where data_pubblicazione is not null and id_categoria = " + filter + " order by master desc;";
//		} else {
//			querySql = "select id, titolo, descrizione, likes, image_ricetta, difficolta, tempo_preparazione, master , id_categoria from ricetta inner join utente on mail = mail_utente_pubblicatore inner join ricetta_categoria on id_ricetta = id where data_pubblicazione is not null and id_categoria = " + filter + " and (titolo like '%" + query + "%' or descrizione like '%" + query + "%') order by master desc;";
//		}
		
		
		if ( filter.equals("all") ) {
			querySql ="select id, titolo, descrizione, likes, image_ricetta, difficolta, tempo_preparazione "
					+ "from ricetta "
					+ "where approvazione=true and (titolo like '%" + query + "%' or descrizione like '%" + query + "%');";
		} else {
			querySql = "select id, titolo, descrizione, likes, image_ricetta, difficolta, tempo_preparazione "
					 + "from ricetta inner join ricetta_categoria on id_ricetta = id "
					 + "where approvazione=true "
				 			+ "and id_categoria = " + filter + " "
				 			+ "and (titolo like '%" + query + "%' or descrizione like '%" + query + "%');";
		}
		
		try {
			ricettaProxy = new ArrayList<RicettaProxy>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(querySql);
			
			while(rs.next()) {
				RicettaProxy r = new RicettaProxy();
				r.setId(rs.getInt("id"));
				r.setTitolo(rs.getString("titolo"));
				r.setDescrizione(rs.getString("descrizione"));
				r.setLikes(rs.getInt("likes"));
				r.setImg(rs.getString("image_ricetta"));
				r.setDifficolta(rs.getInt("difficolta"));
				r.setTempoPreparazione(rs.getInt("tempo_preparazione"));
				
				if(r.getImg() != null) {
					BufferedReader read;
					try {
						read = new BufferedReader(new FileReader(r.getImg()));
						if(read.ready()) {
							String base64 = read.readLine();
							r.setBase64Image(base64);
						}
						read.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				ricettaProxy.add(r);
			}
		} catch(SQLException e) {
			e.getStackTrace();
		}
			
		return ricettaProxy;
	}
	
	@Override
	public boolean save(Ricetta ricetta) {
		if (ricetta == null)
			return false;
		try {
			String query = "INSERT INTO ricetta VALUES(?, ?, ?, ?, ?, ?, ?, 0, 0, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setInt(1, ricetta.getId());
			pr.setString(2, ricetta.getTitolo());
			pr.setString(3, ricetta.getDescrizione());
			pr.setString(4, ricetta.getPreparazione());
			pr.setString(5, ricetta.getConsiglio());
			pr.setString(6, ricetta.getCuriosita());
			Utente utente = ricetta.getUtentePubblicatore();
			if (utente != null) {
				pr.setString(7, utente.getMail());
				if (utente.isMaster()) {
					java.util.Date today = new java.util.Date();
					Date dat = new java.sql.Date(today.getTime());
					pr.setBoolean(8, ricetta.isApprovazione());
					pr.setDate(9, dat);
				} else {
					pr.setNull(9, Types.NULL);
					pr.setNull(8, Types.NULL);
				}
			} else
				pr.setNull(7, Types.NULL);
			pr.setInt(10, ricetta.getDifficolta());
			pr.setInt(11, ricetta.getTempoPreparazione());
			pr.setInt(12, ricetta.getTempoCottura());
			pr.setString(13, ricetta.getDosi());
			if (ricetta.getChefPubblicatore() != null) {
				pr.setInt(14, ricetta.getChefPubblicatore());
				java.util.Date today = new java.util.Date();
				Date dat = new java.sql.Date(today.getTime());
				pr.setDate(9, dat);
				pr.setBoolean(8, ricetta.isApprovazione());
			} else {
				pr.setNull(14, Types.NULL);
			}
			pr.setString(15, ricetta.getImg());
			pr.setString(16, ricetta.getVideo());
			pr.executeUpdate();
			Database.getInstance().getFactory().getIngredienteDao().saveIngredientOfRecipe(ricetta.getId(),
					ricetta.getListaIngredientiConQuantita());
			Database.getInstance().getFactory().getCategoriaDao().saveCategoriesOfRecipe(ricetta.getId(),
					ricetta.getCategorieRicetta());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Ricetta ricetta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRequest(int idRicetta, String mail_utente, String motivazione) {
		try {
			String query = "INSERT INTO richiesta_rimozione_ricetta VALUES(?,?,?)";
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setInt(1, idRicetta);
			pr.setString(2, mail_utente);
			pr.setString(3, motivazione);
			pr.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean deleteRecipeFormChef(int ricetta, int chef) {
		
		try {
			String update = "update ricetta set approvazione=false, chef=null where id=? and chef=?";
			PreparedStatement prst = conn.prepareStatement(update);
	
			prst.setInt(1, ricetta);
			prst.setInt(2, chef);
			
			if ( prst.executeUpdate() == 0 ) {
				return false;
			}
			
		
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return true;
	}


	@Override
	public boolean updateDeleteRequest(int id_ricetta, boolean accettata) {
		try {
			String query = "UPDATE richiesta_rimozione_ricetta SET accettata=? WHERE id_ricetta=?";
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setBoolean(1,accettata);
			pr.setInt(2,id_ricetta);
			pr.executeUpdate();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public List<Ricetta> getWithBan() {
		ArrayList<Ricetta> ricetta = null;
		String query = "select * FROM ricetta WHERE approvazione IS TRUE order by segnalazioni desc;";

		try {
			ricetta = new ArrayList<Ricetta>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				if (rs.getInt("segnalazioni") > 0) {
					Ricetta r = new Ricetta();
					Utente u = Database.getInstance().getFactory().getUtenteDao()
							.findByPrimaryKey(rs.getString("mail_utente_pubblicatore"));
					r.setId(rs.getInt("id"));
					r.setTitolo(rs.getString("titolo"));
					r.setUtentePubblicatore(u);
					r.setSegnalazioni(rs.getInt("segnalazioni"));
					ricetta.add(r);
				}
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}

		return ricetta;
	}

	@Override
	public boolean delete(int id_ricetta) {
		try {
			PreparedStatement p = conn.prepareStatement("DELETE FROM commento WHERE id_ricetta=?;");
			PreparedStatement p1 = conn.prepareStatement("DELETE FROM likes_ricetta WHERE id_ricetta=?;");
			PreparedStatement p2 = conn.prepareStatement("DELETE FROM segnalazioni_ricetta WHERE id_ricetta=?;");
			PreparedStatement p3 = conn.prepareStatement("DELETE FROM contiene WHERE id_ricetta=?;");
			PreparedStatement p4 = conn.prepareStatement("DELETE FROM raccolta WHERE id_ricetta=?;");
			PreparedStatement p5 = conn.prepareStatement("DELETE FROM ricetta_categoria WHERE id_ricetta=?;");
			PreparedStatement p6 = conn.prepareStatement("DELETE FROM ricetta WHERE id=?;");

			p.setInt(1, id_ricetta);
			p.executeUpdate();
			p1.setInt(1, id_ricetta);
			p1.executeUpdate();
			p2.setInt(1, id_ricetta);
			p2.executeUpdate();
			p3.setInt(1, id_ricetta);
			p3.executeUpdate();
			p4.setInt(1, id_ricetta);
			p4.executeUpdate();
			p5.setInt(1, id_ricetta);
			p5.executeUpdate();
			p6.setInt(1, id_ricetta);
			p6.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean update(int id_ricetta, boolean approvazione) {
		try {
			String query = "UPDATE ricetta SET data_pubblicazione=?,approvazione=? WHERE id=?";
			PreparedStatement pr = conn.prepareStatement(query);
			Timestamp date = Timestamp.from(Instant.now());
			pr.setTimestamp(1, date);
			pr.setBoolean(2, approvazione);
			pr.setInt(3, id_ricetta);
			pr.executeUpdate();
			if (approvazione == false) {
				try {
					String query1 = "DELETE FROM raccolta WHERE id_ricetta=?";
					String query2 = "DELETE FROM likes_ricetta WHERE id_ricetta=?";
					PreparedStatement pr1 = conn.prepareStatement(query1);
					PreparedStatement pr2 = conn.prepareStatement(query2);
					pr1.setInt(1, id_ricetta);
					pr2.setInt(1, id_ricetta);
					pr1.executeUpdate();
					pr2.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}

			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}



}
