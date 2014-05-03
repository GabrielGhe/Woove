<!--  @author = 1312040 -->
	<div id="white">

		<p id="quote">
			<i> <q>Music is pure freedom...</q>
			</i>
		</p>

	</div>


	<div id="all">

		<div id="header">

			<div id="menu">

				<img id="menuImage" src="images/menu.png" />
				
				 <p id="profile"><a href="<c:url value='client_login'></c:url>" class="link">My Profile</a></p>
				 <c:if test="${Client.clientID != null}">
				 <p id="disconnect"><a href="<c:url value="client_login?logout=yes"/>">Log out</a></p>
				 </c:if>
				
				<a href="<c:url value='view_cart'></c:url>" class="link">
					
					<img id="cartImage" src="images/cart2.png" />
					
					<p id="cart">
						<%-- here you count the number of article you have in your cart --%>
						(<c:out value="${Cart.album.size() + Cart.tracks.size()}"/>) Items
					</p>
				</a>

			</div>

			<a href="<c:url value='woove_index'></c:url>"> <img id="logo"
				src="images/logofinal3.png" />
			</a>

			<div id="research">

				<a href="#"><img src="images/zoom.png" onclick="searchform.submit()"/> </a>

				<form id="searchform" action="<c:url value='search_result'></c:url>" method="GET">
					<input type="text" name="search" id="search"
						placeholder="Search a track, an album, an artist" maxlength="128"
						size="35" />
				</form>

			</div>

		</div>