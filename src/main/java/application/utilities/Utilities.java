package application.utilities;

import javax.servlet.http.HttpServletRequest;

//Questo serve per settare un valore alla ricetta in base allo stato in cui si trova
public class Utilities {

	public static final int ADMIN_ATTESA = 0;
	public static final int ADMIN_ACCETTA = 1;
	public static final int ADMIN_RIFIUTA = 2;
	public static String getSiteUrl(HttpServletRequest req) {
		String url = req.getRequestURL().toString();
		return url.replace(req.getServletPath(), "");
	}
	
}
