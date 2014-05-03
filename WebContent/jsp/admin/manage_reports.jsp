<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 
@author = 1312040
 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Woove your music !</title>
  <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
  <link rel="stylesheet" type="text/css" href="styles/css/manager_Report.css">
  <link rel="stylesheet" type="text/css" href="styles/css/headerM.css">
  <link rel="stylesheet" type="text/css" href="styles/css/footer2.css">
</head>

<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ include file="headerM.jsp"%>
	
		<div id="content">
		
			<h1>Welcome to the Report page</h1>
			
			<h3>What do you want to do ?</h3>
			
			<form id="choose_Report" method="get">
			
			<label>Type of report :</label>
				<select id="selection" name="selection">
					<option>Total Sales</option>
					<option>Sales by Client</option>
					<option>Sales by Artist</option>
					<option>Sales by Track</option>
					<option>Sales by Album</option>
					<option>Top Sellers</option>
					<option>Top Clients</option>
					<option>Zero Tracks</option>
					<option>Zero Clients</option>
				</select>
				
			<br/>
			<label>Value to search :</label><input id="startDate" name="search" type="text" /><br/><br/>
			<label>Range of date :</label><br/>
			<p>From : <input id="startDate" name="startDate" type="date" /> to : <input id="endDate" name="endDate" type="date" /></p>
			
			
			<input type="image" class="submit" value="submit" src="images/applySmall.png" />
			
			</form>
			
			<div id="displayResults">
				<c:choose>
					<c:when test="${topSellers!=null }">
					<div id="tracksContainer" style="margin-right:5px; margin-left:10px;">
						<table id="tableLoop" border="0" cellpadding="0" cellspacing="0" class="tableStyle">
							<thead>
								<tr>
									<th style="display:none;"></th>
									<th id="id">ID</th>
									<th id="title">Title</th>
									<th id="artist">Artist</th>
									<th id="album">Price</th>
									<th id="price">Total Sales</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${topSellers}"  var="track"> 
			       					<tr> 
										<td id="id"><c:out value="${track[0]}"/></td>
										<td id="title"><c:out value="${track[1]}"/></td>
										<td id="artist"><c:out value="${track[2]}"/></td>
										<td id="album"><c:out value="${track[3]}"/></td>
										<td id="price"><c:out value="${track[4]}"/></td>
									</tr>
								</c:forEach>
	    					</tbody>
							</table>
						</div>
					</c:when>
					<c:when test="${topClients!=null }">
					<div id="tracksContainer" style="margin-right:5px; margin-left:10px;">
						<table id="tableLoop" border="0" cellpadding="0" cellspacing="0" class="tableStyle">
							<thead>
								<tr>
									<th style="display:none;"></th>
									<th id="id">ID</th>
									<th id="name">Name</th>
									<th id="totalPurchases">Total Purchases</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${topClients}"  var="client"> 
			       					<tr> 
										<td id="id"><c:out value="${client[0]}"/></td>
										<td id="name"><c:out value="${client[1]} ${client[2]}"/></td>
										<td id="totalPurchases"><c:out value="${client[3]}"/></td>
									</tr>
								</c:forEach>
	    					</tbody>
							</table>
						</div>
					</c:when>
					<c:when test="${zeroClients!=null }">
					<div id="tracksContainer" style="margin-right:5px; margin-left:10px;">
						<table id="tableLoop" border="0" cellpadding="0" cellspacing="0" class="tableStyle">
							<thead>
								<tr>
									<th style="display:none;"></th>
									<th id="id">ID</th>
									<th id="name">Name</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${zeroClients}"  var="client"> 
			       					<tr> 
										<td id="id"><c:out value="${client.clientID}"/></td>
										<td id="name"><c:out value="${client.firstName} ${client.lastName}"/></td>
									</tr>
								</c:forEach>
	    					</tbody>
							</table>
						</div>
					</c:when>
					<c:when test="${zeroTracks!=null }">
					<div id="tracksContainer" style="margin-right:5px; margin-left:10px;">
						<table id="tableLoop" border="0" cellpadding="0" cellspacing="0" class="tableStyle">
							<thead>
								<tr>
									<th style="display:none;"></th>
									<th id="id">ID</th>
									<th id="title">Title</th>
									<th id="artist">Artist</th>
									<th id="price">Price</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${zeroTracks}"  var="track"> 
			       					<tr> 
										<td id="id"><c:out value="${track.inventory_number}"/></td>
										<td id="title"><c:out value="${track.title}"/></td>
										<td id="artist"><c:out value="${track.artist}"/></td>
										<td id="price"><c:out value="${track.price}"/></td>
									</tr>
								</c:forEach>
	    					</tbody>
							</table>
						</div>
					</c:when>
					<c:when test="${byClient!=null}">
						Total purchases for client: <c:out value="${byClient[0]}"/>$
					</c:when>
					<c:when test="${byArtist!=null}">
						Total sales for artist: <c:out value="${byArtist[0]}"/>$
					</c:when>
					<c:when test="${byTrack!=null}">
						Total purchases for track: <c:out value="${byTrack[0]}"/>$
					</c:when>
					<c:when test="${byAlbum!=null}">
						Total purchases for album: <c:out value="${byAlbum[0]}"/>$
					</c:when>
					<c:when test="${totalSales!=null}">
						Total sales: <c:out value="${totalSales[0]}"/>$
					</c:when>
					<c:otherwise>
				    		
					</c:otherwise>
				</c:choose>
			</div>

		
		</div>
		
	</div>
	
	<%@ include file="../client/footer.jsp"%>
</body>