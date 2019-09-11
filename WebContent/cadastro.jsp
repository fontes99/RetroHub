<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RetroHub - Cadastro</title>
<link rel="stylesheet" href="style.css"/>
</head>
<body>

<h1>Bem-vindo ao RetroHub!</h1>
<a href='login'>Voltar ao login</a>
<hr/><br/><br/>

Cadastre-se abaixo: <br/><br/>
<form method='post'>
Usuário: <br/><input type='text' name='name'required='required'/> <br/>
Senha: <br/><input type='password' name='password'required='required'/> <br/>
Confirmar senha: <br/><input type='password' name='passwordConf'required='required'/> <br/>
<input type='submit'/>
</form>

</body>
</html>