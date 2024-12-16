<%
if (session.getAttribute("name") == null) {
	response.sendRedirect("login.jsp");
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Infosys Employee portal</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
<!-- Font Awesome icons (free version)-->
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/index-styles.css" rel="stylesheet"/>
</head>
<body id="page-top">
	<!-- Navigation-->
	<nav
		class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="#page-top">Employee Login Page</a>
			<button class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded"
				type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="#portfolio">Portfolio</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="#about">About</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="#contact">Contact</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="logout">Logout</a></li>
					<li class="nav-item mx-0 mx-lg-1 bg-danger"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="Logout"><%=session.getAttribute("name")%></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Masthead-->
	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> User Management App </a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
		<div class="container">
			<hr>
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/registration.jsp" class="btn btn-success">Add New User</a>
					<h3 class="text-center">List of Users</h3>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Select</th>
						<th>Employee ID</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Contact</th>
						<th>Joining Date</th>
						<th>Designation</th>
						<th>Country</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="user" items="${listUser}">
						<tr>
							<th><input type="checkbox" id="checkbox1" name="checkbox1" value="select_checkbox"></th>
							<td><a href="showUser?id=<c:out value='${user.emp_id}' />&cntryid=<c:out value='${user.countryId}' />&stateid=<c:out value='${user.stateId}' />&cityid=<c:out value='${user.cityId}' />"><c:out value="${user.emp_id}" /></a></td>
							<td><c:out value="${user.first_name}" /></td>
							<td><c:out value="${user.last_name}" /></td>
							<td><c:out value="${user.user_email}" /></td>
							<td><c:out value="${user.user_mobile}" /></td>
							<td><c:out value="${user.joining_date}" /></td>
							<td><c:out value="${user.designation}" /></td>
							<td><c:out value="${user.country}" /></td>
							<td><a href="edit?id=<c:out value='${user.emp_id}' />&cntryid=<c:out value='${user.countryId}' />&stateid=<c:out value='${user.stateId}' />&cityid=<c:out value='${user.cityId}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${user.emp_id}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
					<tr>
						<th colspan="10"><%
						request.getAttribute("total");
						String totalStr = String.valueOf(request.getAttribute("total"));
						float total = Integer.parseInt(totalStr);
						
						request.getAttribute("start");
						String startStr = String.valueOf(request.getAttribute("start"));
						float start = Integer.parseInt(startStr);
						
						request.getAttribute("recordCount");
						String recCountStr = String.valueOf(request.getAttribute("recordCount"));
						float recordCount = Integer.parseInt(recCountStr);
						
						request.getAttribute("start");
						request.getAttribute("recordCount");
						for (int i = 0; i <= total/recordCount ; i++) {
							out.print("<a href='list?pgno="+i+"'>Page"+ (i+1) +" </a>");
						
						}
						%></th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- Bootstrap core JS-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	
	<!-- <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script> -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"></script>
	<!-- <script type="text/javascript">
		var currentPage= 1;
		var pageSize = 10;
		
		$(document).ready(function(){
			$("#searchButton").click(function(){
		fetchEmployeeData(currentPage);
			});
			
			$("#prevPage").click(function(){
				if(currentPage > 1){
					currentPage--;
		fetchEmployeeData(currentPage);
				}
			});
			
			$("#nextPage").click(function()
{
				currentPage++;
				
		fetchEmployeeData(currentPage);
			});
		});
		
		function fetchEmployeeData(page){
			$.get("Employee?page=" + page + "&pageSize=" + pageSize, function(data){
				$("#employeeTable").html(data);
			});
		}
	</script> -->
</body>
</html>