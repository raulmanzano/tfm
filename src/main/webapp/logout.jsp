<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,es.manzano.tfm.*" %>
<html>
<body>
<%
//página de logout. apartir de esta pagian lso usuairos están sin validar y no tienen acceso a las paginas privadas
System.out.println("Log out"); 
SecurityFilter.doFilterLogout(request,response);
%>
<h2>LogOut</h2>
<p>
El usuario ha sido sacado de sesion.Si quiere continuar trabajando, validese de nuevo en
		<a href="login.jsp">Login</a>
	</p>


</body>
</html>
