<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">

<!-- 
@author = 1312040
@co-author= 0737019
 -->
<head>
<meta charset="utf-8">

<title>Woove your music !</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=2.0, user-scalable=yes" />
	
<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
<link rel="stylesheet" type="text/css" href="styles/css/header.css">
<link rel="stylesheet" type="text/css"  href="styles/css/frontpage.css">
<link rel="stylesheet" type="text/css" href="styles/css/footer2.css">

<%--- check for mobile ---%>
<link
	media="handheld, only screen and (max-width: 480px), only screen and (max-device-width: 480px)"
	href="styles/css/mobileFrontPage.css" type="text/css" rel="stylesheet" />
<script src="js/jquery-1.9.1.min.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
<script src="js/slides.min.jquery.js"></script>
<script src="js/scrollingcarousel.2.0.min.js"></script>
<script>
	$(function() {
		$('#slides').slides({
			preload : true,
			preloadImage : 'img/loading.gif',
			play : 5000,
			hoverPause : true
		});

		$('#genre-carousel1').scrollingCarousel({
			autoScroll : true
		});

		$('#Ad1').click(function() {
			window.open("<c:out value='${ads[0].link}'/>");
		});

		$('#Ad2').click(function() {
			window.open("<c:out value='${ads[1].link}'/>");
		});
	});
</script>
</head>

