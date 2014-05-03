

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
<link rel="stylesheet" type="text/css" href="styles/css/genre.css">
<link rel="stylesheet" type="text/css" href="styles/css/header.css">
<link rel="stylesheet" type="text/css" href="styles/css/footer2.css">
  <script src="js/jquery-1.9.1.min.js"></script>
  <script
	src="http://gsgd.co.uk/sandbox/jquery/easing/jquery.easing.1.3.js"></script>
  <script src="js/scrollingcarousel.2.0.min.js"></script>
  <script src="js/jquery.dataTables.min.js"></script>
<script>
  var albumT;
  var trackT;
  
	$(function() {		
		//genre carousel
		$('#genre-carousel1').scrollingCarousel({
			autoScroll : true
		});
		
		
		//dataTable plugin for track table
		trackT = $('#tableLoop').dataTable({
			"bJQueryUI": true,
			"sPaginationType": "full_numbers",
			"sDom": '<lf<t>ip>',
			'iDisplayLength': 5
		});
		
		
		/* Add a click handler to the rows - this could be used as a callback */
		$("#tableLoop tbody").click(function(event) {
			$(trackT.fnSettings().aoData).each(function (){
				$(this.nTr).removeClass('row_selected').css('background-color', 'white');
			});
			$(event.target.parentNode).addClass('row_selected').css('background-color', '#cc89b0');
			var anSelected = fnGetSelected( trackT );
			var idSelected = $(anSelected).children().eq(0).text();
			if(!isNaN(idSelected) && idSelected.trim() != ""){
				$('#hiddenFieldT').val(idSelected);
				$('#clickedARowT').submit();
			}
		});
		
		
		//dataTable plugin for album table
		albumT = $('#tableLoopA').dataTable({
			"bJQueryUI": true,
			"sPaginationType": "full_numbers",
			"sDom": '<lf<t>ip>',
			'iDisplayLength': 5
		});
		
		
		/* Add a click handler to the rows - this could be used as a callback */
		$("#tableLoopA tbody").click(function(event) {
			$(albumT.fnSettings().aoData).each(function (){
				$(this.nTr).removeClass('row_selected').css('background-color', 'white');
			});
			$(event.target.parentNode).addClass('row_selected').css('background-color', '#cc89b0');
			var anSelected = fnGetSelected( albumT );
			var idSelected = $(anSelected).children().eq(0).text();
			if(!isNaN(idSelected) && idSelected.trim() != ""){
				$('#hiddenFieldA').val(idSelected);
				$('#clickedARowA').submit();
			}
		});
		
		
		/* Get the rows which are currently selected */
		function fnGetSelected( oTableLocal )
		{
			var aReturn = new Array();
			var aTrs = oTableLocal.fnGetNodes();
			
			for ( var i=0 ; i<aTrs.length ; i++ )
			{
				if ( $(aTrs[i]).hasClass('row_selected') )
				{
					aReturn.push( aTrs[i] );
				}
			}
			return aReturn;
		}
		
		
		//hide the unwanted features
		$('#tableLoop_length').css("display", "none");
		$('#tableLoop_first').css("display", "none");
		$('#tableLoop_last').css("display", "none");
		
		
		//hide the unwanted features
		$('#tableLoopA_length').css("display", "none");
		$('#tableLoopA_first').css("display", "none");
		$('#tableLoopA_last').css("display", "none");
		
		
		//hides one table and displays the other
		var rowCountT = $('#tableLoop tr').length;
		var rowCountA = $('#tableLoopA tr').length;
		if(rowCountT == 0){	
			$('#tracksContainer').hide();
		}else if(rowCountA == 0){
			$('#albumsContainer').hide();
		}else{
			$('#albumsContainer').hide();
		}
		
		$('#showTracks').click(displayTracks);
		$('#showAlbums').click(displayAlbums);
	});
	
	function displayTracks(){
		$('#albumsContainer').hide();
		$('#tracksContainer').show();
		$('#showTracks').css('background-image', 'url(images/categoryPressed.png)');
		$('#showAlbums').css('background-color', 'white');
		$('#showAlbums').css('background-image', 'none');
	}
	
	function displayAlbums(){
		$('#albumsContainer').show();
		$('#tracksContainer').hide();
		$('#showTracks').css('background-color', 'white');
		$('#showTracks').css('background-image', 'none');
		$('#showAlbums').css('background-image', 'url(images/categoryPressed.png)');
	}
  </script>

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

			<div id="top">

				<div id="topTrack">

					<h2>Top 3 tracks</h2>			
					
					<c:forEach items="${topTracksByGenre}"  var="topTrack" end="2"> 
					<div class="topTrackR">
						<a href="view_Track?track=<c:url value='${topTrack.inventory_number}'></c:url>">
						<img src="images/albumCover/<c:out value="${topTrack.imagePath}"/>"
							alt="images/albumSample.png" height="55px" width="55px" /></a>

						<p><c:out value="${topTrack.title}"/></br>
						<c:out value="${topTrack.artist}"/></br>
						<c:out value="${topTrack.album}"/></p>

					</div>
					</c:forEach>

				</div>

				<img id="topSeparation" src="images/top.png" />

				<div id="topAlbum">

					<h2>Top 3 albums</h2>
					
					<c:forEach items="${albumsToDisplay}"  var="topAlbum"> 
					<div class="topAlbumR">
						<a href="view_Albums?album=<c:url value='${topAlbum.albumID}'></c:url>">
						<img src="images/albumCover/<c:out value="${topAlbum.imagePath}"/>"
							alt="images/albumSample.png" height="55px" width="55px" /></a>

						<p><c:out value="${topAlbum.albumTitle}"/>
							</br>
							<c:out value="${topAlbum.artist}"/></p>

					</div>
					</c:forEach>
				</div>

			</div>

			<div id="genreDisplay">
			
				<!-- Would need to call ref. to full track list (going to be brought in to display anyways) to obtain genre, -->
				<!-- and avoid null pointer in case where there are no top tracks for said genre. -->
				
				<h2><c:out value="${genre}"/></h2>
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
							
							<c:forEach items="${tracksByGenre}"  var="track"> 
	       					<tr> 
	       						<td style="display:none;"><c:out value="${track.inventory_number}"/></td>
								<td id="title"><c:out value="${track.title}"/></td>
								<td id="note">
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
	
							<c:forEach items="${albumsByGenre}"  var="album"> 
	       					<tr>
	       						<td style="display:none;"><c:out value="${album.albumID}"/></td>
								<td id="title"><c:out value="${album.title}"/></td>
								<td id="note">
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