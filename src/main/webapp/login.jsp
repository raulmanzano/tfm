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
System.out.println("Login validado");
SecurityFilter.doFilterLoginSuccess(request,response,false);
%>
<form action="pagina1.jsp">
		<!-- input type="hidden" name="token" value="<%=request.getAttribute("token")%>"> -->
		<input type="submit" value="Pagina 1">
</form>



<%
}else{
System.out.println("Login sin validar");
%>
<form action="login.jsp" method="post">
		Usuario <input type="text" name="usuario"><br/>
		Password <input type="text" name="pass"><br/>
		<!--   <input type="hidden" name="token" value="<%=request.getAttribute("token")%>">  -->
		<input type="submit" value="Login">
	</form>
<% 
}
%>
</body>
</html>









