<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Idea Likers</title>
</head>
<body>
	<h1>${idea.name}</h1>
	
	<p>Created By: ${idea.creator.name}</p>
	
	<h2>Users who liked your idea:</h2>

	<table>
		<thead>
			<tr>
				<th>Name</th>
			</tr>
		</thead>
		<c:forEach items="${idea.likers}" var="liker">
			<tr>
				<td>${liker.user.name}</td>
			</tr>
		</c:forEach>
	</table><br><br>
	
	<a href="/ideas/${idea.id}/edit"><button>Edit</button></a>
	
	<a href="/users/logout/"><button>Logout</button></a>

</body>
</html>