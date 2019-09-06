<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import='br.edu.insper.model.DAO, br.edu.insper.model.User, java.util.*'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RetroHub</title>
</head>
<body>

	<h1>RetroHub</h1>
	<br /> Faça seu login caso já tenha um usuário!
	<br />
	<br />
	<form method='post'>
		Usuário: <br />
		<input type='text' name='username' /> <br /> 
		Senha: <br />
		<input type='password' name='password' /> <br /> 
		<input type='submit' />
	</form>
	<br /> Ainda não tem um usuário?
	<a href='cadastro'>Clique aqui!</a>
	<br />

</body>
</html>