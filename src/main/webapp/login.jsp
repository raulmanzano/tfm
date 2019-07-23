<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,es.manzano.tfm.*" %>
<html>
<body>
<h2>Login</h2>
<%
//bloque de autentificacion
//si lso parametros pasados para validarse son correctos, pinta la página respuesta, sino muestra el formulario de validacion
//simplemente se verifica que el nombre de usuario se aigual que la password
if 
((request.getParameter("usuario")!=null) 
&& (request.getParameter("pass")!=null)
&& ((String)request.getParameter("pass")).equalsIgnoreCase((String)request.getParameter("usuario")))
{
System.out.println("Login validado");
SecurityFilter.doFilterLoginSuccess(request,response,false);
%>
<form action="pagina1.jsp">
		<!-- DESHABILITADO. El enlace se presenta como un formulario por si se quiere utilizar parametro csrf en la rtransciones. -->
		<!-- input type="hidden" name="token" value="<%=request.getAttribute("token")%>"> -->
		<input type="submit" value="Pagina 1">
</form>



<%
//bloque de formulario de autentificacion
/la propia pagina es a la que realiza el proceso de autorización
}else{
System.out.println("Login sin validar");
%>
<form action="login.jsp" method="post">
		Usuario <input type="text" name="usuario"><br/>
		Password <input type="text" name="pass"><br/>
		<!-- DESHABILITADO. Si se quiere se puede utilizar en envío de token CSRF -->
		<!--   <input type="hidden" name="token" value="<%=request.getAttribute("token")%>">  -->
		<input type="submit" value="Login">
	</form>
<% 
}
%>
</body>
</html>









