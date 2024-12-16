package com.employee.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.employee.model.Country;
import com.employee.model.State;
import com.employee.model.User;

public class StateDAO {
	
	private String jdbcURl = "jdbc:mysql://localhost:3306/login_schema?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUserName = "root";
	private String jdbcPassword = "rootroot";
	
	private static final String INSERT_STATE_SQL = "insert into state(name, country_id) values(?,?) ";
	
	private static final String SELECT_STATE_BY_ID = "SELECT state_id, name FROM state where state_id=?";
	
	private static final String SELECT_STATE_BY_COUNTRY_ID = "SELECT * FROM state where country_id=?";
	
	private static final String UPDATE_STATE_SQL = "UPDATE state set name=?;";
	
	private static final String SELECT_ALL_STATES = "SELECT * from State";

	

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURl, jdbcUserName, jdbcPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}
	
	/*
	 * public void insertState(State state) { try (Connection connection =
	 * getConnection(); PreparedStatement pst =
	 * connection.prepareStatement(INSERT_STATE_SQL)) { pst.setString(1,
	 * state.getName()); pst.executeUpdate();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * public boolean updateState(Country country) { boolean rowUpdated = false; try
	 * (Connection connection = getConnection(); PreparedStatement preparedStatement
	 * = connection.prepareStatement(UPDATE_STATE_SQL)) {
	 * preparedStatement.setString(1, country.getName());
	 * 
	 * rowUpdated = preparedStatement.executeUpdate() > 0;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return rowUpdated; }
	 */
	
	public Country selectStateById(int id) {
		Country country = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATE_BY_ID)) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int state_id = rs.getInt("state_id");
				String state_name = rs.getString("name");
				country = new Country(state_id, state_name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return country;
	}
	
	public List<State> selectStateListByCountryId(int country_id) {
		List<State> listStates = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATE_BY_COUNTRY_ID)) {
			preparedStatement.setInt(1, country_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int state_id = rs.getInt("state_id");
				int cntry_id = rs.getInt("country_id");
				String state_name = rs.getString("name");
				listStates.add(new State(state_id, state_name, cntry_id));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listStates;
	}
	
	public Map<Integer, String> getAllListOfStatesMap() {
		System.out.println();
		List<State> states = new ArrayList<>();
		Map<Integer, String> statesMap = new HashMap<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STATES)) {

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("state_id");
				String stateName = rs.getString("name");
				int cntryId = rs.getInt("country_id");
				states.add(new State(id, stateName, cntryId));
			}

		} catch (SQLException e) {
			printSQLException(e);
		}
		if (!states.isEmpty()) {
			for(State state : states) {
				statesMap.put(state.getId(), state.getName());
			}
		}
		return statesMap;
	}
	
	public List<State> selectAllState() {
		List<State> statesList = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STATES)) {

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int state_id = rs.getInt("state_id");
				int cntry_id = rs.getInt("country_id");
				String stateName = rs.getString("name");
				statesList.add(new State(state_id, stateName, cntry_id));
			}

		} catch (SQLException e) {
			printSQLException(e);
		}
		return statesList;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}

