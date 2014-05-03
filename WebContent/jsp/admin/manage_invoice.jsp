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
  <link rel="stylesheet" type="text/css" href="styles/css/manager_Invoice.css">
  <link rel="stylesheet" type="text/css" href="styles/css/headerM.css">
  <link rel="stylesheet" type="text/css" href="styles/css/footer2.css">
  
 	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="js/jquery.dataTables.min.js"></script>
	<script src="js/gMiddleDiv.min.js"></script>
	<script src="js/myscripts/manage_invoice.js"></script>
</head>

<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<%@ include file="headerM.jsp"%>
	
		<div id="content">
		
			<h1>Invoice Management</h1>
			
			
			<div id="displayTrack">
					
					<table id="tableLoop" border="0" cellpadding="0" cellspacing="0" class="tableStyle">
							<thead>
							<tr>					
								<th class="ID">ID</th>
								<th class="date">Date</th>
								<th class="U_ID">U_ID</th>
								<th class="totalnet">Total Net</th>
								<th class="PST">PST</th>
								<th class="GST">GST</th>
								<th class="HST">HST</th>							
								<th class="total">Total</th>
							</tr>
							</thead>
							<tbody>

							<c:forEach items="${invoices}"  var="invoice"> 
								<tr onclick="location.href='manage_invoiceDetails?invoice=<c:url value="${invoice.sale_number}"></c:url>'" >	
									<td class="ID"><c:out value="${invoice.sale_number}"/></td>
									<td class="date"><c:out value="${invoice.date}"/></td>
									<td class="U_ID"><c:out value="${invoice.client_number}"/>5</td>
									<td class="totalnet"><fmt:formatNumber value="${invoice.total_net_value}" type="currency" currencySymbol="$" currencyCode="USD" pattern="$###,##0.00" /></td>
									<td class="PST"><fmt:formatNumber value="${invoice.pst}" type="currency" currencySymbol="$" currencyCode="USD" pattern="$###,##0.00" /></td>
									<td class="GST"><fmt:formatNumber value="${invoice.gst}" type="currency" currencySymbol="$" currencyCode="USD" pattern="$###,##0.00" /></td>
									<td class="HST"><fmt:formatNumber value="${invoice.hst}" type="currency" currencySymbol="$" currencyCode="USD" pattern="$###,##0.00" /></td>							
									<td class="total"><fmt:formatNumber value="${invoice.total_gross_value}" type="currency" currencySymbol="$" currencyCode="USD" pattern="$###,##0.00" /></td>
								</tr>
							</c:forEach>
	    					</tbody>
					</table>					
			</div>
		
		</div>
		
	</div>

	<%@ include file="../client/footer.jsp"%>	

</body>