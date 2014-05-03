<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 
@author = 1312040
@co-author= 0737019
 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Woove your music !</title>
  <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
  <link rel="stylesheet" type="text/css" href="styles/css/searchResults.css">
  <link rel="stylesheet" type="text/css" href="styles/css/header.css">
  <link rel="stylesheet" type="text/css" href="styles/css/footer2.css">

  <script src="js/jquery-1.9.1.min.js"></script>
  <script
	src="http://gsgd.co.uk/sandbox/jquery/easing/jquery.easing.1.3.js"></script>
  <script src="js/scrollingcarousel.2.0.min.js"></script>
  <script src="js/jquery.dataTables.min.js"></script>
  <script src="js/myscripts/search_results.js"></script>
  
</head>

<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<%@ include file="header.jsp"%>
	
	<form id="clickedARowT" action="view_Track" method="GET">
		<input id="hiddenFieldT" name="track" type="hidden" ></input>
	</form>
	
	<form id="clickedARowA" action="view_Albums" method="GET">
		<input id="hiddenFieldA" name="album" type="hidden" ></input>
	</form>

		<div id="content">
			
		<div id="genre">
			<h2>GENRES</h2>
				<div id="genre-carousel1" class="genre-carousel">
					<ul>
						<li><a href="<c:url value='viewGenres?genre=jazz'></c:url>"><img src="images/genreImg/jazz.jpg" height="125px"></a></li>
						<li><a href="<c:url value='viewGenres?genre=dubstep'></c:url>"><img src="images/genreImg/dubstep.jpg"height="125px"></a></li>
						<li><a href="<c:url value='viewGenres?genre=electro'></c:url>"><img src="images/genreImg/electro.jpg" height="125px"></a></li>
						<li><a href="<c:url value='viewGenres?genre=hip-hop'></c:url>"><img src="images/genreImg/hiphop.jpg" height="125px"></a></li>
						<li><a href="<c:url value='viewGenres?genre=rap'></c:url>"><img src="images/genreImg/rap.jpg" height="125px"></a></li>
						<li><a href="<c:url value='viewGenres?genre=reggae'></c:url>"><img src="images/genreImg/reggae.jpg" height="125px"></a></li>
						<li><a href="<c:url value='viewGenres?genre=rock'></c:url>"><img src="images/genreImg/rock.jpg" height="125px"></a></li>
						<li><a href="<c:url value='viewGenres?genre=pop'></c:url>"><img src="images/genreImg/pop.jpg" height="125px"></a></li>
					</ul>
				</div>
		</div>
		
			<div id="genreDisplay">


				<h2>Search Results</h2>
				
				<div id="toggleTable">
					<div id="showTracks" style="cursor:pointer">Tracks</div>
					<div id="showAlbums" style="cursor:pointer">Albums</div>
				</div>
					
					<div id="tracksContainer" style="margin-right:5px; margin-left:10px;">
						<table id="tableLoop" border="0" cellpadding="0" cellspacing="0" class="tableStyle">
							<thead>
							<tr>
								<th style="display:none;"></th>
								<th id="title">Title</th>
								<th id="note">Note</th>
								<th id="artist">Artist</th>
								<th id="album">Album</th>
								<th id="n°">n°</th>
								<th id="length">Length</th>
								<th id="tdGenre">Genre</th>
								<th id="price">Price</th>
							</tr>
							</thead>
							<tbody>
							
							<c:forEach items="${tracks}"  var="track"> 
	       					<tr> 
	       						<td style="display:none;"><c:out value="${track.inventory_number}"/></td>
								<td id="title"><c:out value="${track.title}"/></td>
								<td id="note" title="<c:out value="${track.rating}"/>">
								<c:choose>
									<c:when test="${track.rating>0}">
										<c:forEach var="i" begin="1" end="${track.rating/1}" step="1">
										  <img class="star" src="images/starFull2.png" alt="Star" />
										</c:forEach>
										<c:if test="${track.rating<5}">
											<c:forEach var="j" begin="${track.rating+1}" end="5" step="1">
										      <img class="star" src="images/starEmpty2.png" alt="Star" />
										    </c:forEach>
										</c:if>
									</c:when>
									<c:otherwise>
										<c:forEach var="k" begin="1" end="5" step="1">
										<img class="star" src="images/starEmpty2.png" alt="Star" />
										</c:forEach>
									</c:otherwise>
								</c:choose>
								</td>
								<td id="artist"><c:out value="${track.artist}"/></td>
								<td id="album"><c:out value="${track.album}"/></td>
								<td id="n°"><c:out value="${track.track_number}"/></td>
								<td id="length"><c:out value="${track.track_length}"/></td>
								<td id="tdGenre"><c:out value="${track.category}"/></td>
								<td id="price"><c:out value="${track.list_price}"/></td>
							</tr>
							</c:forEach>
	    					</tbody>
					</table>
				</div>
				
				<div id="albumsContainer" style="margin-right:5px; margin-left:10px;">
						<table id="tableLoopA" border="0" cellpadding="0" cellspacing="0" class="tableStyle">
							<thead>
							<tr>
								<th style="display:none;"></th>
								<th id="title">Title</th>
								<th id="note">Note</th>
								<th id="artist">Artist</th>
								<th id="n°">n°</th>
								<th id="label">Label</th>
								<th id="tdGenre">Genre</th>
								<th id="price">Price</th>
							</tr>
							</thead>
							<tbody>
	
							<c:forEach items="${albums}" var="album" varStatus="status" > 
	       					<tr>
	       						<td style="display:none;"><c:out value="${album.albumID}"/></td>
								<td id="title"><c:out value="${album.title}"/></td>
								<td id="note" title="<c:out value="${album.rating}"/>">
								<c:choose>
									<c:when test="${album.rating>0}">
										<c:forEach var="i" begin="1" end="${album.rating/1}" step="1">
										  <img class="star" src="images/starFull2.png" alt="Star" />
										</c:forEach>
										<c:if test="${album.rating<5}">
											<c:forEach var="j" begin="${album.rating+1}" end="5" step="1">
										      <img class="star" src="images/starEmpty2.png" alt="Star" />
										    </c:forEach>
										</c:if>
									</c:when>
									<c:otherwise>
										<c:forEach var="k" begin="1" end="5" step="1">
										<img class="star" src="images/starEmpty2.png" alt="Star" />
										</c:forEach>
									</c:otherwise>
								</c:choose>
								</td>
								<td id="artist"><c:out value="${album.artist}"/></td>
								<td id="n°"><c:out value="${album.numOfTracks}"/></td>
								<td id="label"><c:out value="${album.label}"/></td>
								<td id="tdGenre"><c:out value="${album.genre}"/></td>
								<td id="price"><c:out value="${album.listPrice}"/></td>
							</tr>
							</c:forEach>
	    					</tbody>
					</table>
				</div>
					
			</div>
		
		</div>
		
	</div>
		
	<%@ include file="footer.jsp"%>	
		
</body>

</html>