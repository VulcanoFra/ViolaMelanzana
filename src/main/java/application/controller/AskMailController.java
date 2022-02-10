package application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import application.model.Utente;
import application.persistenza.Database;
import application.utilities.Protocol;
import application.utilities.SendEmail;
import application.utilities.Utilities;
import net.bytebuddy.utility.RandomString;

@Controller
public class AskMailController {

	@GetMapping("askMailReset")
	public ModelAndView getAskMailPage() {
		return new ModelAndView("askMail");
	}
	
	
	@PostMapping("/sendMailRecoverPassword")
	@ResponseBody
	public String sendMailRecoverPassword(HttpServletRequest req, @RequestParam("email") String email) {
		String token = RandomString.make(45);
		
		Utente user = Database.getInstance().getFactory().getUtenteDao().findByPrimaryKey(email) ;
		String message = null;
		if(user == null) {
			message = Protocol.NO_ONE_WITH_EMAIL;			
		} else if(user.getPassword() == null) {
			message = Protocol.GOOGLE_EMAIL;
		} else {
			message = Protocol.VALID_EMAIL;
			String link = Utilities.getSiteUrl(req) + "/resetPassword?token=" + token;
			if(SendEmail.getInstance().sendEmailChangePassword(user, link)) {
				Database.getInstance().getFactory().getUtenteDao().setToken(user, token);	
			} else {
				message = Protocol.IMPOSSIBLE_SEND_EMAIL;
			}
		}
		
		return message;
	}
}
