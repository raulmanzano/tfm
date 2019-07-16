<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,es.manzano.tfm.*" %>
<html>
<body>
<h2>Login</h2>
<%
if 
((request.getParameter("usuario")!=null) 
&& (request.getParameter("pass")!=null)
&& ((String)request.getParameter("pass")).equalsIgnoreCase((String)request.getParameter("usuario")))
{
javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("usuario",request.getParameter("usuario"));
response.addCookie(cookie);

SecurityFilter.doFilter(request,response);
%>
<form action="pagina1.jsp">
		<input type="hidden" value="<%=request.getAttribute("token")%>">
		<input type="submit" value="Pagina 1">
</form>



<%
}else{
 %>
<form action="login.jsp">
		Usuario <input type="text" name="usuario"><br/>
		Password <input type="text" name="pass"><br/>
		<input type="hidden" value="<%=request.getAttribute("token")%>">
		<input type="submit" value="Login">
	</form>
<% 
}
%>
</body>
</html>









