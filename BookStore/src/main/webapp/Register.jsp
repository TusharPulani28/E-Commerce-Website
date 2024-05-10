<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration</title>
<style>

h2{
	text-align:center;
	color:blue;
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

table {
    border-spacing: 10px;
}


label {
    font-weight: bold;
}


input[type="text"],
input[type="number"],
input[type='email'],
input[type="password"],
input[type="submit"] {
    width: 200px;
    padding: 5px;
    margin-top: 5px;
}


#btn {
    background-color: #4CAF50; 
    color: white;
    border: none;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 10px 70px;
    cursor: pointer;
    border-radius: 5px;
}

#btn_view {
	
    background-color: #008CBA; 
    color: white;
    padding: 10px 20px;
    text-decoration: none;
    display: block;
    font-size: 16px;
    margin: 10px auto;
    width: fit-content;
    border-radius: 5px;
}


</style>
</head>
<body>
	<h2>REGISTRATION DETAILS</h2>
	<form action="RegServlet" method="post" >
    <table style="border-spacing:10px">
        <tr>
            <td><label for="fullname">Full Name:</label></td>
            <td><input type="text" id="fullname" name="fullname" required></td>
        </tr>
        <tr>
            <td><label for="email">Email:</label></td>
            <td><input type="email" id="email" name="email" required></td>
        </tr> 
        <tr>
            <td><label for="mobile">Mobile No:</label></td>
            <td><input type="number" id="mobile" name="phone" required></td>
        </tr>
         
        <tr>
            <td><label for="username">Username:</label></td>
            <td><input type="text" id="username" name="username" required></td>
        </tr>
        
        <tr>
            <td><label for="pass">Password:</label></td>
            <td><input type="password" id="pass" name="password" required></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Submit" id='btn'></td>
        </tr>
    </table>
</form>
	
</body>
</html>