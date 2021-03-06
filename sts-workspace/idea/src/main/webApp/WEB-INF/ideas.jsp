<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Idea List</title>
</head>
<body>
	<h1>Welcome, ${user.name}</h1>
	
	<h2>Ideas</h2>
	
	<a href="/ideas/low">Low Likes</a> &nbsp;
	<a href="/ideas/high">High Likes</a>
	
	<table>
		<thead>
			<tr>
				<th>Idea</th>
				<th>Created By</th>
				<th>Likes</th>
				<th>Action</th>
			</tr>
		</thead>
			<!-- The forEach tag has the following attributes −
			     Attribute	     Description          Required	Default
					items	Information to loop over	No	     None  -->
					<!-- ideas is what I passed thought from my controller -  model.addAttribute("ideas",iS.all());  -->
					<!-- var is what we are naming items  -->
			<c:forEach items="${ideas}" var="idea">
				<tr>
					<td><a href="/ideas/${idea.id}">${idea.name}</a></td>
					<td>${idea.creator.name}</td>
					<td>${idea.getLikers().size() }</td>
					<!-- this would print whole idea object -->
					<%-- td {idea} td --%>
					<!-- The c:set> tag is JSTL-friendly version of the setProperty action. 
					The tag is helpful because it evaluates an expression and uses the results
					to set a value of a JavaBean or a java.util.Map object.  -->
					<!--  
					The c:set tag has the following attributes −
					   Attribute	    Description	          						           Required	  Default
						Value	     Information to save	  							         No	      body
						target		 Name of the variable whose property should be modified	     No	      None
						property	 Property to modify										     No	      None
						var	         Name of the variable to store information	                 No	      None
						scope	     Scope of variable to store information	                     No	      Page
					-->
					<c:set var="exists" scope="page" value="2"/>
					<c:forEach items="${idea.likers}" var="liker">
						<!-- this is a test to see if this liker is  -->
						<c:if test="${liker.user.id == user.id}">	
							<c:set var="exists" value="1"/>
							<td><a href="/ideas/unlike/${idea.id}">Unlike</a></td>
						</c:if>
					</c:forEach>
						<c:if test="${exists == 2}">
							<td><a href="/ideas/like/${idea.id}">Like</a></td>
						</c:if>
					<c:set var="exists" scope="page" value="2"/>
				</tr>	
			</c:forEach>
	</table><br><br>
	
	<a href="/ideas/new"><button>Create an idea</button></a>

</body>
</html>