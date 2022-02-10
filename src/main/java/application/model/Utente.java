package application.model;

import java.util.ArrayList;
import java.util.Objects;

public class Utente {
	
	private String mail;
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private boolean master;
	private ArrayList<Raccolta> raccolta;
	private boolean enable;
	private String verificationCode;
	private String tokenResetPassword;
	private boolean request;
	
	public Utente() {}
	
	public boolean getRequest() {
		return request;
	}

	public void setRequest(boolean request) {
		this.request = request;
	}
	
	public Utente(String mail, String nome, String cognome, boolean master) {
		super();
		this.mail = mail;
		this.nome = nome;
		this.cognome = cognome;
		this.master = master;
	}
	public Utente(String mail, String nome, String cognome) {
		this.mail = mail;
		this.nome = nome;
		this.cognome = cognome;
		
	}

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public boolean isMaster() {
		return master;
	}

	public void setMaster(boolean master) {
		this.master = master;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ArrayList<Raccolta> getRaccolta() {
		return raccolta;
	}
	
	public void setRaccolta(ArrayList<Raccolta> raccolta) {
		this.raccolta = raccolta;
	}
	
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getTokenResetPassword() {
		return tokenResetPassword;
	}

	public void setTokenResetPassword(String tokenResetPassword) {
		this.tokenResetPassword = tokenResetPassword;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(mail);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return Objects.equals(mail, other.mail);
	}
}
