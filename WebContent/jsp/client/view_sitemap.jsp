<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- @author = 1312040 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Woove your music !</title>
  <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
  <link rel="stylesheet" type="text/css" href="styles/css/view_help.css">
  <link rel="stylesheet" type="text/css" href="styles/css/header.css">
  <link rel="stylesheet" type="text/css" href="styles/css/footer2.css">
</head>

<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<%@ include file="header.jsp"%>

		<div id="content">
			
			 <img id="sitemap" usemap="#map" src="images/sitemap.jpg" />
 
			 <map name="map">
			 <area shape="rect" coords="435,245,565,315" title="From the frontpage, you can access every page. It displays the last 3 tracks and the top tracks/albums" href="<%=response.encodeURL("woove_index")%>"/>
			 <area shape="rect" coords="435,510,565,580" href="<c:url value='client_login'></c:url>" />
			 <area shape="rect" coords="435,630,565,710" href="<c:url value='client_login'></c:url>" />
			 <area shape="rect" coords="435,1015,565,1085" href="<c:url value='view_Track?track=1'></c:url>" />
			 <area shape="rect" coords="435,1285,565,1355" href="<c:url value='view_help'></c:url>" />
			 <area shape="rect" coords="225,375,355,445" href="<c:url value='search_result'></c:url>" />
			 <area shape="rect" coords="625,375,755,445" href="<c:url value='view_cart'></c:url>" />
			 <area shape="rect" coords="265,845,395,915" href="<c:url value='viewGenres?genre=rock'></c:url>" />
			 <area shape="rect" coords="595,845,725,915" href="<c:url value='view_Albums?album=1'></c:url>" />
			 </map>

		
		</div>
		
		</div>
			
	<%@ include file="footer.jsp"%>
		
</body>

</html>