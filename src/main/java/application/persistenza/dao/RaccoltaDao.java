package application.persistenza.dao;

import java.util.List;

import application.model.Raccolta;
import application.model.Ricetta;

public interface RaccoltaDao {
	boolean newFolder(String nome ,String mail_utente);
	boolean addRecipeToFolder(String nome,String mail_utente, int id_ricetta);
	List<Raccolta> getFolderForUser(String mail_utente);
	List<Ricetta> getRecipeForFolder(String mail_utente,String nome);
	boolean deleteRecipeFromFolder(String nome ,String mail_utente,int idRicetta);
	List<String> getFolderWithNoRecipe(String mail_utente,Ricetta ricetta);
	boolean deleteFolder(String nome, String mail_utente);
	 	
}
