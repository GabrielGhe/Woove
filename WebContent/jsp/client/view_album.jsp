<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 
@author = 1312040
@co-author= 0737019
 -->
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	  <title>Woove your music !</title>
	  <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
	  <link rel="stylesheet" type="text/css" href="styles/css/header.css">
	  <link rel="stylesheet" type="text/css" href="styles/css/albumPage.css">
	  <link rel="stylesheet" type="text/css" href="styles/css/footer2.css">
	</head>
	
	<body>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
		<%@ include file="header.jsp"%>
	
			<div id="content">
			  
			<c:if test="${recentThree.size() > 0}">
			<div id="recentlyViewed">
				<p>Recent</p>
				<c:forEach items="${recentThree}"  var="recent"> 
					<a title="<c:out value="${recent.title}"/>" href="view_Track?track=<c:url value='${recent.inventory_number}'></c:url>">
					<img src="images/albumCover/<c:out value="${recent.cover_img_name}"/>"
						alt="images/albumSample.png"/></a>
				</c:forEach>
			</div>
			</c:if>
			  
			  <div id="albumimg">
			      <img src="images/albumCover/<c:out value="${tracksForAlbum[0].cover_img_name}"/>" width="200px" height="200px"/>
			  </div>
			  
			<div id="title">
					<h2> <c:out value="${albumToView.title}"/> </h2>
					<h3> <c:out value="${albumToView.artist}"/> </h3>
			</div>
						
			
				<div id="trackcontainer">
						<form>
						  <table>
						  	<c:if test = "${tracksForAlbum.size() > 0}">
							<c:forEach items="${tracksForAlbum}"  var="track" varStatus="status"> 
						    <tr>
						      <!-- change # count -->
						      <td> <c:out value="${status.count}"/> </td>
						      <!-- change value to actual item name -->
						      <td id="getTitle"><a href="<c:url value="view_Track?track=${track.inventory_number}"></c:url>" style="color:black"><c:out value="${track.title}"/></a></td>
						      <!-- change value to actual quatinty -->
						      <td><c:out value="${track.track_length}"/></td>
						      <!-- change price-->
						      <td><fmt:formatNumber value="${track.sale_price != 0?track.sale_price:track.list_price}" type="currency" currencySymbol="$" currencyCode="USD" pattern="$###,##0.00" /></td>
						      <td>  <a href="<c:url value="view_Track?track=${track.inventory_number}&purchaseTrack=yes"></c:url>">
						      <img src="images/cart.png"  width="20px" height="20px">
						      </a> </td>
						    </tr>
						    </c:forEach>
						    </c:if>
						  </table>
						</form>
						
						
						<div id="albumcontainer">
					  <h3><fmt:formatNumber value="${album.salePrice != 0?album.salePrice:album.listPrice}" type="currency" currencySymbol="$" currencyCode="USD" pattern="$###,##0.00" /></h3>
					  <form action="<c:url value="view_Albums?album=${albumToView.albumID}&purchaseAlbum=yes"></c:url>" method="post">
					  <input type="submit" class="myButton" value="Buy Album" />
					  </form>
						</div>
												
				</div>
				
				 <div id="similar">
				   
				  <h3>You may also like : </h3>
					  
					  <c:forEach items="${similarAlbums}"  var="similar" end="2"> 
					  <div class="lastThree">
					  <a href="view_Albums?album=<c:url value='${similar.albumID}'></c:url>">
						  <img class="albumSample" src="images/albumCover/${similar.imgName}" width="55px" height="55px" />
						  </a>
						  <p><c:out value="${similar.title}"/></p>
						  <p><c:out value="${similar.artist}"/><p>
						  <p><fmt:formatNumber value="${similar.salePrice != 0?similar.salePrice:similar.listPrice}" type="currency" currencySymbol="$" currencyCode="USD" pattern="$###,##0.00" /></p>
					  </div>
					  </c:forEach>
						  
				  
				  </div>
				
				<div id="starcontainer" title="${avgRating}">
				  <c:choose>
					<c:when test="${avgRating>0}">
						<c:forEach var="i" begin="1" end="${avgRating/1}" step="1">
						  <img class="star2" src="images/starFull2.png" alt="Star" />
						</c:forEach>
						<c:if test="${halfStar>0}">
						  <img class="star2" src="images/starHalf2.png" alt="Star" />
						</c:if>
						<c:if test="${halfStar <= 0 }">
							<c:forEach var="j" begin="${avgRating/1+1}" end="5" step="1">
						      <img class="star2" src="images/starEmpty2.png" alt="Star" />
						    </c:forEach>
						</c:if>
						<c:if test="${halfStar > 0 && avgRating<4.5}">
							<c:forEach var="j" begin="${avgRating/1+2}" end="5" step="1">
						      <img class="star2" src="images/starEmpty2.png" alt="Star" />
						    </c:forEach>
						</c:if>
					</c:when>
					<c:otherwise>
						<c:forEach var="k" begin="1" end="5" step="1">
						<img class="star2" src="images/starEmpty2.png" alt="Star" />
						</c:forEach>
					</c:otherwise>
				</c:choose>
				</div>
  
			  <div id="reviews_Container">
			  
			  	<h2>Reviews for this Album</h2>

				  <c:choose>
  					<c:when test="${reviews.size() > 0}">
					  <div class ="reviews">
					   	<c:forEach items="${reviews}" var="reviewed" varStatus="status">
					      <div class="reviewAll">
						      <strong><label for="male"><c:out value="${reviewed.name_of_client}"/></strong> - 
						      	<c:set var="i" value="0"/>
						      	<c:forEach items="${tracksForAlbum}" var="trackR" varStatus="status">
									<c:if test="${(i==0 && trackR.inventory_number == reviewed.inventory_number)}">
									<a href="view_Track?track<c:url value='${trackR.inventory_number}'></c:url>"><i><c:out value="${trackR.title}"/></i></a>
									<c:set var="i" value="1"/>
									</c:if>
								</c:forEach></label>
						      <!-- images must be put it backwards since it is aligned right -->
						      <div id="reviewRating">
								<c:forEach var="i" begin="1" end="${reviewed.rating/1}" step="1">
									<img class="star2" src="images/starFull2.png" alt="Star" />
								</c:forEach>
								<c:if test="${reviewed.rating<5}">
									<c:forEach var="j" begin="${reviewed.rating/1+1}" end="5" step="1">
									 	<img class="star2" src="images/starEmpty2.png" alt="Star" />
									</c:forEach>
								</c:if>
						      </div>
							  <p><c:out value="${reviewed.review}"/></p>
						  	</div>
						</c:forEach>
					  </div>
					  </c:when>
					  <c:otherwise>
					  		<p style="text-align:center">There are no Reviews for this album.</p>
					  </c:otherwise>
				</c:choose>
			  
			  </div>
			    
		</div>

  
	</div>
		
	<%@ include file="footer.jsp"%>
			
	</body>
	
	</html>