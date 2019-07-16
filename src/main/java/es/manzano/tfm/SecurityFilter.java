/**
 * 
 */
package es.manzano.tfm;

import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.manzano.tfm.exceptions.FilterException;
import es.manzano.tfm.filters.OneSessionFilter;
import es.manzano.tfm.filters.PreviousAllowedFilter;
import es.manzano.tfm.filters.PreviousCurrentFilter;
import es.manzano.tfm.filters.SessionUserFilter;
import es.manzano.tfm.filters.TokenFilter;

/**
 * @author manzano
 *
 */

public class SecurityFilter {
	
	//private static final Logger logger = LogManager.getLogger("es.manzano.tfm.SecurityFilter");
	 
	public static String NO_USER= "NOUSER";
	private static InfoDAO infoDAO = new InfoDAO();

	private static final SecurityFilter instance = new SecurityFilter();
	
	private SecurityFilter() 
    {  
    } 
	
	 public static SecurityFilter getInstance() {
	        return instance;
	    }
	
	 public static void reset()
	 {
		 SecurityFilter.infoDAO.reset();
	 }
	 
	public static void filter(String sessionId, String userId,String addr, String previous, String current, String tokenprevious, String tokencurrent) throws FilterException {
		Info currentInfo = new Info(sessionId, userId,addr, previous, current, tokenprevious, tokencurrent);
		Info previousInfo = infoDAO.get(currentInfo.getSessionId());
		
		if (previousInfo != null) {
			try {
				(new SessionUserFilter()).filter(previousInfo, currentInfo);
				(new PreviousCurrentFilter()).filter(previousInfo, currentInfo);
				(new PreviousAllowedFilter()).filter(previousInfo, currentInfo);
				(new TokenFilter()).filter(previousInfo, currentInfo);
				(new OneSessionFilter(infoDAO)).filter(previousInfo, currentInfo);
			} finally {
				infoDAO.reset(currentInfo);
			}
		}
		infoDAO.set(currentInfo);	
	}
	
	
	public static void doFilter(HttpServletRequest request, HttpServletResponse response,boolean csfrToken) throws Exception {
		//debe de existir una cookie con el usuario una vez que se haya validado
		//debe de enviarse el CRSF como un parametro
			//el valor esta en la request como un atributo
		
		String sessionId=request.getSession().getId();
		//logger.debug("sessionId " + sessionId);
		String userId=null;
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
			if 	(cookie.getName().equalsIgnoreCase("usuario")) userId=cookie.getValue(); 
		}
		if (userId==null || userId.trim().equalsIgnoreCase("")) userId=SecurityFilter.NO_USER;
		
		//logger.debug("userId " + userId);
		String addr = request.getRemoteAddr();
		String previous=Util.extractPageNameFromURLString(request.getHeader("referer"));
		//logger.debug("referer " + previous);
		String current=Util.extractPageNameFromURLString(request.getRequestURI());
		//logger.debug("current " + current);
		String tokenprevious= request.getParameter("token");
		//logger.debug("tokenprevious " + tokenprevious);
		String tokencurrent=null;
		if (csfrToken)
		{String token = String.valueOf(100000 + new Random().nextFloat() * 900000);
		request.setAttribute("token",(String)token);
		tokencurrent=token;
		}
		//logger.debug("tokencurrent " + tokencurrent);
	
		Info currentInfo = new Info(sessionId, userId, addr, previous, current, tokenprevious, tokencurrent);
		Info previousInfo = infoDAO.get(currentInfo.getSessionId());
		
		//logger.info("previousInfo " + previousInfo);
		//logger.info("currentInfo " + currentInfo);
		System.out.println("previousInfo " + previousInfo);
		System.out.println("currentInfo " + currentInfo);
		
		
		if (previousInfo != null) {
			try {
				(new SessionUserFilter()).filter(previousInfo, currentInfo);
				(new PreviousCurrentFilter()).filter(previousInfo, currentInfo);
				(new PreviousAllowedFilter()).filter(previousInfo, currentInfo);
				(new TokenFilter()).filter(previousInfo, currentInfo);
				(new OneSessionFilter(infoDAO)).filter(previousInfo, currentInfo);
			}catch (Exception e) {
				//logger.info(e);
				System.out.println(e);
				infoDAO.reset(currentInfo);
				infoDAO.set(currentInfo);
				response.sendRedirect("logout.jsp");
				return;
		}}
		infoDAO.set(currentInfo);
	}
		
	
	public static void doFilter(HttpServletRequest request, HttpServletResponse response) throws Exception {
		doFilter(request,response,false);
	}
	
	
	public static void doFilterLoginSuccess(HttpServletRequest request, HttpServletResponse response,boolean csfrToken) throws Exception {
		Cookie cookie = new Cookie("usuario",request.getParameter("usuario"));
		cookie.setMaxAge(-1);
		response.addCookie(cookie);
		doFilter(request,response,csfrToken);
	}
	
	
	public static void doFilterLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		Cookie cookie = new Cookie("usuario",null);
		//Cookie cookie = new Cookie("usuario",SecurityFilter.NO_USER;);
		cookie.setMaxAge(-1);
		response.addCookie(cookie);
	}
	
	

	
	
	

}
