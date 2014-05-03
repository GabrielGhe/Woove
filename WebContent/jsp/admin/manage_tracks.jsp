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
  <link rel="stylesheet" type="text/css" href="styles/css/manager_AlbumTrack.css">
  <link rel="stylesheet" type="text/css" href="styles/css/headerM.css">
  <link rel="stylesheet" type="text/css" href="styles/css/footer2.css">
  
  <script src="js/jquery-1.9.1.min.js"></script>
  <script src="js/jquery.dataTables.min.js"></script>
  <script src="js/gMiddleDiv.min.js"></script>
  <script src="js/myscripts/manage_tracks.js"></script>
  
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<div id="gMiddleDivTrack">
		<form id="trackForm" action="<c:url value='/manage_albumsTracksCrud'></c:url>" method="POST">		
			<input id="fti" type="hidden" name="fid">
  			<div>Album Id <input id="ftai" type="text" name="ftai"><br></div>
  			<div>Track Title <input id="fttt" type="text" name="fttt"><br></div>
			<div>Artist <input id="fta" type="text" name="fta"><br></div>
			<div>Songwriter <input id="fts" type="text" name="fts"><br></div>
			<div>Length <input id="ftl" type="text" name="ftl"><br></div>
			<div>Track Number <input id="fttn" type="text" name="fttn"><br></div>
			<div>Genre <input id="ftg" type="text" name="ftg"><br></div>
			<div>Album Cover <input id="ftac" type="text" name="ftac"><br></div>
			<div>Cost Price <input id="ftcp" type="text" name="ftcp"><br></div>
			<div>List Price <input id="ftlp" type="text" name="ftlp"><br></div>
			<div>Sale Price <input id="ftsp" type="text" name="ftsp"><br></div>
			<div>Total Sales <input id="fttsp" type="text" name="fttsp"><br></div>
			<div>Date Entered <input id="ftde" type="text" name="ftde"><br></div>
			<div>Removal <input id="ftr" type="text" name="ftr"><br></div>
			<button id="SubmitT" type="button" style="cursor:pointer">Save</button>
			<button id="NewT" type="button" style="cursor:pointer">New</button>
			<button id="DeleteT" type="button" style="cursor:pointer">Delete</button>
			<button id="middleDivCancel" type="button" style="cursor:pointer">Cancel</button>
		</form>
		
		<form>		
  			<input id="hftai" type="hidden" name="hftai">
  			<input id="hfttt" type="hidden" name="hfttt">
			<input id="hfta" type="hidden" name="hfta">
			<input id="hfts" type="hidden" name="hfts">
			<input id="hftl" type="hidden" name="hftl">
			<input id="hfttn" type="hidden" name="hfttn">
			<input id="hftg" type="hidden" name="hftg">
			<input id="hftac" type="hidden" name="hftac">
			<input id="hftcp" type="hidden" name="hftcp">
			<input id="hftlp" type="hidden" name="hftlp">
			<input id="hftsp" type="hidden" name="hftsp">
			<input id="hfttsp" type="hidden" name="fttsp">
			<input id="hftde" type="hidden" name="hftde">
			<input id="hftr" type="hidden" name="hftr">
		</form>	
	</div>
	
	
	<div id="gMiddleDivAlbum">
		<form id="albumForm" action="<c:url value='/manage_albumsTracksCrud'></c:url>" method="GET">		
			<input id="fai" type="hidden" name="fai">
  			<div>Album Title <input id="faat" type="text" name="faat"><br></div>
  			<div>Release <input id="fare" type="text" name="fare"><br></div>
			<div>Artist <input id="faa" type="text" name="faa"><br></div>
			<div>Label <input id="fal" type="text" name="fal"><br></div>
			<div>Tracks <input id="fat" type="text" name="fat"><br></div>
			<div>Date Entered <input id="fade" type="text" name="fade"><br></div>
			<div>Cost Price <input id="facp" type="text" name="facp"><br></div>
			<div>List Price <input id="falp" type="text" name="falp"><br></div>
			<div>Sale Price <input id="fasp" type="text" name="fasp"><br></div>
			<div>Total Sales <input id="fatsp" type="text" name="fatsp">
				<a href="manage_Sales?album=<c:url value="${album.albumID}"></c:url>"><button id="SetSales" type="button" style="cursor:pointer" value="Set Sales">Set Sales</button></a>
				<br/>
			</div>
			<div>Genre <input id="fagre" type="text" name="fagre"><br></div>
			<div>Image <input id="faimg" type="text" name="faimg"><br></div>
			<div>Removal <input id="far" type="text" name="far"><br></div>
			<button id="SubmitA" type="button" style="cursor:pointer">Save</button>
			<button id="NewA" type="button" style="cursor:pointer">New</button>
			<button id="DeleteA" type="button" style="cursor:pointer">Delete</button>
			<button id="middleDivCancelA" type="button" style="cursor:pointer">Cancel</button>
		</form>
		
		<form>		
  			<input id="hfaat" type="hidden" name="hfaat">
  			<input id="hfare" type="hidden" name="hfare">
			<input id="hfaa" type="hidden" name="hfaa">
			<input id="hfal" type="hidden" name="hfal">
			<input id="hfat" type="hidden" name="hfat">
			<input id="hfade" type="hidden" name="hfade">
			<input id="hfacp" type="hidden" name="hfacp">
			<input id="hfalp" type="hidden" name="hfalp">
			<input id="hfasp" type="hidden" name="hfasp">
			<input id="hfatsp" type="hidden" name="hfatsp">
			<input id="hfagre" type="hidden" name="hfagre">
			<input id="hfaimg" type="hidden" name="hfaimg">
			<input id="hfar" type="hidden" name="hfar">
		</form>	
	</div>
	
	<%@ include file="headerM.jsp"%>
	
		<div id="content">
		
			<h1>Album & Track Management</h1>

			
				<div id="displayTrack">
					<div id="toggleTable">
						<div id="showTracks" style="cursor:pointer">Tracks</div>
						<div id="showAlbums" style="cursor:pointer">Albums</div>
					</div>

					<div id="tracksContainer" style="margin-right:5px; margin-left:10px;">
						<table id="tableLoop" border="0" cellpadding="0" cellspacing="0" class="tableStyle">
							<thead>
							<tr>
								<th class="ID">ID</th>
								<th class="A_ID" id="A_ID">A_ID</th>
								<th class="title" id="title">Track Title</th>
								<th class="artist" id="artist">Artist</th>
								<th class="writer" id="writer">Songwriter</th>
								<th class="length" id="length">Length</th>
								<th class="n°">n°</th>
								<th class="trackGenre">Genre</th>
								<th class="A_cover">Album Cover</th>
								<th class="C_price" id="C_price">Cost P.</th>
								<th class="L_price">List P.</th>
								<th class="S_price">Sale P.</th>
								<th class="T_Sales">Total Sales</th>
								<th class="date" id="date">Date entered</th>
								<th class="removal" id="removal">Removal</th>
							</tr>
							</thead>
							<tbody>
							
							
							<c:forEach items="${tracks}"  var="track"> 
	       					<tr> 
	       						<td class="ID"><c:out value="${track.inventoryNumber}"/></td>
								<td class="A_ID" id="A_ID"><c:out value="${track.albumNumber}"/></td>
								<td class="title" id="title"><c:out value="${track.title}"/></td>
								<td class="artist" id="artist"><c:out value="${track.artist}"/></td>
								<td class="writer" id="writer"><c:out value="${track.writer}"/></td>
								<td class="length" id="length"><c:out value="${track.trackLength}"/></td>
								<td class="n°"><c:out value="${track.trackNumber}"/></td>
								<td class="trackGenre"><c:out value="${track.category}"/></td>
								<td class="A_cover"><c:out value="${track.coverImgName}"/></td>
								<td class="C_price" id="C_price"><c:out value="${track.costPrice}"/></td>
								<td class="L_price"><c:out value="${track.listPrice}"/></td>
								<td class="S_price"><c:out value="${track.salePrice}"/></td>
								<td class="T_Sales"><c:out value="${track.totalSales}"/></td>
								<td class="date" id="date"><c:out value="${track.dateEntered}"/></td>
								<td class="removal" id="removal"><c:out value="${track.removalStatus}"/></td>
							</tr>
							</c:forEach>
	    					</tbody>
					</table>
				</div>
				
				<div id="albumsContainer" style="margin-right:5px; margin-left:10px;">
						<table id="tableLoopA" border="0" cellpadding="0" cellspacing="0" class="tableStyle">
							<thead>
							<tr>
								<th class="ID">ID</th>
								<th class="title" id="title">Album Title</th>
								<th class="release">Release</th>
								<th class="artist" id="artist">Artist</th>
								<th class="label" id="label">Label</th>
								<th class="track" id="track">Tracks</th>
								<th class="date" id="date">Date entered</th>							
								<th class="C_price" id="C_price">Cost P.</th>
								<th class="L_price">List P.</th>
								<th class="S_price">Sale P.</th>
								<th class="T_Sales">Total Sales</th>
								<th class="label">Image</th>
								<th class="label">Genre</th>
								<th class="removal" id="removal">Removal</th>
							</tr>
							</thead>
							<tbody>
	
							<c:forEach items="${albums}"  var="album"> 
	       					<tr>
	       						<td class="ID"><c:out value="${album.albumID}"/></td>
								<td class="title" id="title"><c:out value="${album.title}"/></td>
								<td class="release"><c:out value="${album.releaseDate}"/></td>
								<td class="artist" id="artist"><c:out value="${album.artist}"/></td>
								<td class="label" id="label"><c:out value="${album.label}"/></td>
								<td class="track" id="track"><c:out value="${album.numOfTracks}"/></td>
								<td class="date" id="date"><c:out value="${album.dateEntered}"/></td>							
								<td class="C_price" id="C_price"><c:out value="${album.costPrice}"/></td>
								<td class="L_price"><c:out value="${album.listPrice}"/></td>
								<td class="S_price"><c:out value="${album.salePrice}"/></td>
								<td class="T_Sales"><c:out value=""/>${album.totalSales}</td>
								<td class="label"><c:out value="${album.imgName}"/></td>
								<td class="label"><c:out value="${album.genre}"/></td>
								<td class="removal" id="removal"><c:out value="${album.removalStatus}"/></td>
							</tr>
							</c:forEach>
	    					</tbody>
					</table>
				</div>
					
			</div>
		
		</div>
		
	</div>
	
	<%@ include file="../client/footer.jsp"%>
	
</body>
</html>