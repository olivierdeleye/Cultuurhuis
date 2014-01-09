package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.GenreDAO;

/**
 * Servlet implementation class StartServlet
 */
//@WebServlet("/start.htm")
public class StartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW ="/WEB-INF/JSP/startpagina.jsp";
       
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Iterable<String>genres = new GenreDAO().findAllGenres();
		request.setAttribute("genres", genres);
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
		
	}

}
