<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 
@author = 1312040
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Woove your music !</title>
<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
<link rel="stylesheet" type="text/css" href="styles/css/manager_Front.css">
<link rel="stylesheet" type="text/css" href="styles/css/headerM.css">
<link rel="stylesheet" type="text/css" href="styles/css/footer2.css">
</head>
<body>
	
	<%@ include file="headerM.jsp" %>

		<div id="content">

			<h1>Welcome to the backoffice</h1>

			<h3>What do you want to do ?</h3>

			<div id="manage">

				<a href="<c:url value='manage_clients'></c:url>">
				<img id="client" src="images/clientManagement.png" />
				<p id="CM">Client Management</p></a>
				
				<a href="<c:url value='manage_albumsTracks'></c:url>">
				<img id="trackAlbum" src="images/albumManagement.png" />
				<p id="ATM">Album & Track <br /> Management</p></a>
					
				<a href="<c:url value='manage_adsNewsSurvey'></c:url>">
				<img id="adManage" src="images/adManagement.png" />
				<p id="ANSM">Ads, News & <br /> Survey Management</p></a>
					
				<a id="invoice" href="<c:url value='manage_invoice'></c:url>">
				<img src="images/orderManagement.png" />
				<p>Orders Management</p></a>
				
				<a id="report" href="<c:url value='manage_reports'></c:url>">
				<img src="images/reportManagement.png" />
				<p id="report2">Reports</p></a>
				
				<a id="sales" href="<c:url value='manage_Sales'></c:url>">
				<img src="images/salesManagement.png" /></a>

			</div>

		</div>

	</div>

	<%@ include file="../client/footer.jsp"%>
</body>
</html>