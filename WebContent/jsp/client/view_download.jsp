<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- @author = 1312040 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Woove your music !</title>
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="styles/css/header.css">
    <link rel="stylesheet" type="text/css" href="styles/css/downloadPage.css">
    <link rel="stylesheet" type="text/css" href="styles/css/footer2.css">
    
    <%-- check for mobile --%>
<link
	media="handheld, only screen and (max-width: 480px), only screen and (max-device-width: 480px)"
	href="styles/css/profilePageMobile.css" type="text/css" rel="stylesheet" />
  </head>
  
  <body>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
	<%@ include file="header.jsp"%>
  
		  <div id="content">
			  

			  <div id="container">
			  

				 	<h2>Profile</h2>
				 	
				   
				    <p>Welcome <c:out value="${Client.firstName}" /> <c:out value="${Client.lastName}"/> </p>
				    
				    <table>
				    	<c:forEach var="dl" items="${Downloads}">
				    	<tr>
				    	
				    		<!-- change to right image -->
							<td class="sample"> <a href="<c:url value='view_Track?track=${dl.inventory_number}'></c:url>"><img id="trackImage" src="images/albumCover/${dl.cover_img_name}" width="55" height="55"/></a> </td>
							<!-- change value to actual item name -->
							<td class="album"> <c:out value="${dl.title}"/> </td>
							<!-- change value to actual quatinty -->
							<td class="track"> <c:out value="${dl.artist}"/> </td>
							<!-- change price-->
							<!--<td class="price"><c:out value="${dl.list_price}"/> </td> -->
							<td class="date"> <c:out value="${dl.date_entered}"/></td>
							<td><a href="view_download?downloadFile=filter.mp3"><img class="download" src="images/download.png" width="30"/></a></td>
				    	
				    	</tr>
				    	</c:forEach>
				    
				    </table>

  
			  </div>
			  
		  
		  </div>
		  
	  </div>
	  
	<%@ include file="footer.jsp"%>	
		   
  </body>
  
  </html>