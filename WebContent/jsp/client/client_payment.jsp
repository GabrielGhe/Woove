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
<link rel="stylesheet" type="text/css" href="styles/css/paymentPage.css">
<link rel="stylesheet" type="text/css" href="styles/css/footer2.css">

<%--- check for mobile ---%>
<link
	media="handheld, only screen and (max-width: 480px), only screen and (max-device-width: 480px)"
	href="styles/css/paymentPageMobile.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="js/myscripts/paymentJavascript.js">
	</script>
<script src="js/jquery-1.9.1.min.js"></script>

</head>

<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="header.jsp"%>

	<div id="content">

		<div id="title">
			<img src="images/paymentTitle.png" />
		</div>


		<div id="container">


			<form name="myForm" class="right_aligned"
				action="<c:url value='client_payment'></c:url>" method="post">
				<label>Card Type: </label> <select name="card">
					<option></option>
					<option value="Visa" name="visa">Visa</option>
					<option value="Mastercard" name="mastercard">MasterCard</option>
				</select><br /> <label>Card Number: </label><input type="text"
					name="cardnum" placeholder="4111111111111111" maxlength="16"
					size="14" /><br /> <label>Card Name: </label><input type="text"
					name="cardname" placeholder="YOUR NAME" size="30" /><br /> <label>Card
					CSCr: </label><input type="text" name="securitycode" maxlength="4" size="4"
					onkeypress="numValidate(event)" /><br /> <label>Expr: </label>
				<div style="float: left; width: 10%">
					<select name="month">
						<option value="01" id="month">01</option>
						<option value="02" id="month">02</option>
						<option value="03" id="month">03</option>
						<option value="04" id="month">04</option>
						<option value="05" id="month">05</option>
						<option value="06" id="month">06</option>
						<option value="07" id="month">07</option>
						<option value="08" id="month">08</option>
						<option value="09" id="month">09</option>
						<option value="10" id="month">10</option>
						<option value="11" id="month">11</option>
						<option value="12" id="month">12</option>
					</select>
				</div>
				<div style="float: left; width: 10%">
					<select name="year">
						<option value="2013" name="year">2013</option>
						<option value="2014" name="year">2014</option>
						<option value="2015" name="year">2015</option>
						<option value="2016" name="year">2016</option>
						<option value="2017" name="year">2017</option>
						<option value="2018" name="year">2018</option>
						<option value="2019" name="year">2019</option>
						<option value="2020" name="year">2020</option>
						<option value="2021" name="year">2021</option>
					</select>
				</div>
				<br /> <input id="submit" type="image" src="images/Submit.png">
			</form>
			<br>
		</div>


	</div>

	</div>

	<%@ include file="footer.jsp"%>

</body>

</html>