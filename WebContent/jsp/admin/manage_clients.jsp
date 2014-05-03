<!-- 
@author = 1312040
@co-author= 0737019
 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Woove your music !</title>
  <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
  <link rel="stylesheet" type="text/css" href="styles/css/manager_Client.css">
  <link rel="stylesheet" type="text/css" href="styles/css/headerM.css">
  <link rel="stylesheet" type="text/css" href="styles/css/footer2.css">
  
    <script src="js/jquery-1.9.1.min.js"></script>
  	<script src="js/jquery.dataTables.min.js"></script>
  	<script src="js/gMiddleDiv.min.js"></script>
  	<script src="js/myscripts/manage_clients.js"></script>
  	
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<div id="gMiddleDivClient">
		<form id="clientForm" action="<c:url value='/manage_clientscrud'></c:url>" method="GET">
			<input id="fid" type="hidden" name="fid" >
  			<div>Title <input id="ftitle" type="text" name="ftitle"><br></div>
  			<div>Last Name <input id="fln" type="text" name="fln"><br></div>
			<div>First Name <input id="ffn" type="text" name="ffn"><br></div>
			<div>Company Name <input id="fcn" type="text" name="fcn"><br></div>
			<div>Address 1 <input id="fadd" type="text" name="fadd"><br></div>
			<div>Address 2 <input id="fadd2" type="text" name="fadd2"><br></div>
			<div>City <input id="fcity" type="text" name="fcity"><br></div>
			<div>Province <input id="fprov" type="text" name="fprov"><br></div>
			<div>Country <input id="fcountry" type="text" name="fcountry"><br></div>
			<div>Postal Code <input id="fpc" type="text" name="fpc"><br></div>
			<div>Home Phone <input id="fhp" type="text" name="fhp"><br></div>
			<div>Cell Phone <input id="fcp" type="text" name="fcp"><br></div>
			<div>Password <input id="fpwd" type="text" name="fpwd"><br></div>
			<div>Email <input id="femail" type="text" name="femail"><br></div>
			<div>Total Sales <input id="fsales" type="text" name="fsales"><br></div>
			<button id="Submit" type="button" style="cursor:pointer">Save</button>
			<button id="New" type="button" style="cursor:pointer">New</button>
			<button id="Delete" type="button" style="cursor:pointer">Delete</button>
			<button id="middleDivCancel" type="button" style="cursor:pointer">Cancel</button>
			
		</form>
		
		<form>
  			<input id="hftitle" type="hidden" name="hftitle">
  			<input id="hfln" type="hidden" name="hfln">
			<input id="hffn" type="hidden" name="hffn">
			<input id="hfcn" type="hidden" name="hfcn">
			<input id="hfadd" type="hidden" name="hfadd">
			<input id="hfadd2" type="hidden" name="hfadd2">
			<input id="hfcity" type="hidden" name="hfcity">
			<input id="hfprov" type="hidden" name="hfprov">
			<input id="hfcountry" type="hidden" name="hfcountry">
			<input id="hfpc" type="hidden" name="hfpc">
			<input id="hfhp" type="hidden" name="hfhp">
			<input id="hfcp" type="hidden" name="hfcp">
			<input id="hfpwd" type="hidden" name="hfpwd">
			<input id="hfemail" type="hidden" name="hfemail">
			<input id="hfsales" type="hidden" name="hfsales">
		</form>
	</div>

	<%@ include file="headerM.jsp"%>
	
		<div id="content">
		
			<h1>Client Management</h1>
			
			<div id="displayTrack">
					
					<table id="tableLoop" border="0" cellpadding="0" cellspacing="0" class="tableStyle">
							<thead>
							<tr>
								<th class="ID">ID</th>
								<th class="title" id="title">Title</th>
								<th class="lastName" id="lastName">Last Name</th>
								<th class="firstName" id="firstName">First Name</th>
								<th class="label" id="label">Company Name</th>
								<th class="address" id="address">Address 1</th>
								<th class="address" id="address">Address 2</th>							
								<th class="city" id="city">City</th>
								<th class="province" id="province">Province</th>
								<th class="country" id="country">Country</th>
								<th class="postal" id="postal">Postal Code</th>
								<th class="phone" id="phone">Home Telephone</th>
								<th class="cell" id="cell">Cell Telephone</th>
								<th class="cell" id="pwd">Password</th>
								<th class="mail" id="mail" >Email</th>
								<th class="T_Sales" id="T_Sales" >Total Sales</th>
							</tr>
							</thead>
							<tbody>
							
					
							<c:forEach items="${clients}"  var="client"> 
	       					<tr>
								
								<td class="ID"><c:out value="${client.clientID}"/></td>
								<td class="title" id="title"><c:out value="${client.title}"/></td>
								<td class="lastName"><c:out value="${client.lastName}"/></td>
								<td class="firstName" id="firstName"><c:out value="${client.firstName}"/></td>
								<td class="label" id="label"><c:out value="${client.companyName}"/></td>
								<td class="address" id="address"><c:out value="${client.address1}"/></td>
								<td class="address" id="address"><c:out value="${client.address2}"/></td>							
								<td class="city" id="city"><c:out value="${client.city}"/></td>
								<td class="province"><c:out value="${client.province}"/></td>
								<td class="country"><c:out value="${client.country}"/></td>
								<td class="postal" id="postal"><c:out value="${client.postalCode}"/></td>
								<td class="phone" id="phone"><c:out value="${client.homePhone}"/></td>
								<td class="cell" id="cell"><c:out value="${client.cellPhone}"/></td>
								<td class="cell" id="pwd"><c:out value="${client.password}"/></td>							
								<td class="mail" id="mail" ><c:out value="${client.email}"/></td>
								<td class="T_Sales" id="T_Sales" ><c:out value="${client.totalSales}"/></td>
								
							</tr>
							</c:forEach>
	    					</tbody>
					</table>				
			</div>
		
		</div>
		
	</div>

	<%@ include file="../client/footer.jsp"%>
	
</body>
</html>