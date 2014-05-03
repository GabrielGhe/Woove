<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 
@author = 1312040
 -->
<html lang="en">
<head>
<meta charset="utf-8">
<title>Woops 404 !</title>
<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
<link rel="stylesheet" type="text/css" href="styles/css/error.css" />
</head>

<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div id="all">
	<div id="content">
	
		<img id="mouse" src="images/mouse.png" />
	
		<h1>404</h1>
		
		<h3>Page not found</h3>
		
		<p>Sorry, the page you are looking for does not exist. Please do not blame the mouse for eating it, as you do, it prefers music over cheese.<br/> 
		If you still think this shouldn't have happened, you can check that your <span id="url">url</span> is correct or go back to the <a href="<c:url value='woove_index'></c:url>">home</a> page or you can also find the page you are looking in our <a href="<c:url value='view_help'></c:url>">sitemap</a>.</p>
	
		<img id="cheese" src="images/cheese.png"/>
	</div>
	</div>
	
</body>
</html>