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
	  <link rel="stylesheet" type="text/css" href="styles/css/trackPage.css">
	  <link rel="stylesheet" type="text/css" href="styles/css/footer2.css">
	  
	   <%-- check for mobile --%>

	  <script src="js/jquery.js"></script>
	  <script src="js/jquery.rating.pack.js"></script>
	  <script src="js/jquery.form.js"></script>
	  <script src="js/jquery.MetaData.js"></script>
	  <script src="js/jquery.rating.pack.js"></script>
	  <script>
	  	$(function() {
	  		$("#star1").click(function(){
	  			$("#rating").attr("value", "1");
	  		});
	  		
	  		$("#star2").click(function(){
	  			$("#rating").attr("value", "2");
	  		});
	  		
	  		$("#star3").click(function(){
	  			$("#rating").attr("value", "3");
	  		});
	  		
	  		$("#star4").click(function(){
	  			$("#rating").attr("value", "4");
	  		});
	  		
	  		$("#star5").click(function(){
	  			$("#rating").attr("value", "5");
	  		});
	  		
	  		$("#review-su").click(function(){
	  			if($("#review-re").val() != "" && $("#rating").val() != "0"){
	  				$("#comment").submit();
	  			}
	  		});
	  		
	  		
	  	});
	  </script>

	</head>
	
	<body>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
		<%@ page import="beans.TrackBean"%>
		<%@ page import="java.util.ArrayList"%>
		<%@ page import="beans.Cart"%>
		<%
			TrackBean track = (TrackBean)request.getAttribute("track");
			ArrayList<TrackBean> similar = (ArrayList<TrackBean>)request.getAttribute("similarTracks");
			Cart cart = (Cart)request.getSession().getAttribute("Cart");
		%>
		
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
			    <a href="view_Albums?album=<c:url value='${album.albumID}'></c:url>">
			    	<img src="images/albumCover/<c:out value="${track.cover_img_name}"/>" width="200px" height="200px"/>
			    </a>
			  </div>
			  
			<div id="title">
			  	<h2> <c:out value="${track.artist}"/> </h2>
			  	<!-- change value to actual item name -->
			  	<h3> <c:out value="${track.title}"/> </h3>
			</div>
			
				<div id="trackcontainer">					      
						      <!-- change value to actual quatinty -->
						      <p><strong><c:out value="${track.track_length}"/></strong></p>
						      <!-- change price-->
						      <p id="price"><strong><fmt:formatNumber value="${track.sale_price != 0?track.sale_price:track.list_price}" type="currency" currencySymbol="$" currencyCode="USD" pattern="$###,##0.00" /></strong>
						      <a href="<c:url value="view_Track?track=${track.inventory_number}&purchaseTrack=yes"></c:url>">
						      <img src="images/cart.png"  width="20px" height="20px">
						      </a></p></br>
					<div id="test">
						<table>
						  	<c:if test = "${tracksForAlbum.size() > 1}">
						  		<p>Other songs in the album:</p>
								<c:forEach items="${tracksForAlbum}"  var="tracks" varStatus="status"> 
									<c:if test = "${tracks.inventory_number != track.inventory_number}">
								    <tr>
								    	<td id="getTitle">
								    		<a href="<c:url value="view_Track?track=${tracks.inventory_number}"></c:url>" style="color:black"><c:out value="${tracks.title}"/></a>
								     	</td>
								    </tr>
								    </c:if>
							    </c:forEach>
						    </c:if>
						  </table>
					</div>
					
				</div>
				
				<div id="starcontainer" title="${rating}">
				<c:choose>
					<c:when test="${rating>0}">
						<c:forEach var="i" begin="1" end="${rating/1}" step="1">
						  <img class="star2" src="images/starFull2.png" alt="Star" />
						</c:forEach>
						<c:if test="${halfStar>0}">
						  <img class="star2" src="images/starHalf2.png" alt="Star" />
						</c:if>
						<c:if test="${halfStar <= 0 }">
							<c:forEach var="j" begin="${rating/1+1}" end="5" step="1">
						      <img class="star2" src="images/starEmpty2.png" alt="Star" />
						    </c:forEach>
						</c:if>
						<c:if test="${halfStar > 0 && rating<4.5}">
							<c:forEach var="j" begin="${rating/1+2}" end="5" step="1">
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
  
  
  
			  <div id="similar">
  
			  <h3>You may also like: </h3>
				  
				  	<c:forEach items="${similarTracks}"  var="similar" begin="0" end="2" step="1"> 
				  	<div class="lastThree">
				  	<a href="view_Track?track=<c:url value='${similar.inventory_number}'></c:url>">
					  <img class="albumSample" src="images/albumCover/${similar.cover_img_name}"width="55px" height="55px" />
					  </a>
					  <p><c:out value="${similar.title}" /></p>
					  <p><fmt:formatNumber value="${similar.sale_price != 0?similar.sale_price:similar.list_price}" type="currency" currencySymbol="$" currencyCode="USD" pattern="$###,##0.00" /><p>
					  <p><c:out value="${similar.artist}" /></p>
				    </div>
					</c:forEach>
				 
			  
			  </div>
			  
			  
			  <div id="reviews_Container">
			  		<c:choose>
  					<c:when test="${sessionScope.Client != null || reviews.size() > 0}">
					  <div class ="reviews">
					  	<form id="comment" action="<c:url value="client_Submit_Review"></c:url>" method="post">
					  	  <c:choose>
						  	  <c:when test = "${sessionScope.Client != null}">
							  	  <label><c:out value="${sessionScope.Client.firstName}"/> <c:out value="${sessionScope.Client.lastName}"/></label></br>
							      <!-- images must be put it backwards since it is aligned right -->
							       <input type="hidden" name="track" value=<c:out value="${track.inventory_number}"/>>
								   <textarea id="review-re" name="review"></textarea></br>
								  
								  
								  <div class="Clear">
										<input id="star1" class="star required" type="radio" name="star1" value="1"/>
										<input id="star2" class="star" type="radio" name="star1" value="2"/>
										<input id="star3" class="star" type="radio" name="star1" value="3"/>
										<input id="star4" class="star" type="radio" name="star1" value="4"/>
										<input id="star5" class="star" type="radio" name="star1" value="5"/>
								  </div>
								  
								  <input id="rating" name="rating" type="hidden" value="0" />
								   </br>
							       <input id="review-su" type="button" value="Comment">
							       
						      </c:when>
						      <c:when test="${sessionScope.Client == null}">
						      	<textarea id="fakeReview-re" name="review" disabled="disabled" placeholder="You need to log in to be able to comment."></textarea></br>
						      </c:when>
						  </c:choose>
					      </form>
					    <br/>
					   
					   <c:forEach items="${reviews}"  var="reviewed" varStatus="status"> 
					      <div class="reviewAll">
						      <strong><label for="male"><c:out value="${reviewed.name_of_client}"/></label></strong>
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
							  <p id="commentBody"><c:out value="${reviewed.review}"/></p>
						  </div>
						</c:forEach>
					  </div>
					  </c:when>
					  <c:otherwise>
					  		<p style="text-align:center">There are no Reviews for this track.</p>
					  </c:otherwise>
				</c:choose>
  				</div>
  			
  			
			</div>	
			
	</div>
			
	<%@ include file="footer.jsp"%>
	</body>
	
	</html>