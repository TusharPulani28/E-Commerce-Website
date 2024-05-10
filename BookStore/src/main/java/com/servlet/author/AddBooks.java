package com.servlet.author;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet("/AddBooks")
public class AddBooks extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);
		resp.getWriter().append("Upload Successfully ");
		String name = req.getParameter("b_name");
		String price = req.getParameter("b_price");
		String author = req.getParameter("b_author");
		
		System.out.println("Do Post Method");
		Part img = req.getPart("img");

		String imageFileName = img.getSubmittedFileName(); // get selected image file name
		System.out.println("selected image file name: " + imageFileName);

		String uploadPath = "C:/Users/Admin/eclipse/java-2024-03/Eclipse-workspace/BookStore/src/main/webapp/images/"
				+ imageFileName; // upload path where we have to upload our actual image
		System.out.println("Upload Path: " + uploadPath);

		// uploading our selected image into images folder
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

		// getting database connection (jdbc code)
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookProject", "root", "Database@sql20");
			PreparedStatement pst;
			String query = "INSERT INTO Items(B_name,B_price,B_author,B_filename)VALUES(?,?,?,?)";
			pst = con.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, price);
			pst.setString(3, author);
			pst.setString(4, imageFileName);
			int row = pst.executeUpdate(); // it returns no. of rows affected

			if (row > 0) {
				HttpSession hs = req.getSession();
				hs.setAttribute("key_name",name);
				
				RequestDispatcher rd = req.getRequestDispatcher("/AdminDashboard.jsp");
				rd.forward(req, resp);
				System.out.println("Book Saved Into Database Successfully !!!");

			} else {
				System.out.println("Failed to Save Image..");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	}

