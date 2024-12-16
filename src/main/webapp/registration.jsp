<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign Up Form</title>
<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
	integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
	crossorigin="anonymous"></script>
<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<input type="hidden" id="status"
		value="<%=request.getAttribute("status")%>">

	<div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Sign up</h2>
						<c:if test="${user != null}">
							<form method="post" action="update" class="register-form"
								id="register-form">
						</c:if>
						<c:if test="${user == null}">
							<form method="post" action="register" class="register-form"
								id="register-form">
						</c:if>
						<caption>
							<h2>
								<c:if test="${user != null}">
            			            Edit User
            				        </c:if>
								<c:if test="${user == null}">
            					    Add New User
            				        </c:if>
							</h2>
						</caption>
						<c:if test="${user != null}">
							<input type="hidden" name="id"
								value="<c:out value='${user.emp_id}' />" />
						</c:if>
						<div class="form-group">
							<label for="name"><i
								class="zmdi zmdi-account material-icons-name"></i></label> <input
								value="<c:out value='${user.first_name}' />" type="text"
								name="name" id="name" placeholder="First Name" required/>
						</div>
						<div class="form-group">
							<label for="name"><i
								class="zmdi zmdi-account material-icons-name"></i></label> <input
								value="<c:out value='${user.last_name}' />" type="text"
								name="lastname" id="lastname" placeholder="Last Name" required/>
						</div>
						<div class="form-group">
							<label for="name"><i
								class="zmdi zmdi-account material-icons-name"></i></label> <input
								value="<c:out value='${user.designation}' />" type="text"
								name="designation" id="designation" placeholder="Designation" required/>
						</div>
						<div class="form-group">
							<label for="name"><i
								class="zmdi zmdi-account material-icons-name"></i></label> <input
								value="<c:out value='${user.joining_date}' />" type="date"
								name="dateOfJoining" id="dateOfJoining"
								placeholder="Date of joining" required/>
						</div>

						<div class="form-group">
							<label for="name"><i
								class="zmdi zmdi-account material-icons-name"></i></label> <input
								value="<c:out value='${user.country}' />" type="text"
								name="country" id="country" placeholder="Country" required/>
						</div>

						<div class="form-group">
							<table>
								<tr>
									<td><b>Select Country :</b></td>
									<td><select id="cntry" name="cntry" required>
											<!-- <option value="">Select Country</option> -->
											<c:if test="${not empty user.countryId}">
												<option value="${user.countryId}" selected="selected">${user.selectedCountry}</option>
											</c:if>
									</select></td>
								</tr>
								<tr>
									<td><b>Select State :</b></td>
									<td><select id="state" name="state" required>
											<option value="">Select State</option>
											<c:if test="${not empty user.stateId}">
												<option value="${user.stateId}" selected="selected">${user.selectedState}</option>
											</c:if>
									</select></td>
								</tr>
								<tr>
									<td><b>Select City :</b></td>
									<td><select id="city" name="city" required>
											<option value="">Select City</option>
											<c:if test="${not empty user.cityId}">
												<option value="${user.cityId}" selected="selected">${user.selectedCity}</option>
											</c:if>
									</select></td>
								</tr>
							</table>
							<%-- <table>
						<tr>
							<td><b>Select Country :</b></td>
							<td><select id="cntry", name="cntry" >
									<option value="${item.key}" selected="selected">${item.value}>-Select Country-</option>
									<option value="<c:out value='${user.countryId}' />">Select Country</option>
							</select></td>
						</tr>
						<tr>
							<td><b>Select State :</b></td>
							<td><select id="state", name="state">	
									<option value="<c:out value='${user.stateId}' />">Select State</option>
							</select></td>
						</tr>
						<tr>
							<td><b>Select City :</b></td>
							<td><select id="city", name="city">
									<option value="<c:out value='${user.cityId}' />">Select City</option>
							</select></td>
						</tr>
					</table> --%>
						</div>
						<div class="form-group">
							<label for="email"><i class="zmdi zmdi-email"></i></label> <input
								value="<c:out value='${user.user_email}' />" type="email"
								name="email" id="email" placeholder="Your Email" required/>
						</div>
						<div class="form-group">
							<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
								value="<c:out value='${user.password}' />" type="password"
								name="pass" id="pass" placeholder="Password" required/>
						</div>
						<!-- <div class="form-group">
							    <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
							    <input type="password" name="re_pass" id="re_pass"
								    placeholder="Repeat your password" />
						    </div> -->
						<div class="form-group">
							<label for="contact"><i class="zmdi zmdi-lock-outline"></i></label>
							<input type="text" name="contact" id="contact"
								value="<c:out value='${user.user_mobile}' />"
								placeholder="Contact no" required/>
						</div>
						<div class="form-group">
							<input type="checkbox" name="agree-term" id="agree-term"
								class="agree-term" /> <label for="agree-term"
								class="label-agree-term"><span><span></span></span>I
								agree all statements in <a href="#" class="term-service">Terms
									of service</a></label>
						</div>
						<c:if test="${user == null}">
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
							</div>
						</c:if>
						<c:if test="${user != null}">
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Update" />
							</div>
						</c:if>
						</form>
						<c:if test="${user != null}">
							<form method="post" action="list" class="register-form"
								id="register-form">
								<div class="form-group form-button">
									<input type="submit" name="signup" id="signup"
										class="form-submit" value="Cancel" />
								</div>
							</form>
						</c:if>
					</div>


				</div>
				<div class="signup-image">
					<figure>
						<img src="images/signup-image.jpg" alt="sing up image">
					</figure>
					<a href="login.jsp" class="signup-image-link">I am already
						member</a>
				</div>
			</div>
		</section>



	</div>
	<!-- JS -->
	<!-- <script type="text/javascript" src="js/app.js"></script> -->
	<!-- <script type="text/javascript"  src="js/app.js"></script> -->
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
		integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
		crossorigin="anonymous"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script
		src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>

