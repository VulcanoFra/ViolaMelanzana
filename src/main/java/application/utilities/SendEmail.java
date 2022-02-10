package application.utilities;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import application.model.Utente;

public class SendEmail {
	
	private static SendEmail instance = null;
	final String fromEmail = "sito.di.ricette@gmail.com";
	final String password = "Melanzana82!";	
	
	private Session session = null;
	
	private SendEmail() {
		Properties pr = new Properties();
		pr.put("mail.smtp.host", "smtp.gmail.com");
		pr.put("mail.smtp.port", "465");
		pr.put("mail.smtp.auth", "true");
		pr.put("mail.smtp.ssl.enable", "true");
		pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	
		if(session == null) {

			session = Session.getInstance(pr, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});	
		}
		
	}
	
	public static SendEmail getInstance() {
		if(instance == null)
			instance = new SendEmail();
		return instance;
	}
	
	public boolean sendEmailVerification(Utente u, String link) {
		boolean res = false;
		
		String toEmail = u.getMail();
		
		try {
			
			Message message = new MimeMessage(session);
			
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setFrom(new InternetAddress(fromEmail));
			
			message.setSubject("Verifica Email");
			message.setText("Ciao!\n"
							+ "Manca poco, \n"
							+ "Clicca il link qui sotto per verificare la tua email ed entrare a far parte della nostra community.\n\n"
							+ link + "\n\n"
							+ "Ignora questa mail se non ti sei registrato sul nostro sito");
			
			Transport.send(message);
			
			res = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public boolean sendEmailChangePassword(Utente u, String resetPasswordLink) {
		boolean res = false;
		
		String toEmail = u.getMail();
		
		try {
			
			Message message = new MimeMessage(session);
			
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setFrom(new InternetAddress(fromEmail));
			
			message.setSubject("Cambio password");
			message.setText("Ciao "+ u.getUsername() + "!\n"
							+ "Hai richiesto di cambiare la tua password,\n"
							+ "Clicca il link qui sotto per cambiare la tua password.\n\n"
							+ resetPasswordLink + "\n\n"
							+ "Ignora questa mail se non hai richiesto il reset");
			
			Transport.send(message);
			
			res = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public boolean sendEmailDeclassMaster(Utente u) {
		boolean res = false;
		
		String toEmail = u.getMail();
		
		try {
			
			Message message = new MimeMessage(session);
			
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setFrom(new InternetAddress(fromEmail));
			
			message.setSubject("Declassamento");
			message.setText("Ciao " + u.getUsername() + "\n"
						   + "A seguito di comportamenti non adeguati hai perso i benefici di utente master\n"
						   + "Ci dispiace molto.\n"
						   + "Tutto il nostro team si augura potrai ritornarlo presto.\n"
						   + "Team Viola Melanzana");
			
			Transport.send(message);
			
			res = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public boolean sendEmailWhenBecomingMaster(Utente u) {
		boolean res = false;
		
		String toEmail = u.getMail();
		
		try {
			
			Message message = new MimeMessage(session);

			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setFrom(new InternetAddress(fromEmail));
			
			message.setSubject("Promozione");
			message.setText("Ciao " + u.getUsername() + "\n"
						   + "Abbiamo ottime notizie!\n"
						   + "A seguito dell'ottimo feedback rilasciato sulle tue ricette sei diventato un utente master!\n"
						   + "Avrai la possibilità di rimuovere e aggiungere le tue ricette liberamente senza nessuna autorizzazione\n"
						   + "Ovviamente si ricorda di mantenere un comportamento esemplare sui contenuti pubblicati, rischiando altrimenti un declassamento.\n"
						   + "Tutto il nostro team si congratula con te.\n"
						   + "Team Viola Melanzana");
			
			Transport.send(message);
			
			res = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
}
