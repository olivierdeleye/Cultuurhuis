package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BevestigingServlet
 */
//@WebServlet("/bevestiging.htm")
public class BevestigingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestiging.jsp";
	
   
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW); // naar bevestiging.jsp
	    dispatcher.forward(request, response);
	}



}
