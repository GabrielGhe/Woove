<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 
@author = 1312040
@co-author= 0737019
 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Woove your music !</title>
 <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
  <link rel="stylesheet" type="text/css" href="styles/css/manager_Ad.css">
  <link rel="stylesheet" type="text/css" href="styles/css/headerM.css">
  <link rel="stylesheet" type="text/css" href="styles/css/footer2.css">
</head>

<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ include file="headerM.jsp"%>

		<div id="content">
		
			<h1>Ads, News & Survey <br/> Management</h1>
			
			<div id="ad_Management">
			
				<h2>Ads Management</h2>
				
					<div id="M_ad1">
					
						<h3>Ad1</h3>
						
						<label class="previous">Previous Ads : </label>
						<select>
						<option> </option>
						<c:forEach items="${inactiveAds}" var="ads">
							<option><c:out value="${ads.link}"/></option>
						</c:forEach>
						</select>
						
						<img src="images/ad1Preview.png" height="50px" width="50px"/>
						<form method="post" action="<c:url value='adsNewsSurvey'></c:url>" enctype="multipart/form-data">
						
							<label><strong>Ad image</strong></label><input id ="adImg1" name ="adImg1" type="file" value="<c:out value="${ads[0].imgPath}"/>" /> <br/><br/>
							<label><strong>Ad Link</strong></label><input id ="adUrl1" name ="adUrl1" type="text" size="55" class="url" value="<c:out value="${ads[0].link}"/>" /><br/>
							
							<input type="image" class="submit" value="submit" src="images/applySmall.png"/>
						
						</form>
						
					</div>
					
					<img src="images/top.png" />
					
					<div id="M_ad2">
						
						<h3>Ad2</h3>
						
						<label class="previous">Previous Ads : </label>
						<select>
						<option> </option>
						<c:forEach items="${inactiveAds}" var="ads">
							<option><c:out value="${ads.link}"/></option>
						</c:forEach>
						</select>
						
						<img src="images/ad2Preview.png" height="50px" width="50px"/>
						
						<form method="post" action="<c:url value='manage_adsNewsSurvey'></c:url>"  enctype="multipart/form-data">
						
							<label><strong>Ad image</strong></label><input id="adImg2" name ="adImg2" type="file" /> <br/><br/>
							<label><strong>Ad link</strong></label><input  id="adUrl2" name ="adUrl2" type="text" size="55" class="url" value="<c:out value="${ads[1].link}"/>"/><br/>
							
							<input type="image" class="submit" value="submit" src="images/applySmall.png" />
						
						</form>
						
					</div>
			
			</div>
			<div id="news_Management">
			
				<h2>News Management</h2>
				
				<label>Previous News : </label>
						<select>
						<option> </option>
						<c:forEach items="${inactiveRSS}" var="news">
							<option><c:out value="${news.link}"/></option>
						</c:forEach> 
						</select>
				<br/>
		<!-- 
				<div id="currentN">
				
					<p><strong>Current News</strong></p>
					<img src="images/news1.jpg" />
					
				</div>

				<form method="post" action="<c:url value='manage_adsNewsSurvey'></c:url>">
				
					<strong><label>News</label></strong><select>
						<option>1</option>
						<option>2</option>
						<option>3</option>
					</select><br/>
					
					<label><strong>Ad image</strong></label><input type="file" /> <br/><br/>
		 -->		
			
					<label id="rss"><strong>RSS link</strong></label><input id="rssUrl" name="rssUrl" type="text" size="55" class="url" value="<c:out value="${rss.link}"/>"/><br/>
							
					<input type="image" class="submit" value="submit" src="images/applySmall.png" />
				
				</form>
				
			</div>
			<div id="survey_Management">
			
				<h2>Survey Management</h2>
				
				<label id="Psurvey">Previous Survey : </label>
						<select>
						<c:forEach items="${inactiveSurveys}" var="survey">
							<option><c:out value="${survey.q}"/></option>
						</c:forEach>
						</select>
				
				<form method="post" action="<c:url value='manage_adsNewsSurvey'></c:url>">
				
					<label><strong>Question :</strong></label><br/>
					<input type="text" name="surveyQ" size="60" value="<c:out value="${surveys.q}"/>"/><br/>
					<label><strong>Answers :</strong></label><br/>
					<label><strong>Top</strong></label><input type="text" name="surveyA1" size="15" value="<c:out value="${surveys.a1}"/>"/>
					<label><strong>Bottom</strong></label><input type="text" name="surveyA2" size="15" value="<c:out value="${surveys.a2}"/>"/>
				
					<input type="image" id="submitS" value="submit" src="images/applySmall.png" />
					
				</form>
			
			</div>

		
		</div>
		
	</div>
	
	<%@ include file="../client/footer.jsp"%>
</body>
</html>