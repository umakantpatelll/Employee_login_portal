<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.card {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	transition: 0.3s;
	width: 40%;
}

.card:hover {
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}

.container {
	padding: 2px 16px;
}

.button {
	background-color: #4CAF50; /* Green */
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}

.button4 {
	background-color: #e7e7e7;
	color: black;
} /* Gray */
.button5 {
	background-color: #555555;
} /* Black */
</style>
</head>
<body>

	<h2>
		<c:out value="${user.first_name} " />
	</h2>

	<div class="card">
		<img src="images/img_avatar.png" alt="Avatar" style="width: 100%">
		<div class="container">
			<h4>
				<b><c:out value="${user.first_name} " /> <c:out
						value="${user.last_name}" /></b>
			</h4>
			<p>
				Employee ID:
				<c:out value="${user.emp_id}" />
			</p>
			<p>
				Email:
				<c:out value="${user.user_email}" />
			</p>
			<p>
				Contact:
				<c:out value="${user.user_mobile}" />
			</p>
			<p>
				Date of Joining
				<c:out value="${user.joining_date}" />
			</p>
			<p>
				Designation:
				<c:out value="${user.designation}" />
			</p>
			<p>
				Country:
				<c:out value="${user.country}" />
			</p>
			<form method="post" action="edit?id=<c:out value='${user.emp_id}' />&cntryid=<c:out value='${user.countryId}' />&stateid=<c:out value='${user.stateId}' />&cityid=<c:out value='${user.cityId}' />" class="register-form"
				id="register-form">
			<button class="button">update</button>
			</form>
			<form method="post" action="list" class="register-form"
				id="register-form">
			<button class="button button4">cancel</button>
		</form>
		<form method="post" action="list" class="register-form"
				id="register-form">
			<button class="button button4">Back</button>
		</form>
		</div>
	</div>
</body>
</html>