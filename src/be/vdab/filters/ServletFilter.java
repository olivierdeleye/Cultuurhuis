package be.vdab.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class ServletFilter
 */
//@WebFilter("*.htm")
public class ServletFilter implements Filter {

	private String encoding; //instance variable, wordt geinitialiseerd met init-param uit web-xml
    /**
     * Default constructor. 
     */
    public ServletFilter() {
        
    }

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		
		encoding = fConfig.getInitParameter("encoding");
	}
}
