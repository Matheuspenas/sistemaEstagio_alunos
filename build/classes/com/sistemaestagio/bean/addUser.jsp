<%@ page import="com.sistemaestagio.dao.UserDao"%>
<%@ page import="javax.servlet.RequestDispatcher" %>
<jsp:useBean id="u" class="com.sistemaestagio.bean.User"> </jsp:useBean>
<jsp:setProperty property="*" name="u"/>property

<%
	int i = UserDao.addUser(u);
	request.setAttribute("resultado", i > 0 ? "sucess" : "error");
	RequestDispatcher rd = request.getRequestDispatcher("formUser.jsp");
	rd.forward(request, response);
%>