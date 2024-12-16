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

import com.employee.model.City;
import com.employee.model.Country;
import com.employee.model.State;

public class CityDAO {
	
	private String jdbcURl = "jdbc:mysql://localhost:3306/login_schema?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUserName = "root";
	private String jdbcPassword = "rootroot";
	
	private static final String INSERT_CITY_SQL = "insert into cities(name, state_id) values(?,?) ";
	
	private static final String SELECT_CITY_BY_ID = "SELECT city_id, name FROM cities where city_id=?";
	
	private static final String SELECT_CITY_BY_STATE_ID = "SELECT city_id, name, state_id FROM cities where state_id=?";
	
	private static final String UPDATE_CITY_SQL = "UPDATE cities set name=?;";
	
	private static final String SELECT_ALL_CITIES = "SELECT * from cities";

	

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
	
	public City selectCityById(int id) {
		City city = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CITY_BY_ID)) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int city_id = rs.getInt("city_id");
				String city_name = rs.getString("name");
				int state_id = rs.getInt("state_id");
				city = new City(city_id, city_name, state_id);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}
	
	public List<City> selectCityListByStateId(int state_id) {
		List<City> cityList = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CITY_BY_STATE_ID)) {
			preparedStatement.setInt(1, state_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int city_id = rs.getInt("city_id");
				int st_id = rs.getInt("state_id");
				String city_name = rs.getString("name");
				cityList.add(new City(city_id, city_name, st_id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityList;
	}
	
	public Map<Integer, String> getAllListOfcitiesMap() {
		List<City> cities = new ArrayList<>();
		Map<Integer, String> cityMap = new HashMap<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CITIES)) {

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("city_id");
				String stateName = rs.getString("name");
				int cntryId = rs.getInt("state_id");
				cities.add(new City(id, stateName, cntryId));
			}

		} catch (SQLException e) {
			printSQLException(e);
		}
		if (!cities.isEmpty()) {
			for(City city : cities) {
				cityMap.put(city.getId(), city.getName());
			}
		}
		return cityMap;
	}
	
	public List<City> selectAllCity() {
		List<City> citiesList = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CITIES)) {

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int city_id = rs.getInt("city_id");
				int state_id = rs.getInt("country_id");
				String cityName = rs.getString("name");
				citiesList.add(new City(city_id, cityName, state_id));
			}

		} catch (SQLException e) {
			printSQLException(e);
		}
		return citiesList;
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

