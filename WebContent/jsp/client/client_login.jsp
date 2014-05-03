<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- author Kimberly Noel -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Woove your music !</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=2.0, user-scalable=yes" />
	
<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
<link rel="stylesheet" type="text/css" href="styles/css/header.css">
<link rel="stylesheet" type="text/css" href="styles/css/loginPage.css">
<link rel="stylesheet" type="text/css" href="styles/css/footer2.css">

<%-- check for mobile --%>
<link
media="handheld, only screen and (max-width: 480px), only screen and (max-device-width: 480px)"
href="styles/css/mobileLoginPage.css" type="text/css" rel="stylesheet" />
</head>

<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="header.jsp"%>

		<div id="content">


			<div id="container">

				<div id="signin">

					<h2>Log in</h2>
					<p>
					<form class="signin"
						action="client_login" method="post">
						<label>Login: </label> <br> <input type="text"
							id="loginname" name="loginname" size=35px /><br /> <label>Password: </label> <br>
						<input type="password" id="password" name="password" size=35px /> <br> 
						<input type="image" src="images/Login.png" id="loginImg" value="Login" />
					</form>
				</div>

				<img id="separator" src="images/top.png" />

				<div id="register">
					<form class="signin" method="POST" action="client_registration">
						<h2>Register</h2>

						<p>Not yet a member? Want to listen to your favorite songs?</p>
						<p>Join us now! Register for free.</p>
						<br> <input type="image" src="images/register.png" id="registerImg" value="Register" />
					</form>
				</div>

			</div>

		</div>

	</div>
	
<%@ include file="footer.jsp"%>	

</body>

</html>