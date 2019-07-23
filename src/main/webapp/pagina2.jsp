<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,es.manzano.tfm.*" %>
<%
//pagina generica sin uso del token CSRF
System.out.println("pagina2");
SecurityFilter.doFilter(request,response);
 %>
<html>
<body>
<h2>Pagina privada 2</h2>

<form action="pagina3.jsp">
		<!--   <input type="hidden" name="token" value="<%=request.getAttribute("token")%>"> -->
		<input type="submit" value="Pagina 3">
</form>

<form action="logout.jsp">
		<!--   <input type="hidden" name="token" value="<%=request.getAttribute("token")%>"> -->
		<input type="submit" value="Logout">
</form>

</body>
</html>
