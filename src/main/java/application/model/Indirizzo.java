package application.model;

import java.util.Objects;

public class Indirizzo {
	public int id;
	public String indirizzo;
	public String n_civico;
	public String cap;
	public String citta;
	public String provincia;
	public String telefono;
	public String mail;
	public boolean attivo;
	
	public Indirizzo() {}
	
	public Indirizzo(String indirizzo, String n_civico, String cap, String citta, String provincia, String telefono, String mail, boolean attivo) {
		super();
		id = 0;
		this.indirizzo = indirizzo;
		this.n_civico = n_civico;
		this.cap = cap;
		this.citta = citta;
		this.provincia = provincia;
		this.telefono = telefono;
		this.mail = mail;
		this.attivo = attivo;
	}
	
	public Indirizzo(int id,String indirizzo, String n_civico, String cap, String citta, String provincia, String telefono, String mail, boolean attivo) {
		super();
		this.id = id;
		this.indirizzo = indirizzo;
		this.n_civico = n_civico;
		this.cap = cap;
		this.citta = citta;
		this.provincia = provincia;
		this.telefono = telefono;
		this.mail = mail;
		this.attivo = attivo;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getN_civico() {
		return n_civico;
	}
	public void setN_civico(String n_civico) {
		this.n_civico = n_civico;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public boolean getAttivo() {
		return attivo;
	}
	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cap, citta, id, indirizzo, n_civico, provincia, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Indirizzo other = (Indirizzo) obj;
		return cap == other.cap && Objects.equals(citta, other.citta) && Objects.equals(id, other.id)
				&& Objects.equals(indirizzo, other.indirizzo) && n_civico == other.n_civico
				&& Objects.equals(provincia, other.provincia) && telefono == other.telefono;
	}
}
