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
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=2.0, user-scalable=yes" />
<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
<link rel="stylesheet" type="text/css" href="styles/css/header.css">
<link rel="stylesheet" type="text/css" href="styles/css/cartPage.css">
<link rel="stylesheet" type="text/css" href="styles/css/footer2.css">

<%-- check for mobile --%>
<link
	media="handheld, only screen and (max-width: 480px), only screen and (max-device-width: 480px)"
	href="styles/css/cartPageMobile.css" type="text/css" rel="stylesheet" />
</head>

<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ include file="header.jsp"%>

		<div id="content">

			<img id="cartTitle" src="images/cartTitle.png" />

			<div id="container">


				<p>You have "<c:out value="${Cart.album.size() + Cart.tracks.size()}"/>" items in your cart.</p>

				<form>
					<table>

						<tr>

							<th class="none" class="sample"></th>
							<th class="header" class="album">Item</th>
							<th class="header" class="track">Artist</th>
							<th class="header" class="price">Price</th>
							<th class="none" class="remove"></th>
						
						</tr>
						
						<c:forEach items="${tracksInCart}"  var="track"> 
						<tr class="defaultColor">
							<!-- change to right image -->
							<td class="sample"><img id="trackImage"
								src="images/albumCover/<c:out value="${track.cover_img_name}"/>" width="55" height="55" /></td>
							<!-- change value to actual item name -->
							<td class="album"><c:out value="${track.title}"/></td>
							<!-- change value to actual quantity -->
							<td class="track"><c:out value="${track.artist}"/></td>
							<!-- change price-->
							<td class="price"><fmt:formatNumber value="${track.sale_price != 0?track.sale_price:track.list_price}" type="currency" currencySymbol="$" currencyCode="USD" pattern="$###,##0.00" /></td>
							<td class="remove"><a href="<c:url value="view_cart?removed=yes&removeTrack=${track.inventory_number}"></c:url>">
									<img id="remove" class="removeImage" src="images/trash.png" onclick="remove()" /></a></td>
						</tr>
						</c:forEach>
						
						<c:forEach items="${albumsInCart}"  var="album"> 
						<tr class="colored">
							<!-- change to right image -->
							<td class="sample"><img id="trackImage"
								src="images/albumCover/<c:out value="${album.imgName}"/>" width="55" height="55" /></td>
							<!-- change value to actual item name -->
							<td class="album"><c:out value="${album.title}"/></td>
							<!-- change value to actual quatinty -->
							<td class="track"><c:out value="${album.artist}"/></td>
							<!-- change price-->
							<td class="price"><fmt:formatNumber value="${album.salePrice != 0?album.salePrice:album.listPrice}" type="currency" currencySymbol="$" currencyCode="USD" pattern="$###,##0.00" /></td>
							<td class="remove"><a href="<c:url value="view_cart?removed=yes&removeAlbum=${album.albumID}"></c:url>">
								<img id="remove" class="removeImage" src="images/trash.png" onclick="remove()" /></a></td>
						</tr>
						</c:forEach>


					</table>

				</form>
					<div id="buttondiv">
						<form action="<c:url value='client_payment'></c:url>" method="post" >
							<input type="image" src="images/CheckOut.png">
						</form>
					</div>
			</div>


		</div>


	</div>

	<%@ include file="footer.jsp"%>	

</body>

</html>