<script>
  $(document).ready(function() {
    $("#register-form").validate({
      rules: {
        name: {
          required: true
        },
        lastname: {
          required: true
        },
        email: {
          required: true,
          email: true
        },
        password: {
          required: true
        },
        country: {
          required: true
        },
        dateOfJoining: {
          required: true
        },
        cntry: {
            required: true
        },
        state: {
            required: true
        },
        city: {
            required: true
        },
        contact: {
            required: true
        },
        pass: {
            required: true
        },
        designation: {
            required: true
        }
      }
    });
  });
</script>

	<script>
		$(document).ready(
				function() {
					$("#cntry").val("${requestScope.selectedDepartment}").attr(
							'selected', 'selected');
				});
	</script>

	<script type="text/javascript">
		var status = document.getElementById("status").value;
		console.log(status);
		if (status == "success") {
			swal("congrats", "Account created successfully", "success")
		}
	</script>



<!-- 	<script type="text/javascript">
		function selectElement(id, valueToSelect) {
			let element = document.getElementById(id);
			element.value = valueToSelect;
			console.log("");
		}

		selectElement('cntry', '1');
	</script> -->



	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$
									.ajax({
										url : "CountryServlet",
										method : "GET",
										data : {
											operation : "country",
										},
										success : function(data, textStatus,
												jqXHR) {
											console.log(data);
											let obj = $.parseJSON(data);
											$.each(obj, function(key, value) {
												$("#cntry").append(
														'<option value="' + value.id + '">'+ value.name+ "</option>");
												console.log(value.name);
												console.log(value.id);
											});
											$("select").formSelect();
										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											$("#cntry")
													.append(
															"<option>Country Unavailable</option>");
										},
										cache : false,
									});
							$("#cntry")
									.change(
											function() {
												$("#state").find("option")
														.remove();
												$("#state")
														.append(
																"<option>Select State</option>");
												$("#city").find("option")
														.remove();
												$("#city")
														.append(
																"<option>Select City</option>");

												let country_id = $("#cntry")
														.val();
												let data = {
													operation : "state",
													id : country_id
												};

												$
														.ajax({
															url : "CountryServlet",
															method : "GET",
															data : data,
															success : function(
																	data,
																	textStatus,
																	jqXHR) {
																console
																		.log(data);
																let obj = $
																		.parseJSON(data);
																$
																		.each(
																				obj,
																				function(
																						key,
																						value) {
																					$(
																							"#state")
																							.append(
																									'<option value="' + value.id + '">'
																											+ value.name
																											+ "</option>");
																				});
																$("select")
																		.formSelect();
															},
															error : function(
																	jqXHR,
																	textStatus,
																	errorThrown) {
																$("#state")
																		.append(
																				"<option>State Unavailable</option>");
															},
															cache : false,
														});
											});

							$("#state")
									.change(
											function() {
												$("#city").find("option")
														.remove();
												$("#city")
														.append(
																"<option>Select City</option>");

												let state_id = $("#state")
														.val();
												let data = {
													operation : "city",
													id : state_id,
												};

												$
														.ajax({
															url : "CountryServlet",
															method : "GET",
															data : data,
															success : function(
																	data,
																	textStatus,
																	jqXHR) {
																console
																		.log(data);
																let obj = $
																		.parseJSON(data);
																$
																		.each(
																				obj,
																				function(
																						key,
																						value) {
																					$(
																							"#city")
																							.append(
																									'<option value="' + value.id + '">'
																											+ value.name
																											+ "</option>");
																				});
																$("select")
																		.formSelect();
															},
															error : function(
																	jqXHR,
																	textStatus,
																	errorThrown) {
																$("#city")
																		.append(
																				"<option>City Unavailable</option>");
															},
															cache : false,
														});
											});
						});
	</script>









	<!-- <script type="text/javascript">
	getCountry();
	
	function getCountry() {
		$.ajax({
			url : 'CountryServlet',
			cache : false,
			dataType : 'JSON',
			success : function(result){
				$.each(result, function(key, value){
					$('<option>').val(key).text(value).appendTo("#cntry");
				});
			}
		});
		}
	
	$(document).on("change", "#cntry", function(){
		$("#state").find('option').remove();
		$('<option>').val("").text("-Select State-").appendTo("#state");
		$("#city").find('option').remove();
		$('<option>').val("").text("-Select City-").appendTo("#city");
		var selectedCntryId = $("#cntry").val();
		$.ajax({
			url : "StateServlet",
			data : {
				countryId : selectedCntryId
			},
			dataType : 'JSON',
			success : function(result){
				$.each(result, function(key, value){
				$('<option>').val(key).text(value).appendTo('#state');	
				});
			}
		});
	});

	$(document).on("change", "#state", function(){
		$("#city").find('option').remove();
		$('<option>').val("").text("-Select State-").appendTo("#city");
		var selectedStateId = $("#state").val();
		$.ajax({
			url : "CityServlet",
			data : {
				stateId : selectedStateId
			},
			dataType : 'JSON',
			success : function(result){
				$.each(result, function(key, value){
				$('<option>').val(key).text(value).appendTo('#city');	
				});
			}
		});
	});
	</script> -->


</body>
</html>
