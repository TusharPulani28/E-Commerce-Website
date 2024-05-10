<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<style>
h2{
	text-align:center;
	color:blue;
	text-shadow: 2px 1px blue;
}
	form {
    margin: auto; 
    display: flex;
    flex-direction: column;
    align-items: center; 
    justify-content: center;
    padding: 20px;
    background-color: #f0f0f0; 
    border-radius: 10px;
    width:500px;
}
input[type="text"],
input[type="number"],
input[type="password"],
input[type="submit"] {
    width: 200px;
    padding: 5px;
    margin-top: 5px;
}
input[type='submit']{
	color:white;
	background-color:#4CAF50;
	border:none;
	padding: 10px 20px;
	border-radius:10px;
	cursor:pointer;
}
div{
	text-align: center;
	display:flex;
	justify-content:center;
}
a{
	margin:10px 10px;
	display:inline-block;
	text-decoration:none;
	background-color:#3b00b3;
	color:white;
	padding:10px;
	border-radius:10px;
}
</style>
</head>
<body>
	
	<h2>LOGIN</h2>
	 
	<form action='LoginServlet' method='POST'>
	<label>Username:</label> <input type='text' id="user" name='user'><br><br>
	<label>Password:</label> <input type='password' id="pwd" name='pwd'><br>
	
	<input type='submit' value='Login' id="btn">
	</form>
	<div>
	
	<p>Don't Have any Account??</p>
	<a href='Register.jsp'>Register</a>
	</div>
	
	</body>
	</html>
	
	
	
	
	

	
	
	
