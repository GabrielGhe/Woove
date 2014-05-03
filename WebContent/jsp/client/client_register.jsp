
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- author Kimberly Noel
@co-author = 1312040
 -->
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
<link rel="stylesheet" type="text/css" href="styles/css/registerPage.css">
<link rel="stylesheet" type="text/css" href="styles/css/footer2.css">

<link
media="handheld, only screen and (max-width: 480px), only screen and (max-device-width: 480px)"
href="styles/css/regPageMobile.css" type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="js/myscripts/registrationJavascript.js">
	
</script>

</head>

<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="header.jsp"%>

	<div id="content">

		<div id="regTitle">
			<img src="images/registrationTitle.png" />
		</div>

		<div id="container">

			<form class="right_aligned" action="client_registration"
				method="POST" id="registrationForm">
				<div id="left">
				<label>First Name*: </label><input required="required" type="text"
					name="firstName" maxlength="25" id="firstname"
					value="${client.firstName}" onkeypress="nameValidate(event)" /><br />
				<label>Last Name*: </label><input required="required" type="text"
					value="${client.lastName}" id="lastname" name="lastName"
					maxlength="25" onkeypress="nameValidate(event)" /><br /> <label>Title*:
				</label><select id="title" name="title">
					<option value="Mrs"
						<c:if test="${client.title =='Mrs'} "> selected </c:if>>
						Mrs</option>
					<option value="Mr"
						<c:if test="${client.title == 'Mr' }"> selected </c:if>>
						Mr</option>
				</select><br /> <label>E-mail*: </label><input required="required"
					type="text" id="email" name="email" maxlength="128"
					onchange="emailValidate(this)" value="${client.email}" /><br /> <label>Confirm
					E-mail*: </label><input required="required" type="text" id="emailTwo"
					name="email2" value="${email2}" onchange="emailValidate(this)" /><br />
				<label>Password*: </label> <input required="required" type="password"
					id="passwordOne" name="passwordOne" maxlength="25" /><br /> <label>Confirm
					Password*: </label> <input required="required" type="password"
					id="passwordTwo" name="passwordTwo" maxlength="25" /><br />
					</div>
					<div id="right">
					 <label>Company:
				</label><input type="text" id="companyname" name="companyname"
					value="${client.companyName}" maxlength="50" /><br /> <label>Country*:
				</label> <select>
					<option value="CA">CA</option>
				</select><br /> <label>Address 1*: </label><input required="required"
					type="text" name="addressOne" id="addressOne"
					value="${client.address1}" maxlength="100" /><br /> <label>Address
					2: </label><input type="text" id="addressTwo" name="addressTwo"
					value="${client.address2}" maxlength="100"
					onkeypress="nameValidate(event)" /><br /> <label>City*: </label> <input
					required="required" type="text" id="city" name="city"
					value="${client.city}" onkeypress="nameValidate(event)" /><br />

				<label>Province*: </label><select id="province" name="province" required="required">
					<option value="AB"
						<c:if test="${client.province =='AB'} "> selected </c:if>>
						AB</option>
					<option value="BC"
						<c:if test="${client.province == 'BC' }"> selected </c:if>>
						BC</option>
					<option value="MB"
						<c:if test="${client.province == 'MB' }"> selected </c:if>>
						MB</option>
					<option value="NB"
						<c:if test="${client.province == 'NB' }"> selected </c:if>>
						NB</option>
					<option value="NL"
						<c:if test="${client.province == 'NL' }"> selected </c:if>>
						NL</option>
					<option value="NT"
						<c:if test="${client.province == 'NT' }"> selected </c:if>>
						NT</option>
					<option value="NS"
						<c:if test="${client.province == 'NS' }"> selected </c:if>>
						NS</option>
					<option value="NU"
						<c:if test="${client.province == 'NU' }"> selected </c:if>>
						NU</option>
					<option value="ON"
						<c:if test="${client.province == 'ON' }"> selected </c:if>>
						ON</option>
					<option value="PEI"
						<c:if test="${client.province == 'PEI' }"> selected </c:if>>
						PEI</option>
					<option value="QC"
						<c:if test="${client.province == 'QC' }"> selected </c:if>>
						QC</option>
					<option value="SK"
						<c:if test="${client.province == 'SK' }"> selected </c:if>>
						SK</option>
					<option value="YT"
						<c:if test="${client.province == 'YT' }"> selected </c:if>>
						YT</option>
				</select><br /> <label>Postal Code*: </label><input required="required"
					type="text" id="postalcode" maxlength="6" name="postalcode"
					value="${client.postalCode}"
					onchange="postalCodeValidate(this.value)" /><br /> <label>Home
					#*: </label><input type="text" id="homephone" name="homephone" required="required"
					value="${client.homePhone}"
					onkeydown="javascript:backspacerDOWN(this,event);"
					onkeyup="javascript:backspacerUP(this,event);" /> <br /> <label>Cell
					#*: </label><input type="text" id="cellphone" name="cellphone" required="required"
					value="${client.cellPhone}"
					onkeydown="javascript:backspacerDOWN(this,event);"
					onkeyup="javascript:backspacerUP(this,event);" /> <br />
					</div>
				<div id="buttondiv">
					<input id="submit" type="image" src="images/register.png" value="Register">
				</div>
			</form>

		<p id="mandatory">Fields marked with a * are mandatory</p>
		</div>



	</div>

	</div>

	<%@ include file="footer.jsp"%>


</body>

</html>