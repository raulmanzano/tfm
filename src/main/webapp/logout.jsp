<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,es.manzano.tfm.*" %>
<html>
<body>
<%System.out.println("Log out"); 
SecurityFilter.doFilterLogout(request,response);
%>
<h2>LogOut</h2>
<p>
El usuario ha sido sacado de sesion.Si quiere cintinuar trabajando, validese de nuevo en
		<a href="login.jsp">Login</a>
	</p>


</body>
</html>
