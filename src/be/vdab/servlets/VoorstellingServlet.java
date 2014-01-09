package be.vdab.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.GenreDAO;
import be.vdab.dao.VoorstellingDAO;
import be.vdab.entities.Voorstelling;

/**
 * Servlet implementation class GenreServlet
 */
//@WebServlet("/voorstelling.htm")
public class VoorstellingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW ="/WEB-INF/JSP/voorstellingen.jsp";
	
  
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    Iterable<String>genres = new GenreDAO().findAllGenres();
		    request.setAttribute("genres", genres);
		
			String genre = request.getParameter("genre");
            List<Voorstelling> voorstellingen =  new VoorstellingDAO().findAllByGenre(genre);
			 //lees adv nummer voorstellingen van gevraagde genre in 
            if(voorstellingen.isEmpty()){
				request.setAttribute("fout", "Geen voorstellingen gevonden");
			}
			else {
				request.setAttribute("voorstellingen",voorstellingen);
				request.setAttribute("genre", genre);
		    }
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
	
	}

}
