<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,es.manzano.tfm.*" %>
<%
SecurityFilter.doFilter(request,response);
 %>
<html>
<body>
<h2>Pagina privada 1</h2>

<form action="pagina2.jsp">
		<input type="hidden" value="<%=request.getAttribute("token")%>">
		<input type="submit" value="Pagina 2">
</form>

<form action="logout.jsp">
		<input type="hidden" value="<%=request.getAttribute("token")%>">
		<input type="submit" value="Logout">
</form>

</body>
</html>