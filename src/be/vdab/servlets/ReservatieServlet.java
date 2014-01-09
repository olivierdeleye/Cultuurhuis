package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.dao.VoorstellingDAO;
import be.vdab.entities.Voorstelling;

/**
 * Servlet implementation class ReservatieServlet
 */
//@WebServlet("/reserveren.htm")
public class ReservatieServlet extends HttpServlet {
	
   private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reserveren.jsp";
	private static final String REDIRECT_URL = "/mandje.htm";

	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Voorstelling voorstelling = null;
		HttpSession session = request.getSession();
		long nummer=0;
		
		if (request.getParameter("nummer") != null){  
	      nummer = Long.parseLong(request.getParameter("nummer"));
	      voorstelling = new VoorstellingDAO().findVoorstelling(nummer);
		   if ( session.getAttribute("mandje") != null ){ // indien er een mandje is
			  Map<Long,Long>mandje = (Map<Long,Long>) session.getAttribute("mandje"); // haal bestaand mandje op van session
			
              if (mandje.containsKey(nummer)){ // als mandje niet leeg is en deze voorstelling WEL al bevat
				long vorigePlaatsen = mandje.get(nummer); 
				request.setAttribute("plaatsen",vorigePlaatsen); //plaats voordiengekozen aantal plaatsen terug
			  }
		   }
		}
		request.setAttribute("voorstelling", voorstelling);
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
	    dispatcher.forward(request, response);
	}
	
	@SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
		Voorstelling voorstelling = null;
		HttpSession session = request.getSession();
		long plaatsen=0;
		long nummer=0;
		Map<String,String>fouten = new HashMap<>();
		Map<Long,Long> mandje = new HashMap<>();;
		
		if (request.getParameter("voorstellingsnummer") != null){
		  nummer = Long.parseLong(request.getParameter("voorstellingsnummer")); //param meegekregen van VIEW JSP
	      voorstelling = new VoorstellingDAO().findVoorstelling(nummer);
	      long vrijePlaatsen = voorstelling.getVrijePlaatsen();
		  try {
	        	plaatsen = Long.parseLong(request.getParameter("plaatsen"));
	        	if(plaatsen < 1){ //indien er een post gedaan werd met een negatieve waarde
					  fouten.put("ongeldigeWaarde", "Vul een getal in groter dan 0");
				}// indien aantal gevraagde plaatsen groter is dan aantal vrije plaatsen of 0
	        	if( (plaatsen > vrijePlaatsen) || (plaatsen == 0) ){ 
	  			  fouten.put("ongeldigeWaarde", "Tik een getal tussen 1 en "+vrijePlaatsen);
	        	}
	       }
	       catch(Exception ex) {
	         fouten.put("ongeldigeWaarde", "Vul een getal");
	       }
		   if(!fouten.isEmpty()) {  //indien er inputfouten waren
		        request.setAttribute("voorstelling", voorstelling); //plaats zelfde voorstelling terug
				request.setAttribute("fouten", fouten); // geef eventuele fouten mee op resquestScope
			    RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW); // terug naar reserveren.jsp
				dispatcher.forward(request, response);
		   }
		   else { // else-block indien geen fouten
			  voorstelling = new VoorstellingDAO().findVoorstelling(nummer);
		      if ( session.getAttribute("mandje") != null ) { //indien er reeds een session en mandje is
			      mandje = (Map<Long,Long>) session.getAttribute("mandje"); // haal bestaand mandje op van session
			
		       if (mandje.containsKey(nummer)){ // als mandje deze voorstelling WEL reeds bevat
			       long vorigePlaatsen = mandje.get(nummer); 
				   if(vorigePlaatsen == plaatsen){ //als gebruiker de plaatsen niet wijzigde
				     
				     request.setAttribute("voorstelling", voorstelling); // plaats zelfde voorstelling terug
				     request.setAttribute("plaatsen",vorigePlaatsen); //plaats voordiengekozen aantal plaatsen terug
				    
				     RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW); // terug naar reservaties.jsp
				     dispatcher.forward(request, response);
				    }
				   else{ // bestaande reservatie werd door gebruiker wel gewijzigd 
				      mandje.remove(nummer); // verwijder bestaande reservatie
				      mandje.put(nummer, plaatsen); //plaats gewijzigde reservatie in mandje
				      session.setAttribute("mandje", mandje);
				      response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+REDIRECT_URL));	
				    }
			    }
		       else{ // voorstelling komt nog niet voor in mandje
				   mandje.put(nummer, plaatsen);
				   session.setAttribute("mandje", mandje);
				   response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+REDIRECT_URL));	 
			    }
		     }
	         else{  // indien er nog geen session en mandje is en doPost() correct is
	        	 mandje.put(nummer, plaatsen);
	             session.setAttribute("mandje", mandje); //indien nog geen mandje, wordt een leeg mandje geplaatst
			     response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+REDIRECT_URL));
		      }
		   }
		}
   }
}

   

	

