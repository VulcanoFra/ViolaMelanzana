package application.persistenza.dao;

import java.util.List;
import application.model.Ricetta;

public interface LikesRicettaDao {
	List<Ricetta> findForUser(String mail_utente);
	public String handleLike(int idRicetta, String mail_utente);
    boolean findForRecipeAndUser(String mail_utente,int idRicetta);
}
