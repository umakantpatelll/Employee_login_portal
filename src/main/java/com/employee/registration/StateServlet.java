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
import com.employee.model.State;
import com.employee.DAO.StateDAO;


@WebServlet("/StateServlet")
public class StateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StateDAO stateDAO;
    
	public void init() {
		stateDAO = new StateDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("========================== inside StateServlet =============");
		Map<Integer, String> statesMap = stateDAO.getAllListOfStatesMap();
		Gson gson = new Gson();
		String statesString = gson.toJson(statesMap);
		response.setContentType("text/html");
		response.getWriter().write(statesString);
	}

	
}

