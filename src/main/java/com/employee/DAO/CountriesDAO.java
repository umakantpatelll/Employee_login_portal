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
import com.employee.model.User;

public class CountriesDAO {
	
	private String jdbcURl = "jdbc:mysql://localhost:3306/login_schema?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUserName = "root";
	private String jdbcPassword = "rootroot";
	
	private static final String INSERT_COUNTRY_SQL = "insert into countries(name) values(?) ";
	
	private static final String SELECT_COUNTRY_BY_ID = "SELECT country_id, name FROM countries where counrty_id=?";
	
	private static final String UPDATE_COUNTRY_SQL = "UPDATE countries set name=?;";
	
	private static final String SELECT_ALL_COUNTRIES = "SELECT * from countries";

	

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
	 * public void insertCountry(Country country) { try (Connection connection =
	 * getConnection(); PreparedStatement pst =
	 * connection.prepareStatement(INSERT_COUNTRY_SQL)) { pst.setString(1,
	 * country.getName()); pst.executeUpdate();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * public boolean updateCountry(Country country) { boolean rowUpdated = false;
	 * try (Connection connection = getConnection(); PreparedStatement
	 * preparedStatement = connection.prepareStatement(UPDATE_COUNTRY_SQL)) {
	 * preparedStatement.setString(1, country.getName());
	 * 
	 * rowUpdated = preparedStatement.executeUpdate() > 0;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return rowUpdated; }
	 */
	
	public Country selectCountryById(int id) {
		Country country = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNTRY_BY_ID)) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int country_id = rs.getInt("country_id");
				String cntry_name = rs.getString("name");
				country = new Country(country_id, cntry_name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return country;
	}
	
	public List<Country> getAllListOfCountries() {
		List<Country> countries = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COUNTRIES)) {

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Country country = new Country();
				country.setId( rs.getInt("country_id"));
				country.setName( rs.getString("name"));
				countries.add(country);
			}

		} catch (SQLException e) {
			printSQLException(e);
		}
		return countries;
	}
	
	public Map<Integer, String> getAllListOfCountriesMap() {
		List<Country> countries = new ArrayList<>();
		Map<Integer, String> countriesMap = new HashMap<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COUNTRIES)) {

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("country_id");
				String countryName = rs.getString("name");
				countries.add(new Country(id, countryName));
			}

		} catch (SQLException e) {
			printSQLException(e);
		}
		if (!countries.isEmpty()) {
			for(Country country : countries) {
				countriesMap.put(country.getId(), country.getName());
			}
		}
		return countriesMap;
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

