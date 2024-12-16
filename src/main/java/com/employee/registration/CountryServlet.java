package com.employee.registration;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.employee.DAO.CityDAO;
import com.employee.DAO.CountriesDAO;
import com.employee.DAO.StateDAO;
import com.employee.model.City;
import com.employee.model.Country;
import com.employee.model.State;


@WebServlet("/CountryServlet")
public class CountryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CountriesDAO countriesDAO;
	
	private StateDAO stateDAO;
	
	private CityDAO cityDAO;
	
	
	public void init() {
		countriesDAO = new CountriesDAO();
		stateDAO = new StateDAO();
		cityDAO = new CityDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("========================== inside countryServlet =============");
		String op = request.getParameter("operation");
		if (op.equals("country")) {
			List<Country> clist = countriesDAO.getAllListOfCountries();
			Map<Integer, String> countryMap = countriesDAO.getAllListOfCountriesMap();
			Gson gson = new Gson();
			String countriesString = gson.toJson(clist);
			System.out.println("==========="+countriesString);
			response.setContentType("text/html");
			response.getWriter().write(countriesString);
		}
		if (op.equals("state")) {
			System.out.println("========================== inside StateServlet =============");
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
			List<State> statelist = stateDAO.selectStateListByCountryId(id);
			Map<Integer, String> statesMap = stateDAO.getAllListOfStatesMap();
			Gson gson = new Gson();
			String statesString = gson.toJson(statelist);
			System.out.println("==========="+statesString);
			response.setContentType("text/html");
			response.getWriter().write(statesString);
		}
		if (op.equals("city")) {
			int id = Integer.parseInt(request.getParameter("id"));
			List<City> citylist = cityDAO.selectCityListByStateId(id);
			System.out.println("========================== inside CityServlet =============");
			Map<Integer, String> citiesMap = cityDAO.getAllListOfcitiesMap();
			Gson gson = new Gson();
			String countryString = gson.toJson(citylist);
			System.out.println("==========="+countryString);
			response.setContentType("text/html");
			response.getWriter().write(countryString);
		}
	}


}
