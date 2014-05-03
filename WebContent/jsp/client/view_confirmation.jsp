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
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="styles/css/header.css">
    <link rel="stylesheet" type="text/css" href="styles/css/confirmPage.css">
    <link rel="stylesheet" type="text/css" href="styles/css/footer2.css">
  </head>
  
  <body>
  	<%@ page import="beans.TrackBean"%>
	<%@ page import="beans.AlbumBean"%>
	<%@ page import="beans.Cart"%>
	<%@ page import="java.util.ArrayList"%>
	<%
		ArrayList<TrackBean> tracks = (ArrayList<TrackBean>)request.getAttribute("tracksInCart");
		ArrayList<AlbumBean> albums = (ArrayList<AlbumBean>)request.getAttribute("albumsInCart");
		Cart cart = (Cart)request.getSession().getAttribute("Cart");
	%>
  	  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  	  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  	  
	<%@ include file="header.jsp"%>
  
		 <div id="content">

<dic id="title">
<img id="trackImage" src="images/confirmationTitle.png" />

		  
<div id="paymentcontainer">
<br>Credit Card Number: <c:out value="${requestScope.CardNumber}"/><br/>
<br>Credit Card Holder: <c:out value="${requestScope.CardName}"></c:out><br/>
<br>Expiration Date: <c:out value="${requestScope.ExpiryDate }"></c:out><br/>
<br>CSS Code: ***
</div>
		  
			  
			  <div id="container">
			  
<br>
	  <br>
					    <table>
					      <tr>
  <th class="header"></th>
  <th class="header">Album Name</th>
  <th class="header">Track Name</th>
  <th class="header">Price</th>
  </tr>
  					<c:forEach items="${Cart.tracks}"  var="track"> 
					    <tr>
						<!-- change to right image -->
						<td> <img id="trackImage" src="images/albumCover/<c:out value="${track.cover_img_name}"/>" width="55" height="55"/> </td>
						<!-- change value to actual item name -->
						<td> <c:out value="${track.title}"/> </td>
						<!-- change value to actual quatinty -->
						<td> <c:out value="${track.artist}"/></td>
						<td> <c:out value="${track.list_price}"/></td>
						</tr>
					</c:forEach>
					
					<c:forEach items="${Cart.album}"  var="a"> 
					<tr>
						<!-- change to right image -->
						<td class="colored"> <img id="trackImage" src="images/albumCover/<c:out value="${a.imgName}"/>" width="55" height="55"/> </td>
						<!-- change value to actual item name -->
						<td class="colored"> <c:out value="${a.title}"/> </td>
						<!-- change value to actual quatinty -->
						<td class="colored"> <c:out value="${a.artist}"/></td>
						<td class="colored"> <c:out value="${a.salePrice != 0?a.salePrice:a.listPrice}"/></td>
					</tr>
					</c:forEach>
					</table>
						 
					<strong>Total Price : <fmt:formatNumber value="${Gross}" type="currency" currencySymbol="$" currencyCode="USD" pattern="$###,##0.00" /></strong>
			  
			  </div>

		<div id="buttondiv">
				  
			<a href="<c:url value='view_confirmation'></c:url>"> 
				<img id="contBtn" src="images/Continue.png" /> 
			</a>  
				  
		</div>
		</div>
		  
	  </div>
		 
<%@ include file="footer.jsp"%>	 
		  
  </body>
  
  </html>