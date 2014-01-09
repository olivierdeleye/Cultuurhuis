package be.vdab.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.dao.KlantDAO;
import be.vdab.entities.Klant;

/**
 * Servlet implementation class NieuweKlantServlet
 */
//@WebServlet("/nieuweklant.htm")
public class NieuweKlantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW ="/WEB-INF/JSP/nieuweklant.jsp";
	private static final String VIEW_BEVESTIGING = "/bevestiging.htm";
	private static final String REDIRECT_URL = "/nieuweklant.htm";
       
 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("klant") != null){
			RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_BEVESTIGING); //naar bevestiging.jsp 
		     dispatcher.forward(request, response);
		}
		else{
			RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW); //naar nieuweklant.jsp
		     dispatcher.forward(request, response);
		}
		 
		
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Klant klant = null;
		HttpSession session = request.getSession();
		Map<String,String>fouten = new LinkedHashMap<>();
		Map<String,String>badChars = new LinkedHashMap<>();
		
		
		if (request.getParameter("voornaam").isEmpty()){
			fouten.put("voornaam", "Voornaam niet ingevuld");
		}
		if (request.getParameter("voornaam").contains("<") || request.getParameter("voornaam").contains("[")
				|| request.getParameter("voornaam").contains("/")){
			badChars.put("badCharVn", "Tekens <,[,/ zijn niet toegelaten");
		}
		if (request.getParameter("familienaam").isEmpty()){
			fouten.put("familienaam", "Familienaam niet ingevuld");
		}
		if (request.getParameter("familienaam").contains("<") || request.getParameter("familienaam").contains("[")
				|| request.getParameter("familienaam").contains("/")){
			badChars.put("badCharFn", "Tekens <,[,/ zijn niet toegelaten");
		}
		if (request.getParameter("straat").isEmpty()){
			fouten.put("straat", "straat niet ingevuld");
		}
		if (request.getParameter("straat").contains("<") || request.getParameter("straat").contains("[")
				|| request.getParameter("straat").contains("/")){
			badChars.put("badCharSt", "Tekens <,[,/ zijn niet toegelaten");
		}
		if (request.getParameter("huisnr").isEmpty()){
			fouten.put("huisnr", "Huisnr. niet ingevuld");
		}
		if (request.getParameter("huisnr").contains("<") || request.getParameter("huisnr").contains("[")
				|| request.getParameter("huisnr").contains("/")){
			badChars.put("badCharHn", "Tekens <,[,/ zijn niet toegelaten");
		}
		if (request.getParameter("postcode").isEmpty()){
			fouten.put("postcode", "Postcode niet ingevuld");
		}
		if (request.getParameter("postcode").contains("<") || request.getParameter("postcode").contains("[")
				|| request.getParameter("postcode").contains("/")){
			badChars.put("badCharPc", "Tekens <,[,/ zijn niet toegelaten");
		}
		if (request.getParameter("gemeente").isEmpty()){
			fouten.put("gemeente", "Gemeente niet ingevuld");
		}
		if (request.getParameter("gemeente").contains("<") || request.getParameter("gemeente").contains("[")
				|| request.getParameter("gemeente").contains("/")){
			badChars.put("badCharGem", "Tekens <,[,/ zijn niet toegelaten");
		}
		if (request.getParameter("gebruikersnaam").isEmpty()){
			fouten.put("gebruikersnaam", "Gebruikersnaam niet ingevuld");
		}
		if (request.getParameter("gebruikersnaam").contains("<") || request.getParameter("gebruikersnaam").contains("[")
				|| request.getParameter("gebruikersnaam").contains("/")){
			badChars.put("badCharGn", "Tekens <,[,/ zijn niet toegelaten");
		}
		if (request.getParameter("paswoord").isEmpty()){
			fouten.put("paswoord", "Paswoord niet ingevuld");
		}
		if (request.getParameter("paswoord").contains("<") || request.getParameter("paswoord").contains("[")
				|| request.getParameter("paswoord").contains("/")){
			badChars.put("badCharPw", "Tekens <,[,/ zijn niet toegelaten");
		}
		if (request.getParameter("herhaalpaswoord").isEmpty()){
			fouten.put("herhaalpaswoord", "Herhaal paswoord niet ingevuld");
		}
		if (request.getParameter("herhaalpaswoord").contains("<") || request.getParameter("herhaalpaswoord").contains("[")
				|| request.getParameter("herhaalpaswoord").contains("/")){
			badChars.put("badCharHpw", "Tekens <,[,/ zijn niet toegelaten");
		}
		if (! request.getParameter("paswoord").equals(request.getParameter("herhaalpaswoord"))){
			fouten.put("paswoordMatch", "Herhaal paswoord is niet gelijk met paswoord");
		}
		if(! request.getParameter("gebruikersnaam").isEmpty() && new KlantDAO().checkGebruikersnaamUniciteit(request.getParameter("gebruikersnaam"))){
			fouten.put("gebruikersnaamIngebruik", "Gebruikersnaam is reeds in gebruik");
		}
		
		if( ! fouten.isEmpty() ){
			if( ! badChars.isEmpty()){
				request.setAttribute("badChars", badChars);
			}
			request.setAttribute("fouten", fouten); // geef fouten mee op resquestScope
			request.setAttribute("firstname",request.getAttribute("voornaam"));
		    RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW); // terug naar nieuweklant.jsp
			dispatcher.forward(request, response);
		}
		else{
			String voorNaam = request.getParameter("voornaam");
			String familieNaam = request.getParameter("familienaam");
			String straat = request.getParameter("straat");
			String huisNr = request.getParameter("huisnr");
			String postCode = request.getParameter("postcode");
			String gemeente = request.getParameter("gemeente");
			String gebruikersNaam = request.getParameter("gebruikersnaam");
			String paswoord = request.getParameter("paswoord");
			
			klant = new Klant(voorNaam, familieNaam, straat, huisNr, postCode, gemeente, gebruikersNaam, paswoord);
			new KlantDAO().createKlant(klant);
			session.setAttribute("klant", klant);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+REDIRECT_URL)); 
			//toevoegen klant gelukt - terug naar doGet() van daar naar bevestiging.htm  
		}
	}

}
