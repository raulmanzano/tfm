<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,es.manzano.tfm.*" %>
<%
System.out.println("pagina3");
SecurityFilter.doFilter(request,response);
 %>
<html>
<body>
<h2>Pagina privada 3</h2>

<form action="pagina1.jsp">
		<!--  <input type="hidden" name="token" value="<%=request.getAttribute("token")%>"> -->
		<input type="submit" value="Pagina 1">
</form>

<form action="logout.jsp">
		<!--  <input type="hidden" name="token" value="<%=request.getAttribute("token")%>"> -->
		<input type="submit" value="Logout">
</form>

</body>
</html>
