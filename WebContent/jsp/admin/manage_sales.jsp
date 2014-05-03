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
  <link rel="stylesheet" type="text/css" href="styles/css/manager_Sales.css">
  <link rel="stylesheet" type="text/css" href="styles/css/headerM.css">
  <link rel="stylesheet" type="text/css" href="styles/css/footer2.css">
  
  <script src="js/jquery-1.9.1.min.js"></script>
  <script src="js/jquery.dataTables.min.js"></script>
  <script src="js/gMiddleDiv.min.js"></script>
  <script src="js/myscripts/manage_sales.js"></script>
  
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<div id="gMiddleDivTrack">
		<form id="trackForm" action="<c:url value='/manage_Sales'></c:url>" method="POST">		
			<input id="fti" type="hidden" name="fid">
  			<div>Album Id <input id="ftai" type="text" name="ftai" disabled="disabled"><br></div>
  			<div>Track Title <input id="fttt" type="text" name="fttt" disabled="disabled"><br></div>
			<div>Artist <input id="fta" type="text" name="fta" disabled="disabled"><br></div>
			<div>Cost Price <input id="ftcp" type="text" name="ftcp" disabled="disabled"><br></div>
			<div>List Price <input id="ftlp" type="text" name="ftlp" disabled="disabled"><br></div>
			<div>Sale Price <input id="ftsp" type="text" name="ftsp"><br></div>
			<button id="SubmitT" type="button" style="cursor:pointer">Save</button>
			<button id="middleDivCancel" type="button" style="cursor:pointer">Cancel</button>
		</form>
		
		<form>	
		</form>	
	</div>
	
	
	<div id="gMiddleDivAlbum">
		<form id="albumForm" action="<c:url value='/manage_Sales'></c:url>" method="GET">		
			<input id="fai" type="hidden" name="fai">
  			<div>Album Title <input id="faat" type="text" name="faat" disabled="disabled"><br></div>
			<div>Artist <input id="faa" type="text" name="faa" disabled="disabled"><br></div>
			<div>Tracks <input id="fat" type="text" name="fat" disabled="disabled"><br></div>
			<div>Cost Price <input id="facp" type="text" name="facp" disabled="disabled"><br></div>
			<div>List Price <input id="falp" type="text" name="falp" disabled="disabled"><br></div>
			<div>Sale Price <input id="fasp" type="text" name="fasp"><br></div>
			<button id="SubmitA" type="button" style="cursor:pointer">Save</button>
			<button id="middleDivCancelA" type="button" style="cursor:pointer">Cancel</button>
		</form>
		
		<form>		
		</form>	
	</div>
	
	<%@ include file="headerM.jsp"%>
	
		<div id="content">
		
			<h1>Sales Management</h1>

			
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
								<th class="C_price" id="C_price">Cost P.</th>
								<th class="L_price">List P.</th>
								<th class="S_price">Sale P.</th>
							</tr>
							</thead>
							<tbody>
							
							
							<c:forEach items="${tracks}"  var="track"> 
	       					<tr> 
	       						<td class="ID"><c:out value="${track.inventory_number}"/></td>
								<td class="A_ID" id="A_ID"><c:out value="${track.album_number}"/></td>
								<td class="title" id="title"><c:out value="${track.title}"/></td>
								<td class="artist" id="artist"><c:out value="${track.artist}"/></td>
								<td class="C_price" id="C_price"><c:out value="${track.cost_price}"/></td>
								<td class="L_price"><c:out value="${track.list_price}"/></td>
								<td class="S_price"><c:out value="${track.sale_price}"/></td>
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
								<th class="artist" id="artist">Artist</th>
								<th class="track" id="track">Tracks</th>							
								<th class="C_price" id="C_price">Cost P.</th>
								<th class="L_price">List P.</th>
								<th class="S_price">Sale P.</th>
							</tr>
							</thead>
							<tbody>
	
							<c:forEach items="${albums}"  var="album"> 
	       					<tr>
	       						<td class="ID"><c:out value="${album.albumID}"/></td>
								<td class="title" id="title"><c:out value="${album.title}"/></td>
								<td class="artist" id="artist"><c:out value="${album.artist}"/></td>
								<td class="track" id="track"><c:out value="${album.numOfTracks}"/></td>							
								<td class="C_price" id="C_price"><c:out value="${album.costPrice}"/></td>
								<td class="L_price"><c:out value="${album.listPrice}"/></td>
								<td class="S_price"><c:out value="${album.salePrice}"/></td>
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