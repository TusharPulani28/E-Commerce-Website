<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Admin</title>
<style>
	
</style>
<%@include file="Includes/Header.jsp" %>
</head>
<body>
	<%
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;  
            
            String username = (String) request.getSession().getAttribute("username");
        %>
        
	<%@include file="Includes/navbar.jsp" %>
 	<%
           
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookProject", "root", "Database@sql20"); 
            String sql = "SELECT * FROM Items ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
     %>
            
<div class="container">
	<h2 style="text-align:center; color:skyblue;">Welcome <%=username %></h2>
    <div class="card-header my-3 text-white bg-dark p-2">All Books</div>
    <div class="row d-flex justify-content-start">
        <% 
        while(rs.next()) {
        %>
        <div class="col-md-3 my-3">
            <div class="card" style="width: 18rem;">
                <img src="images/<%=rs.getString(5) %>" class="card-img-top m-auto" alt="..." style="width: 200px; height: 200px;">
                <div class="card-body">
                    <h5 class="card-title"><%=rs.getString(2) %></h5>
                    <h6 class="price">Price: <%=rs.getInt(3) %></h6>
                    <h6 class="Author">Author: <%=rs.getString(4) %></h6>
                    <div class="mt-3">
                        <a href="UpdateBook.jsp?b_name=<%=rs.getString(2) %>" class="btn btn-primary">Update</a>
                        <a href="DeleteBook?b_name=<%=rs.getString(2) %>" class="btn btn-danger">Delete</a>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
    </div>
    <div class="text-center mt-4">
        <a href="AddBooks.jsp" class="btn btn-info text-white">Add Books</a>
    </div>
</div>

<%@include file="Includes/Footer.jsp" %>
</body>
</html>