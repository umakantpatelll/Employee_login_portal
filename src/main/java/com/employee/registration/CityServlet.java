package com.employee.registration;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.employee.DAO.CityDAO;

/**
 * Servlet implementation class CityServlet
 */
@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CityDAO cityDAO;
    
	public void init() {
		cityDAO = new CityDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("========================== inside CityServlet =============");
		Map<Integer, String> citiesMap = cityDAO.getAllListOfcitiesMap();
		Gson gson = new Gson();
		String statesString = gson.toJson(citiesMap);
		response.setContentType("text/html");
		response.getWriter().write(statesString);
	}

}
