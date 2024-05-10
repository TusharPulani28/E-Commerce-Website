<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Books</title>
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
input[type='file'],
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
	
	
</style>
</head>
<body>
	<h2>Update Book</h2>
	<form action="UpdateBooks" method="post" enctype="multipart/form-data">
	<table>
	
	<tr>
		<td><label for="name">Title:</label></td>
		<td><input type='text' name="b_name" id="name"></td>
	</tr>
	<tr>
		<td><label for="price">Price:</label></td>
		<td><input type='number' name="b_price" id="price"></td>
	</tr>
	<tr>
		<td><label for="author">Author:</label></td>
		<td><input type='text' name="b_author" id="author"></td>
	</tr>
	<tr>
		<td><label for='image'>Upload Image:</label></td>
		<td><input type="file" name="img" id="image"></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" id='btn' value="Update"></td>
	</tr>
</table>
</form>
</body>
</html>