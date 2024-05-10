<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<title>Add To Cart</title>
<%@include file="Includes/Header.jsp" %>
</head>
<body>
<%@include file="Includes/navbar.jsp" %>

<br><br>
<div class="container">
   <!--   <div class="d-flex py-3"><h2>Total Price: <span id="totalPrice">800</span></h2> <a class='btn btn-primary' href="#">Check Out</a></div>-->
    <table class="table table-light">
        <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Price</th>
                <th scope="col">Author</th>
                <th scope="col">Quantity</th>
                <th scope="col">Cancel</th>
            </tr>
        </thead>
        <tbody>
            <%!
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            %>
            <% 
            try {
                // Establish database connection
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookProject", "root", "Database@sql20");
                
                // Query to retrieve shopping cart items from the database
                String sql = "SELECT itemName, itemPrice, itemAuthor, quantity FROM ShoppingCart";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String itemName = rs.getString("itemName");
                    int itemPrice = rs.getInt("itemPrice");
                    String itemAuthor = rs.getString("itemAuthor");
                    int quantity = rs.getInt("quantity");
            %>
            <tr>
                <td><%=itemName%></td>
                <td><%=itemPrice%></td>
                <td><%=itemAuthor%></td>
                <td>
                    <form action="" method="post" class="form-inline">
                        <input type="hidden" value="<%=itemName%>" name="itemName" class="itemName">
                        <div class="input-group ">
                            <div class="input-group-prepend">
                                <button type="button" class="btn btn-sm btn-primary btn-decre" onclick="decrementQuantity(this)">-</button>
                            </div>
                            <input type="text" name='quantity' value='<%=quantity%>' style="width:10px" class="form-control form-control-sm text-center quantity" readonly>
                            <div class="input-group-append">
                                <button type="button" class="btn btn-primary btn-sm btn-incre" onclick="incrementQuantity(this)">+</button>
                            </div>
                        </div>
                    </form>
                </td>
                <td><a class="btn btn-sm btn-danger" href="DeleteItem?b_name=<%=itemName %>">Remove</a></td>
            </tr>
            <% 
                }
            } catch (SQLException ex) {
                // Handle database errors
                ex.printStackTrace();
            } finally {
                // Close resources
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            %>
        </tbody>
    </table>
</div>

<script>
    function incrementQuantity(button) {
        var quantityInput = button.parentNode.previousElementSibling.querySelector('input[name="quantity"]');
        var quantity = parseInt(quantityInput.value);
        quantityInput.value = quantity + 1;
        updateTotalPrice();
    }

    function decrementQuantity(button) {
        var quantityInput = button.parentNode.nextElementSibling.querySelector('input[name="quantity"]');
        var quantity = parseInt(quantityInput.value);
        if (quantity > 1) {
            quantityInput.value = quantity - 1;
            updateTotalPrice();
        }
    }

    function updateTotalPrice() {
        var totalPrice = 0;
        var quantityInputs = document.querySelectorAll('.quantity');
        var priceElements = document.querySelectorAll('td:nth-child(2)');

        for (var i = 0; i < quantityInputs.length; i++) {
            var quantity = parseInt(quantityInputs[i].value);
            var price = parseInt(priceElements[i].innerText);
            totalPrice += quantity * price;
        }

        document.getElementById('totalPrice').innerText = totalPrice;
    }
</script>




<%@include file="Includes/Footer.jsp" %>


</body>
</html>
