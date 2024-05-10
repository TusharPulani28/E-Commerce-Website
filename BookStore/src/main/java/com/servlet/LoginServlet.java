package com.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		String username = request.getParameter("user");
        String password = request.getParameter("pwd");
       
        
        Connection conn = null;
        PrintWriter out = response.getWriter(); 
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookProject", "root", "Database@sql20");
            String sql = "SELECT * FROM register WHERE Username=? AND UserPassword=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
         
            if (rs.next()) {
                // Login successful, redirect to filelist.jsp
            	 HttpSession session = request.getSession();
	                session.setAttribute("username", username);
	                session.setAttribute("password", password);
				if(username.equals("admin") && password.equals("09876")) {
					System.out.println("Admin log in successfully");
					RequestDispatcher rd = request.getRequestDispatcher("/AdminDashboard.jsp");
	                rd.forward(request, response);
				}
				else{System.out.println("log in successfully");
				
                RequestDispatcher rd = request.getRequestDispatcher("/Dashboard.jsp");
                rd.forward(request, response);
                }
            } else {
                // Login failed, redirect back to login.jsp
            	response.setContentType("text/html");
            	out.println("<h2 style=color:red;font-style:bold;>Incorrect Username and Password..</h2>");
                RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
                rd.include(request, response);
        		}
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception, show error page, or redirect to an error page
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // Close resources in the finally block
        }

	}

	
}
