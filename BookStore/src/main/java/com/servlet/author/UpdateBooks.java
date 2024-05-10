package com.servlet.author;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Servlet implementation class UpdateBooks
 */
@MultipartConfig
public class UpdateBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String name = request.getParameter("b_name");
		String price = request.getParameter("b_price");
		String author = request.getParameter("b_author");
		
		
		Part img = request.getPart("img");
				
		String imageFileName = img.getSubmittedFileName();
		String uploadPath = "C:/Users/Admin/eclipse/java-2024-03/Eclipse-workspace/BookStore/src/main/webapp/images/"
				+ imageFileName; // upload path where we have to upload our actual image
		try {

			FileOutputStream fos = new FileOutputStream(uploadPath);
			InputStream is = img.getInputStream();

			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookProject", "root", "Database@sql20");
			// Query the database to get the bookId based on other attributes
			
//            String sql = "SELECT id FROM Items WHERE B_name=? AND B_price=?";
//            ps = con.prepareStatement(sql);
//            ps.setString(1, name);
//            ps.setString(2, price);
//            
//            rs = ps.executeQuery();
		
         // If a record is found, update the book
//            if (rs.next()) {
//                int bookId = rs.getInt("id");
//                System.out.println("bookId" + bookId);
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookProject", "root", "Database@sql20");
//			HttpSession hs = request.getSession();
//			String UpdateBook = (String)hs.getAttribute("key_name");
//			
			String sql = "UPDATE Items SET B_name=?,B_price=?,B_author=?,B_filename=? WHERE B_name = ?";
			ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, price);
            ps.setString(3, author);
            ps.setString(4, imageFileName);
            ps.setString(5, name);
            
            int rowsUpdated = ps.executeUpdate();
            
            if (rowsUpdated > 0) {

            	System.out.println("Updated Successfully");
            	PrintWriter out = response.getWriter();
             	 response.setContentType("text/html");
             	out.println("<h2 style='text-align:center;color:orange'>" + name + " Updated Successfully</h2>");
                RequestDispatcher rd = request.getRequestDispatcher("/AdminDashboard.jsp");
                rd.include(request, response);
//                hs.setAttribute("key_name", name);
                
            } else {
                response.getWriter().println("Failed to update Book details!");
                RequestDispatcher rd = request.getRequestDispatcher("/UpdateBook.jsp");
                rd.forward(request,response);
            	}
//            }
		} catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try { if (ps != null) ps.close(); } catch (Exception e) { /* ignored */ }
            try { if (con != null) con.close(); } catch (Exception e) { /* ignored */ }
        }
    
		
	}

}
