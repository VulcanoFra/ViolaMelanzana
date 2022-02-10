package application.persistenza.dao;

public interface SegnalazioniRicettaDao {
	public boolean add (int idRicetta, String mail_utente,String motivazione);
	public boolean findForRecipeAndUser (String mail_utente, int id_ricetta);
}
