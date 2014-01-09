package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import be.vdab.dao.MandjeDAO;
import be.vdab.dao.VoorstellingDAO;
import be.vdab.entities.MandjeItem;

/**
 * Servlet implementation class MandjeServlet
 */
//@WebServlet("/mandje.htm")
public class MandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW ="/WEB-INF/JSP/mandje.jsp";
	private static final String REDIRECT_URL = "/mandje.htm";
  
	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("mandje") != null){
		  BigDecimal totaalPrijs = BigDecimal.ZERO;
		  Map<Long,Long> mandje = (Map<Long,Long>) session.getAttribute("mandje"); //haal bestaand mandje op van session
		  for(Map.Entry<Long,Long> entry : mandje.entrySet()){
			   BigDecimal prijs = new VoorstellingDAO().findVoorstelling(entry.getKey()).getPrijs();
			   BigDecimal aantalPlaatsen = new BigDecimal(entry.getValue());
			   BigDecimal totaal = prijs.multiply(aantalPlaatsen);
			   totaalPrijs = totaalPrijs.add(totaal);
		  }
		  Iterable<MandjeItem> mandjeItems = new MandjeDAO().toonMandje(mandje);
		  request.setAttribute("mandjeItems", mandjeItems); // volledige inhoud van reservaties
		  request.setAttribute("totaalprijs", totaalPrijs);
		  
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW); //naar reservatiemandje.jsp
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		 
		if(request.getParameterValues("nummer") != null){
			 Map<Long,Long> mandje = (Map<Long,Long>)session.getAttribute("mandje");
			for(String nummer : request.getParameterValues("nummer")){
				 mandje.remove(Long.parseLong(nummer));
			}
		
		 session.setAttribute("mandje", mandje);
		 response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+REDIRECT_URL));
	    }
		else{ // indien op knop "verwijderen" werd geklikt en niet werd aangevinkt 
			  BigDecimal totaalPrijs = BigDecimal.ZERO;
			  Map<Long,Long> mandje = (Map<Long,Long>) session.getAttribute("mandje"); //haal bestaand mandje op van session
			  for(Map.Entry<Long,Long> entry : mandje.entrySet()){
				   BigDecimal prijs = new VoorstellingDAO().findVoorstelling(entry.getKey()).getPrijs();
				   BigDecimal aantalPlaatsen = new BigDecimal(entry.getValue());
				   BigDecimal totaal = prijs.multiply(aantalPlaatsen);
				   totaalPrijs = totaalPrijs.add(totaal);
			  }
			  Iterable<MandjeItem> mandjeItems = new MandjeDAO().toonMandje(mandje);
			  request.setAttribute("mandjeItems", mandjeItems); // volledige inhoud van reservaties terug
			  request.setAttribute("totaalprijs", totaalPrijs);
			  
			  RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW); //naar reservatiemandje.jsp
			  dispatcher.forward(request, response);
	    }
	}
		
	
	

}
