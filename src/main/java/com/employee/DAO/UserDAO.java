package com.employee.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.employee.model.User;

public class UserDAO {
	private String jdbcURl = "jdbc:mysql://localhost:3306/login_schema?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUserName = "root";
	private String jdbcPassword = "rootroot";

	private static final String INSERT_USERS_SQL = "insert into login_users(user_name,password,user_email,user_mobile,last_name,designation,joining_date,country) values(?,?,?,?,?,?,?,?) ";

	private static final String SELECT_USER_BY_ID = "select * FROM login_users where emp_id=?";

	private static final String SELECT_ALL_USERS = "SELECT * from login_users limit ?,?";

	private static final String DELETE_USERS_SQL = "delete from login_users where emp_id =?;";

	private static final String UPDATE_USERS_SQL = "update login_users set user_name=?,password=?,user_email=?,user_mobile=?,last_name=?,designation=?,joining_date=?,country=?,country_id=?,state_id=?,city_id=? where emp_id=?;";

	private static final String SELECT_COUNTRY_BY_ID = "SELECT * FROM countries where country_id=?";
	
	private static final String SELECT_STATE_BY_ID = "SELECT * FROM state where state_id=?";
	
	private static final String SELECT_CITY_BY_ID = "SELECT * FROM cities where city_id=?";
	
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

	public void insertUser(User user) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			System.out.println("inside insert user from userDAO");
			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setString(2, user.getUser_email());
			preparedStatement.setString(3, user.getUser_mobile());
			preparedStatement.setString(4, user.getLast_name());
			preparedStatement.setString(5, user.getDesignation());
			preparedStatement.setString(6, user.getJoining_date());
			preparedStatement.setString(7, user.getCountry());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean updateUser(User user) {
		boolean rowUpdated = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
			System.out.println("========================Inside update user from userDAO");
			preparedStatement.setString(1, user.getFirst_name());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getUser_email());
			preparedStatement.setString(4, user.getUser_mobile());
			preparedStatement.setString(5, user.getLast_name());
			preparedStatement.setString(6, user.getDesignation());
			preparedStatement.setString(7, user.getJoining_date());
			preparedStatement.setString(8, user.getCountry());
			preparedStatement.setString(9, user.getCountryId());
			preparedStatement.setString(10, user.getStateId());
			preparedStatement.setString(11, user.getCityId());
			preparedStatement.setInt(12, user.getEmp_id());

			rowUpdated = preparedStatement.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}

	public User selectUserById(int emp_id, int cntry_id, int state_id, int city_id) {
		User user = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
			preparedStatement.setInt(1, emp_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			PreparedStatement preparedStatement2 = connection.prepareStatement(SELECT_COUNTRY_BY_ID);
			preparedStatement2.setInt(1, cntry_id);
			ResultSet rs2 = preparedStatement2.executeQuery();
			String country_name = null;
			String statee_name = null;
			String cityy_name = null;
			while(rs2.next()) {
				country_name = rs2.getString("name");
				System.out.println("country name "+country_name);
			}
			PreparedStatement preparedStatement3 = connection.prepareStatement(SELECT_STATE_BY_ID);
			preparedStatement3.setInt(1, state_id);
			ResultSet rs3 = preparedStatement3.executeQuery();
			while(rs3.next()) {
				statee_name = rs3.getString("name");
				System.out.println("state name "+statee_name);
			}
			PreparedStatement preparedStatement4 = connection.prepareStatement(SELECT_CITY_BY_ID);
			preparedStatement4.setInt(1, city_id);
			ResultSet rs4 = preparedStatement4.executeQuery();
			while(rs4.next()) {
				cityy_name = rs4.getString("name");
				System.out.println("city name "+cityy_name);
			}
			
			while (rs.next()) {
				int empid = rs.getInt("emp_id");
				String firstName = rs.getString("user_name");
				String lastName = rs.getString("last_name");
				String password = rs.getString("password");
				String email = rs.getString("user_email");
				String contact = rs.getString("user_mobile");
				String designation = rs.getString("designation");
				String joiningDate = rs.getString("joining_date");
				String country = rs.getString("country");
				System.out.println("country from edit user select user by id "+country);
				String countryId = rs.getString("country_id");
				String stateId = rs.getString("state_id");
				String cityId = rs.getString("city_id");
				
				user = new User(empid, firstName, password, lastName, email, contact, joiningDate, country, 
						designation, countryId, stateId, cityId, country_name, statee_name, cityy_name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
		
	public List<User> selectAllUsers(HttpServletRequest request) {
		List<User> users = new ArrayList<>();
		int total =0;
		int start =0, recordCount = 5;
		int pgno = request.getParameter("pgno")==null?0:Integer.parseInt(request.getParameter("pgno"));
		System.out.println(pgno);
		start = pgno*recordCount;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
				preparedStatement.setInt(1, start);
				preparedStatement.setInt(2, recordCount);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("emp_id");
				String firstName = rs.getString("user_name");
				String lastName = rs.getString("last_name");
				String password = rs.getString("password");
				String email = rs.getString("user_email");
				String contact = rs.getString("user_mobile");
				String designation = rs.getString("designation");
				String joiningDate = rs.getString("joining_date");
				String country = rs.getString("country");
				String countryId = rs.getString("country_id");
				String stateId = rs.getString("state_id");
				String cityId = rs.getString("city_id");
				users.add(new User(id, firstName, password, lastName, email, contact, joiningDate, country, designation, countryId, stateId, cityId));
				request.setAttribute("start", start);
				request.setAttribute("recordCount", recordCount);
				total = totalNoOfRecord(request, total);
				request.setAttribute("total", total);
			}

		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}
	
	private int totalNoOfRecord(HttpServletRequest request, int total) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from login_users")) {
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return total;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
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
