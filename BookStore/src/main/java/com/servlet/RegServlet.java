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
 * Servlet implementation class RegServlet
 */

public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter out = null;
	Connection conn = null;
    PreparedStatement pst = null;
  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
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
		try {
            out = response.getWriter();
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
    			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookProject", "root", "Database@sql20");
                String query = "INSERT INTO register(Fullname,Email,Mobile,Username,UserPassword) values(?,?,?,?,?)";
                pst = conn.prepareStatement(query);
                pst.setString(1, fullname);
                pst.setString(2, email);
                pst.setString(3, phone);
                pst.setString(4, username);
                pst.setString(5, password);
                int status = pst.executeUpdate();
                if (status > 0) {
                	response.setContentType("text/html");
                	out.print("<h2 style='color:blue;font-style:bold;'>Registered Successfully...</h2>");
                    RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
                    rd.include(request, response);
                    System.out.println("Data Saved successfully...");
                }
            } catch (SQLException e) {
                out.println("Exception: " + e);
                System.out.println("Exception1: " + e);
            } catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
                try {
                    if (pst != null) {
                        pst.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    out.println(e);
                }
            }

        } catch (IOException | ServletException e) {
            out.println("Exception: " + e);
            System.out.println("Exception2: " + e);
        	}

        }
	}



