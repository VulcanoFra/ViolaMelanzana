package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ricetta {

	private int id;
	private String titolo;
	private String descrizione;
	private String preparazione;
	private String consiglio;
	private int likes;
	private int segnalazioni;
	private int difficolta;
	private int tempoPreparazione;
	private int tempoCottura;
	private String dosi;
	private String img;
	private String curiosita;
	private Utente utentePubblicatore;
	private Boolean approvazione;
	private ArrayList<IngredienteQuantita> listaIngredientiConQuantita;
	private ArrayList<Commento> commenti;
	private ArrayList<String> categorieRicetta;
	private String base64Image;
	private String video;
	private Integer chefPubblicatore; 
	
	public Ricetta() {
		categorieRicetta = new ArrayList<String>();
	}
	
	public int getDifficolta() {
		return difficolta;
	}
	
	public void setDifficolta(int difficolta) {
		this.difficolta = difficolta;
	}
	public int getTempoPreparazione() {
		return tempoPreparazione;
	}
	
	public void setTempoPreparazione(int tempoPreparazione) {
		this.tempoPreparazione = tempoPreparazione;
	}
	public int getTempoCottura() {
		return tempoCottura;
	}
	
	public void setTempoCottura(int tempoCottura) {
		this.tempoCottura = tempoCottura;
	}
	
	public String getDosi() {
		return dosi;
	}

	public ArrayList<IngredienteQuantita> getListaIngredientiConQuantita() {
		return listaIngredientiConQuantita;
	}

	public ArrayList<String> getCategorieRicetta() {
		return categorieRicetta;
	}

	public void setCategorieRicetta(ArrayList<String> categorieRicetta) {
		this.categorieRicetta = categorieRicetta;
	}

	public void setListaIngredientiConQuantita(ArrayList<IngredienteQuantita> listaIngredientiConQuantita) {
		this.listaIngredientiConQuantita = listaIngredientiConQuantita;
	}

	public void setDosi(String dosi) {
		this.dosi = dosi;
	}
	
	public String getCuriosita() {
		return curiosita;
	}
	
	public void setCuriosita(String curiosita) {
		this.curiosita = curiosita;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	

	public void setImg(String img) {
		this.img = img;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getPreparazione() {
		return preparazione;
	}
	
	public void setPreparazione(String preparazione) {
		this.preparazione = preparazione;
	}
	
	public String getConsiglio() {
		return consiglio;
	}
	
	public void setConsiglio(String consiglio) {
		this.consiglio = consiglio;
	}

	public int getLikes() {
		return likes;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public int getSegnalazioni() {
		return segnalazioni;
	}
	
	public void setSegnalazioni(int segnalazioni) {
		this.segnalazioni = segnalazioni;
	}
	
	public Utente getUtentePubblicatore() {
		return utentePubblicatore;
	}
	
	public void setUtentePubblicatore(Utente utentePubblicatore) {
		this.utentePubblicatore = utentePubblicatore;
	}
	
	public Boolean isApprovazione() {
		return approvazione;
	}

	public void setApprovazione(Boolean approvazione) {
		this.approvazione = approvazione;
	}

	public ArrayList<Commento> getCommenti() {
		return commenti;
	}
	
	public void setCommenti(List<Commento> commenti2) {
		this.commenti = (ArrayList<Commento>) commenti2;
	}
	
	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	

	public Integer getChefPubblicatore() {
		return chefPubblicatore;
	}

	public void setChefPubblicatore(Integer chefPubblicatore) {
		this.chefPubblicatore = chefPubblicatore;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ricetta other = (Ricetta) obj;
		return id == other.id;
	}

}