<body>


	<%@ include file="client/header.jsp"%>

	<div id="content">

		<c:if test="${recentThree.size() > 0}">
			<div id="recentlyViewed">
				<p>Recent</p>
				<c:forEach items="${recentThree}" var="recent">
					<a title="<c:out value="${recent.title}"/>"
						href="view_Track?track=<c:url value='${recent.inventory_number}'></c:url>">
						<img
						src="images/albumCover/<c:out value="${recent.cover_img_name}"/>"
						alt="images/albumSample.png" />
					</a>
				</c:forEach>
			</div>
		</c:if>

		<div id="highlights">
			<div id="myCarousel">
				<div id="slides">
					<div class="slides_container">
						<c:choose>
							<c:when test="${cookie.containsKey('genre')}">
								<c:forEach items="${genreCookieTracks}" var="track"
									varStatus="status">
									<a
										href="view_Track?track=<c:url value='${track.inventory_number}'></c:url>">
										<img
										src="images/albumCover/<c:out value="${track.cover_img_name}"/>"
										alt="images/albumSample.png" height="180px" width="180px" />
										<h2>
											<c:out value="${track.title}" />
										</h2>
										<p>
											<c:out value="${track.artist}" />
											(artist)
										</p>
										<p>
											<fmt:formatNumber value="${track.list_price}" type="currency"
												currencySymbol="$" currencyCode="USD" pattern="$###,##0.00" />
											<strong><fmt:formatNumber value="${sale.sale_price}"
													type="currency" currencySymbol="$" currencyCode="USD"
													pattern="$###,##0.00" />
											</strong>
										</p> 
										<i>Recommended</i> 
										</a>
								</c:forEach>
							</c:when>
							<c:when test="${sessionScope.Client!=null}">
								<c:forEach items="${genreTracks}" var="track" varStatus="status">
									<a
										href="view_Track?track=<c:url value='${track.inventory_number}'></c:url>">
										<img
										src="images/albumCover/<c:out value="${track.cover_img_name}"/>"
										alt="images/albumSample.png" height="180px" width="180px" />
										<h2>
											<c:out value="${track.title}" />
										</h2>
										<p>
											<c:out value="${track.artist}" />
											(artist)
										</p>
										<p>
											<fmt:formatNumber value="${track.list_price}" type="currency"
												currencySymbol="$" currencyCode="USD" pattern="$###,##0.00" />
											<strong><fmt:formatNumber value="${sale.sale_price}"
													type="currency" currencySymbol="$" currencyCode="USD"
													pattern="$###,##0.00" />
											</strong>
										</p> </a>
								</c:forEach>
							</c:when>
							<c:otherwise>

							</c:otherwise>
						</c:choose>
						<c:forEach items="${trackOnSale}" var="sale" varStatus="status">
							<a
								href="view_Track?track=<c:url value='${sale.inventory_number}'></c:url>">
								<img
								src="images/albumCover/<c:out value="${sale.cover_img_name}"/>"
								alt="images/albumSample.png" height="180px" width="180px" />
								<h2>
									<c:out value="${sale.title}" />
								</h2>
								<p>
									<c:out value="${sale.artist}" />
									(artist)
								</p>
								<p>
									<span id="crossed">
									<fmt:formatNumber
											value="${sale.list_price}" type="currency" currencySymbol="$"
											currencyCode="USD" pattern="$###,##0.00" />
									</span>
									<strong>
									<fmt:formatNumber value="${sale.sale_price}"
											type="currency" currencySymbol="$" currencyCode="USD"
											pattern="$###,##0.00" />
									</strong>
								</p> </a>
						</c:forEach>
						<c:forEach items="${albumOnSale}" var="sales" varStatus="status">
							<a
								href="view_Albums?album=<c:url value='${sales.albumID}'></c:url>">
								<img src="images/albumCover/<c:out value="${sales.imgName}"/>"
								alt="images/albumSample.png" height="180px" width="180px" />
								<h2>
									<c:out value="${sales.title}" />
								</h2>
								<p id="artist_sale">
									<c:out value="${sales.artist}" />
									(artist)
								</p>
								<p id="Nb_Track">
									(
									<c:out value="${sales.numOfTracks}" />
									) Tracks
								</p>
								<p id="price_Sale">
									<span id="crossed"><fmt:formatNumber
											value="${sales.listPrice}" type="currency" currencySymbol="$"
											currencyCode="USD" pattern="$###,##0.00" />
									</span><strong><fmt:formatNumber value="${sales.salePrice}"
											type="currency" currencySymbol="$" currencyCode="USD"
											pattern="$###,##0.00" />
									</strong>
								</p> </a>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>



		<div id="survey">

			<h3>Survey</h3>
			<p>
				<c:out value="${surveys.q}" />
			</p>

			<form action="<c:url value='woove_index'></c:url>" method="POST">
				<c:if test="${sessionScope.voted != true}">
					<p>
						<input type="radio" name="survey" value="1" />
				</c:if>
				<c:out value="${surveys.a1}" />
				</p>
				<span id="yesResult"> <!-- Result of the survey -->
					<c:out value="${surveys.a1Count}" /> </span> <br />
				<c:if test="${sessionScope.voted != true}">
					<p>
						<input type="radio" name="survey" value="2" />
				</c:if>
				<c:out value="${surveys.a2}" />
				</p>
				<span id="noResult"> <!-- Result of the survey -->
					<c:out value="${surveys.a2Count}" /> </span> </br>			
					
			</form>
			
				<c:if test="${sessionScope.voted != true}">
					<a><input id="voteSurvey" type="image"
						src="images/vote.png" value="Vote !"/>
					</a>
				</c:if>
			

		</div>

		<div id="genre">

			<h2>GENRES</h2>

			<div id="genre-carousel1" class="genre-carousel">
				<ul>
					<li><a href="<c:url value='viewGenres?genre=jazz'></c:url>"><img
							src="images/genreImg/jazz.jpg" height="125px">
					</a>
					</li>
					<li><a href="<c:url value='viewGenres?genre=dubstep'></c:url>"><img
							src="images/genreImg/dubstep.jpg" height="125px">
					</a>
					</li>
					<li><a href="<c:url value='viewGenres?genre=electro'></c:url>"><img
							src="images/genreImg/electro.jpg" height="125px">
					</a>
					</li>
					<li><a href="<c:url value='viewGenres?genre=hip-hop'></c:url>"><img
							src="images/genreImg/hiphop.jpg" height="125px">
					</a>
					</li>
					<li><a
						href="<c:url value='viewGenres?genre=r&b'>
						<c:param name='genre' value='r&b' ></c:param>
						</c:url>"><img
							src="images/genreImg/r&b.jpg" height="125px"> </a>
					</li>
					<li><a href="<c:url value='viewGenres?genre=reggae'></c:url>"><img
							src="images/genreImg/reggae.jpg" height="125px">
					</a>
					</li>
					<li><a href="<c:url value='viewGenres?genre=rock'></c:url>"><img
							src="images/genreImg/rock.jpg" height="125px">
					</a>
					</li>
					<li><a href="<c:url value='viewGenres?genre=pop'></c:url>"><img
							src="images/genreImg/pop.jpg" height="125px">
					</a>
					</li>
				</ul>
			</div>
		</div>

		<div id="last3">

			<h2>Latest Tracks</h2>

				<c:forEach items="${lastThreeT}" var="lastT" varStatus="status">
					<div class="lastThree">
						<a
							href="view_Track?track=<c:url value='${lastT.inventory_number}'></c:url>">
							<img src="images/albumCover/<c:out value="${lastT.imagePath}"/>"
							alt="images/albumSample.png" height="55px" width="55px" />
						</a>
						<c:choose>
							<c:when test="${status.count==3}">
								<p style="border:none">
									<c:out value="${lastT.title}" />
									<br />
									<c:out value="${lastT.artist}" />
									<br />
									<c:out value="${lastT.album}" />
								</p>
							</c:when>
							<c:otherwise>
								<p style="border-right:solid 2px">
									<c:out value="${lastT.title}" />
									<br />
									<c:out value="${lastT.artist}" />
									<br />
									<c:out value="${lastT.album}" />
								</p>
							</c:otherwise>
						</c:choose>

					</div>
				</c:forEach>

		</div>


		<div id="topP">
			<div id="top">

				<div id="topTrack">

					<h2>Top 3 tracks</h2>


					<c:forEach items="${topTracks}" var="topTrack">
						<div class="topTrackR">
							<a
								href="view_Track?track=<c:url value='${topTrack.inventory_number}'></c:url>">
								<img
								src="images/albumCover/<c:out value="${topTrack.imagePath}"/>"
								alt="images/albumSample.png" height="55px" width="55px" />
							</a>

							<p>
								<c:out value="${topTrack.title}" />
								</br>
								<c:out value="${topTrack.artist}" />
								</br>
								<c:out value="${topTrack.album}" />
							</p>

						</div>
					</c:forEach>

				</div>

				<img id="topSeparation" src="images/top.png" />

				<div id="topAlbum">

					<h2>Top 3 albums</h2>


					<c:forEach items="${topAlbums}" var="topAlbum">
						<div class="topAlbumR">
							<a
								href="view_Albums?album=<c:url value='${topAlbum.albumID}'></c:url>">
								<img
								src="images/albumCover/<c:out value="${topAlbum.imagePath}"/>"
								alt="images/albumSample.png" height="55px" width="55px" />
							</a>

							<p>
								<c:out value="${topAlbum.albumTitle}" />
								</br>
								<c:out value="${topAlbum.artist}" />
							</p>

						</div>
					</c:forEach>


				</div>

			</div>
		</div>

		<div id="RSS">
			<c:forEach var="rssItem" items="${rssFeed}">
			<a href="<c:url value="${rssItem.link}"/>">
				<div class="rssArticle">
					${rssItem.title}
				</div>
			</a>
			</c:forEach>
		</div>


	</div>

	<div id="Ad1">
		<a style="cursor: pointer"><img
			src="images/ads/<c:out value="${ads[0].imgPath}"/>" width="100%"
			height="100%">
		</a>
	</div>

	<div id="Ad2">
		<a style="cursor: pointer"><img
			src="images/ads/<c:out value="${ads[1].imgPath}"/>" width="100%"
			height="100%">
		</a>
	</div>

	</div>

	<%@ include file="client/footer.jsp"%>

</body>

</html>