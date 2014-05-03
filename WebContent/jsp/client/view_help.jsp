<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- author Kimberly Noel -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Woove your music !</title>
 <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
      <link rel="stylesheet" type="text/css" href="styles/css/helpfont.css">
    <link rel="stylesheet" type="text/css" href="styles/css/helppage.css">
    <link rel="stylesheet" type="text/css" href="styles/css/footer2.css">
    <link rel="stylesheet" type="text/css" href="styles/css/header.css">

<script type="text/javascript" src="js/myscripts/helpPageJavaScript.js">
</script>

</head>

<body>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<%@ include file="header.jsp"%>
	
			<div id="content">
			

				   
<div id="selection">
<ul>
<li> <u id="albumlink" onclick="switchContent(id)" onmouseover="bigger(this)" onmouseout="normal(this)" > Album Page </u></li>
<li> <u id="tracklink" onclick="switchContent(id)"  onmouseover="bigger(this)" onmouseout="normal(this)"> Track Page </u> </li>
<li> <u id="loginlink"  onclick="switchContent(id)" onmouseover="bigger(this)" onmouseout="normal(this)"> Login Page </u> </li>
<li> <u id="reglink" onclick="switchContent(id)" onmouseover="bigger(this)" onmouseout="normal(this)"> Registration Page </u> </li>
<li> <u id="profilelink" onclick="switchContent(id)" onmouseover="bigger(this)" onmouseout="normal(this)" > Profile Page </u> </li>
<li> <u id="searchlink" onclick="switchContent(id)" onmouseover="bigger(this)" onmouseout="normal(this)" > Search Page </u> </li> 
<li> <u id="checkoutlink" onclick="switchContent(id)" onmouseover="bigger(this)" onmouseout="normal(this)" > Check Out Page </u> </li>
<li> <u id="paymentlink" onclick="switchContent(id)" onmouseover="bigger(this)" onmouseout="normal(this)" > Payment Page </u> </li> 
<li> <u id="confirmlink" onclick="switchContent(id)" onmouseover="bigger(this)" onmouseout="normal(this)"> Confirmation Page </u> </li>
<li> <u id="downloadlink" onclick="switchContent(id)" onmouseover="bigger(this)" onmouseout="normal(this)" > Download Page </u> </li>

</ul>
</div>
<div id="infocontent"> <h2 id="theheader"> Welcome </h2> <p id="para">Please select a page to receive more information</p> </div>

</div>

</div>
			  
	<%@ include file="footer.jsp"%>


</body>
</html>