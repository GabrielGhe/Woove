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
  <link rel="stylesheet" type="text/css" href="styles/css/manager_Review.css">
  <link rel="stylesheet" type="text/css" href="styles/css/headerM.css">
  <link rel="stylesheet" type="text/css" href="styles/css/footer2.css">
  
  <script src="js/jquery-1.9.1.min.js"></script>
  <script src="js/jquery.dataTables.min.js"></script>
  <script src="js/gMiddleDiv.min.js"></script>
  <script src="js/myscripts/manage_review_approval.js"></script>
</head>

<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<form id="reviewForm" action="<c:url value='/manage_albumsTracksCrud'></c:url>" method="POST">		
		<input id="freview_id" type="hidden" name="freview_id">
		<input id="fapproval_status" type="hidden" name="fapproval_status">
	</form>	
		
		<form id="myForm" action="<c:url value='manage_review_approvalCRUD'></c:url>" method="POST">
			<input type="hidden" name="review_id" id="review_id"/>
		</form>
	
	<%@ include file="headerM.jsp"%>
	
		<div id="content">
		
			<h1>Review Management</h1>
			
			<div id="displayTrack">
					
					<input id="approve" type="image" src="images/approve.png" />
					<input id="reject" type="image" src="images/reject.png" />
					<div id="reviewContainer" style="margin-right:5px; margin-left:10px;">
						<table id="tableLoop" border="0" cellpadding="0" cellspacing="0" class="tableStyle">
							<thead>
							<tr>
								<th class="T_ID">ID</th>
								<th class="title" id="title">Inventory Number</th>
								<th class="album">Date</th>
								<th class="artist">Client</th>
								<th class="U_ID" id="U_ID">Rating</th>
								<th class="comment">Review</th>
								<th class="user">Approval Status</th>	
							</tr>
							</thead>
							<tbody>
							
							
							<c:forEach items="${reviews}"  var="review"> 
							<tr>
								<td class="T_ID"><c:out value="${review.id}"/></td>
								<td class="title"><c:out value="${review.inventory_number}"/></td>
								<td class="album"><c:out value="${review.date}"/></td>
								<td class="artist"><c:out value="${review.name_of_client}"/></td>
								<td class="U_ID" id="U_ID"><c:out value="${review.rating}"/></td>
								<td class="comment"><c:out value="${review.review}"/></td>
								<td class="user"><c:out value="${review.approval_status}"/></td>	
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