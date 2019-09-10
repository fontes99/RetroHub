<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RetroHub - Chat</title>
</head>
<body>
<%@ page import="java.util.*,br.edu.insper.controller.*,br.edu.insper.model.*" %>
<%-- <% session.setAttribute("backToMain", "true"); %> --%>
<h1><a href="main">RetroHub</a></h1>
<a href="login">log out</a>
<hr/>
<% 
DAO dao = new DAO();
String myURL = request.getParameter("myURL");
String chatName = request.getParameter("subjectName");
%>
<h1>Assunto do chat: <i><%=chatName%></i></h1>

<table border='1'>
<%
 List<Message> messages = dao.getChatMessages(myURL);
 for (Message message : messages ) { %>
 <tr>
 <% String user = dao.getUserById(message.getIdUser()); %>
 <td><%=user%></td>
 <td><%=message.getMsg()%></td>
 <td><%=message.getTime()%></td>
 </tr>
<% } %>
</table>
<form method='post'>
<input placeholder="Digite aqui sua mensagem" type='text' name='newMsg'/>
<input type='submit' placeholder='Enviar'/>
</form>

</body>
</html>