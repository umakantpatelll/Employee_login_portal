package com.employee.registration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.employee.DAO.CountriesDAO;
import com.employee.DAO.UserDAO;
import com.employee.model.Country;
import com.employee.model.User;

/**
 * Servlet implementation class EmployeeServlet
 */

//this one is running perfectly and working fine with latest pagination implementation 
@WebServlet("/")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;
	private CountriesDAO countriesDAO;

	public void init() {
		userDAO = new UserDAO();
		countriesDAO = new CountriesDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println("Action from switch case "+action);
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			case "/showUser":
				System.out.println("==========Show user employee============");
				showUser(request, response);
				break;
			case "/showCountries":
				showCountries(request, response);
				break;
			case "/list":
				System.out.println("=============Employee List case");
				listUser(request, response);
				break;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
       
   
	private void showCountries(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Country> listcountries = countriesDAO.getAllListOfCountries();
		request.setAttribute("countries", listcountries);
		RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
		dispatcher.forward(request, response);
		
	}

	private void showUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("===================id "+id);
		int cntryid = Integer.parseInt(request.getParameter("cntryid"));
		int stateid = Integer.parseInt(request.getParameter("stateid"));
		int cityid = Integer.parseInt(request.getParameter("cityid"));
		System.out.println("country id "+cntryid);
		System.out.println("state id "+stateid);
		System.out.println("city id "+cityid);
		System.out.println(" id from edit form  "+id);
		User existingUser = userDAO.selectUserById(id, cntryid, stateid, cityid);
		System.out.println("2222222222222222222222");
		System.out.println(existingUser.getFirst_name());
		RequestDispatcher dispatcher = request.getRequestDispatcher("showuser.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);
		
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = userDAO.selectAllUsers(request);
		request.setAttribute("listUser", listUser);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("searchEmp.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int empid = Integer.parseInt(request.getParameter("id"));

		int cntryid = Integer.parseInt(request.getParameter("cntryid"));
		int stateid = Integer.parseInt(request.getParameter("stateid"));
		int cityid = Integer.parseInt(request.getParameter("cityid"));

		System.out.println(" id from edit form  "+empid);
		User existingUser = userDAO.selectUserById(empid, cntryid, stateid, cityid);
		System.out.println(existingUser.getFirst_name());
		RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
		request.setAttribute("user", existingUser);
		System.out.println(existingUser.getCountryId());
		System.out.println(existingUser.getStateId());
		System.out.println(existingUser.getCityId());
		dispatcher.forward(request, response);
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String first_name = request.getParameter("name");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		String designation = request.getParameter("designation");
		String joiningDate = request.getParameter("dateOfJoining");
		String contact = request.getParameter("contact");
		String country = request.getParameter("country");
		String countryId = request.getParameter("cntry");
		String stateId = request.getParameter("state");
		String cityId = request.getParameter("city");
		User newUser = new User(first_name, password, lastname, email, contact, joiningDate, country, designation, countryId, stateId, cityId);
		userDAO.insertUser(newUser);
		response.sendRedirect("list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String first_name = request.getParameter("name");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		String designation = request.getParameter("designation");
		String joiningDate = request.getParameter("dateOfJoining");
		String contact = request.getParameter("contact");
		String country = request.getParameter("country");
		String countryId = request.getParameter("cntry");
		String stateId = request.getParameter("state");
		String cityId = request.getParameter("city");

		User book = new User(id, first_name, password, lastname, email, contact, joiningDate, country, designation, countryId, stateId, cityId);
		userDAO.updateUser(book);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("list");

	}
	
}

