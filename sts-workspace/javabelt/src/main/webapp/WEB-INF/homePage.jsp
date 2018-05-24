<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Page</title>
</head>
<body>
    <h1>Welcome Page <c:out value="${currentUser.username}"></c:out></h1>
    
    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
    
    <h1>Courses</h1>
    <table style="width:100%">
		  <tr>
			    <th>Courses</th>
			    <th>Instructor</th>
				<th>Signups</th>
			    <th>Action</th>
		  </tr>
		 <!-- for loop through the courses -->
		  <tr>
		    		<td></td>
		    		<td></td>
		    		<td></td>
		    		<td></td>
		  </tr>
	</table>
	<form method="GET" action="/course/new">
        <input type="submit" value="Add a course"/>
	</form>
    
    
    
</body>
</html>