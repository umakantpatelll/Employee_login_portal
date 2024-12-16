package com.employee.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uName = request.getParameter("name");
		String uEmail = request.getParameter("email");
		String uPwd = request.getParameter("pass");
		String uContact = request.getParameter("contact");
		String lastName = request.getParameter("lastname");
		String designation = request.getParameter("designation");
		String dateOfJoin = request.getParameter("dateOfJoining");
		String country = request.getParameter("country");
		String countryId = request.getParameter("cntry");
		String stateId = request.getParameter("state");
		String cityId = request.getParameter("city");
		
		RequestDispatcher dispatcher = null;
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_schema?useSSL=false", "root", "rootroot");
			PreparedStatement pst = con.prepareStatement("insert into login_users(user_name,password,user_email,user_mobile,last_name,designation,joining_date,country,country_id,state_id,city_id) values(?,?,?,?,?,?,?,?,?,?,?) ");
			pst.setString(1, uName);
			pst.setString(2, uPwd);
			pst.setString(3, uEmail);
			pst.setString(4, uContact);
			pst.setString(5, lastName);
			pst.setString(6, designation);
			pst.setString(7, dateOfJoin);
			pst.setString(8, country);
			pst.setString(9, countryId);
			pst.setString(10, stateId);
			pst.setString(11, cityId);
			
			
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
			if(rowCount > 0) {
				request.setAttribute("status", "success");
			}else {
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.close();
			} catch (Exception e2) {
				e.printStackTrace();
			}
		}
	}

}
