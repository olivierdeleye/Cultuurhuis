package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
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
 * Servlet implementation class KlantServlet
 */
//@WebServlet("/zoekKlant.htm")
public class ZoekKlantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW ="/WEB-INF/JSP/bevestiging.jsp";
	
       
  
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<String,String>fouten = new HashMap<>();
		Map<String,String>badChars = new LinkedHashMap<>();
		
		Klant klant = null;
		if( (request.getParameter("gebruikersnaam")!= null) && (request.getParameter("paswoord") != null)){
			if (request.getParameter("gebruikersnaam").contains("<") || request.getParameter("gebruikersnaam").contains("[")
					|| request.getParameter("gebruikersnaam").contains("/")){
				badChars.put("badCharGn", "Tekens <,[,/ zijn niet toegelaten");
			}
			if (request.getParameter("paswoord").contains("<") || request.getParameter("paswoord").contains("[")
					|| request.getParameter("paswoord").contains("/")){
				badChars.put("badCharPw", "Tekens <,[,/ zijn niet toegelaten");
			}
			if(!badChars.isEmpty()) {
				request.setAttribute("badChars", badChars);
			}
			
		    klant = new KlantDAO().findKlant(request.getParameter("gebruikersnaam"),
		    		request.getParameter("paswoord"));
		    
		    if(klant != null){
		    	session.setAttribute("klant", klant);
		    }
		    else{
				fouten.put("verkeerd", "Verkeerde gebruikersnaam of paswoord");
				request.setAttribute("fouten", fouten);
				
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
    	dispatcher.forward(request, response);
	}


}
