package com.employee.model;

public class User {
	private int emp_id ;
	private String first_name;
	private String password;
	private String last_name;
	private String user_email;
	private String user_mobile;
	private String joining_date;
	private String country;
	private String designation;
	private String countryId;
	private String stateId;
	private String cityId;
	private String selectedCountry;
	private String selectedState;
	private String selectedCity;

	
	public User(int emp_id, String first_name, String password, String last_name, String user_email, String user_mobile,
			String joining_date, String country, String designation, String countryId, String stateId, String cityId,
			String selectedCountry, String selectedState, String selectedCity) {
		super();
		this.emp_id = emp_id;
		this.first_name = first_name;
		this.password = password;
		this.last_name = last_name;
		this.user_email = user_email;
		this.user_mobile = user_mobile;
		this.joining_date = joining_date;
		this.country = country;
		this.designation = designation;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
		this.selectedCountry = selectedCountry;
		this.selectedState = selectedState;
		this.selectedCity = selectedCity;
	}



	public User(String first_name, String password, String last_name, String user_email, String user_mobile,
			String joining_date, String country, String designation, String countryId, String stateId, String cityId) {
		super();
		this.first_name = first_name;
		this.password = password;
		this.last_name = last_name;
		this.user_email = user_email;
		this.user_mobile = user_mobile;
		this.joining_date = joining_date;
		this.country = country;
		this.designation = designation;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
	}
	
	
	
	public User(int emp_id, String first_name, String password, String last_name, String user_email, String user_mobile,
			String joining_date, String country, String designation, String countryId, String stateId, String cityId) {
		super();
		this.emp_id = emp_id;
		this.first_name = first_name;
		this.password = password;
		this.last_name = last_name;
		this.user_email = user_email;
		this.user_mobile = user_mobile;
		this.joining_date = joining_date;
		this.country = country;
		this.designation = designation;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
	}


	public String getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(String selectedCountry) {
		this.selectedCountry = selectedCountry;
	}

	public String getSelectedState() {
		return selectedState;
	}

	public void setSelectedState(String selectedState) {
		this.selectedState = selectedState;
	}

	public String getSelectedCity() {
		return selectedCity;
	}

	public void setSelectedCity(String selectedCity) {
		this.selectedCity = selectedCity;
	}

	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_mobile() {
		return user_mobile;
	}
	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}
	public String getJoining_date() {
		return joining_date;
	}
	public void setJoining_date(String joining_date) {
		this.joining_date = joining_date;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
}

