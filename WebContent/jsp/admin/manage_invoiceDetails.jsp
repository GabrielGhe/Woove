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
  <link rel="stylesheet" type="text/css" href="styles/css/manager_invoiceDetails.css">
  <link rel="stylesheet" type="text/css" href="styles/css/headerM.css">
  <link rel="stylesheet" type="text/css" href="styles/css/footer2.css">
  
 	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="js/jquery.dataTables.min.js"></script>
	<script src="js/gMiddleDiv.min.js"></script>
	<script src="js/myscripts/manage_invoice_details.js"></script>
</head>

<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
	<%@ include file="headerM.jsp"%>
	
		<form id="invoiceForm" action="<c:url value="/manage_invoiceDetails?invoice=${invoice.sale_number}"></c:url>" method="POST">
			<input id="removeForm" type="hidden" name="remove" >
		</form>
		
		
	<input id="hiddenSaleNum" type="hidden" value="<c:out value="${invoice.sale_number}"/>" >
	
		<div id="content">
		
			<p id="C_ID">Client ID : ${invoice.client_number}</p>
			
			<p>Invoice ID : <c:out value="${invoice.sale_number}"/></p>
			
				<div id="container">
				
					<table>

						<tr>

							<th class="header">Invoice details ID</th>
							<th class="header">Track/Album ID</th>
							<th class="header" class="album">Item</th>
							<th class="header" class="price">Price</th>
							<th class="none" class="remove"></th>
						
						</tr>
						
						<!-- LOOP HERE -->

						<c:forEach items="${invoiceDetails}"  var="invoiceD"> 
							<tr>
								<td class="sample"><c:out value="${invoiceD.id}"/></td>
								<td class="sample">
									<c:choose>
					  					<c:when test="${invoiceD.album_number !=0}">
										  	<c:out value="${invoiceD.album_number}"/>
										</c:when>
										<c:when test="${invoiceD.inventory_number!= 0}">
										  	<c:out value="${invoiceD.inventory_number}"/>
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>	
								</td>
								<!-- change value to actual item name -->
								<td class="album">
									<c:choose>
					  					<c:when test="${invoiceD.album_number > 0}">
										  	Album
										</c:when>
										<c:when test="${invoiceD.inventory_number > 0}">
										  	Track
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>								
								</td>
								<!-- change price-->
								<td class="price"><fmt:formatNumber value="${invoiceD.price_at_sale}" type="currency" currencySymbol="$" currencyCode="USD" pattern="$###,##0.00" /></td>
								<td id="<c:out value="${invoiceD.id}"/>" class="remove" style="cursor:pointer"><img id="remove" class="removeImage" src="images/trash.png" /></td>
							</tr>
						</c:forEach>

						</table>
						
				
				</div>
		
		</div>
	
	</div>

	<%@ include file="../client/footer.jsp"%>
	
</body>
</html>