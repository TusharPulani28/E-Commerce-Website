package com.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Servlet implementation class OrderList
 */
public class OrderList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement pst = null;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String name = request.getParameter("b_name");
		int quantity = 1;
		ShoppingCart.addToCart(request.getSession(), name,quantity);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookProject", "root", "Database@sql20");
			String sql = "Select * FROM Items WHERE b_name = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				String b_Name = rs.getString(2); // Assuming item name is in the second column
                int b_Price = rs.getInt(3); // Assuming item price is in the third column
                String b_Author = rs.getString(4);
                sql = "INSERT INTO ShoppingCart (itemName, itemPrice, itemAuthor,quantity) VALUES (?, ?, ?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, b_Name);
                pst.setInt(2, b_Price);
                pst.setString(3, b_Author);
                pst.setInt(4, quantity);
                pst.executeUpdate();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/Cart.jsp");
			rd.forward(request, response);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
            // Close resources
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            }catch (SQLException e) {
                    
					e.printStackTrace();;
                }
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
