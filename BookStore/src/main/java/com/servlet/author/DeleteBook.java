package com.servlet.author;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


/**
 * Servlet implementation class DeleteBook
 */
@MultipartConfig
public class DeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con = null;
       PreparedStatement pst = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		System.out.println("Delete");
//		HttpSession hs = request.getSession();
//		String deleteBook = (String)hs.getAttribute("key_name");
		String name = request.getParameter("b_name");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookProject", "root", "Database@sql20");
			String query = "DELETE FROM Items WHERE B_name = ?";
			pst = con.prepareStatement(query);
            pst.setString(1, name);
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
//              response.sendRedirect("UpdateSuccess.jsp");
          	 System.out.println("Deleted Successfully");
          	 PrintWriter out = response.getWriter();
          	 response.setContentType("text/html");
          	out.println("<h2 style='text-align:center;color:orange'>" + name + " Deleted Successfully</h2>");
          	 
              RequestDispatcher rd = request.getRequestDispatcher("/AdminDashboard.jsp");
              rd.include(request, response);
          } else {
              response.getWriter().println("Failed to Delete user data!");
              
          }
		} catch (SQLException e) {
          e.printStackTrace();
          response.getWriter().println("Error: " + e.getMessage());
      } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
          try { if (pst != null) pst.close(); } catch (Exception e) { /* ignored */ }
          try { if (con != null) con.close(); } catch (Exception e) { /* ignored */ }
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