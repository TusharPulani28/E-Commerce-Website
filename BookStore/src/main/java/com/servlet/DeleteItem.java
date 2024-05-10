package com.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Servlet implementation class DeleteItem
 */
public class DeleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement pst = null;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteItem() {
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
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookProject", "root", "Database@sql20");
			String sql = "Select * FROM Items WHERE b_name = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					String query = "DELETE FROM ShoppingCart WHERE itemName = ?";
					pst = con.prepareStatement(query);
					pst.setString(1, name);
					int rowsUpdated = pst.executeUpdate();
					if (rowsUpdated > 0) {
//              	response.sendRedirect("UpdateSuccess.jsp");
						System.out.println("Deleted Successfully");
						PrintWriter out = response.getWriter();
						response.setContentType("text/html");
						out.println("<h2 style='text-align:center;color:orange'>"+name + " Removed From Cart</h2>");
          	 
						RequestDispatcher rd = request.getRequestDispatcher("/Cart.jsp");
						rd.include(request, response);
					} 
				}
		}catch (ClassNotFoundException e) {
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
