package be.vdab.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.dao.MandjeDAO;
import be.vdab.dao.ReservatieDAO;
import be.vdab.entities.Klant;
import be.vdab.entities.MandjeItem;

/**
 * Servlet implementation class BoekingServlet
 */
//@WebServlet("/boeking.htm")
public class BoekingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/overzicht.jsp";
	
       
  
	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Map<Long,Long> mislukt = new ConcurrentHashMap<>();
		Map<Long,Long> gelukt = new ConcurrentHashMap<>();
		
		if ((session.getAttribute("mandje") != null) && (session.getAttribute("klant") != null)){
			Map<Long,Long> mandje = (Map<Long,Long>) session.getAttribute("mandje"); //haal bestaand mandje op van session
			Klant klant = (Klant)session.getAttribute("klant"); //haal klant op van session
			long klantnr = klant.getNummer();
			for(Map.Entry<Long, Long> entry : mandje.entrySet()){
				long voorstellingsnr = entry.getKey();
				long plaatsen = entry.getValue();
				if(new ReservatieDAO().boekReservaties(klantnr, voorstellingsnr, plaatsen)){
					gelukt.put(entry.getKey(), entry.getValue());
				}
				else{
					mislukt.put(entry.getKey(), entry.getValue());
				}
			}
			Iterable<MandjeItem> mandjeItemsGelukt = new MandjeDAO().toonMandje(gelukt);
			Iterable<MandjeItem> mandjeItemsMislukt = new MandjeDAO().toonMandje(mislukt);
			request.setAttribute("mandjeItemsGelukt",mandjeItemsGelukt);
			request.setAttribute("mandjeItemsMislukt",mandjeItemsMislukt);
			session.removeAttribute("mandje");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW); // naar overzicht.jsp
	    dispatcher.forward(request, response);
	}
}
