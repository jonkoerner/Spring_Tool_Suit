<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Subscription</title>
</head>
<body>
	<h1>Welcome, ${user.id}</h1>

	
	
	<h2>Your Current Package =</h2>

	<table>
		<thead>
			<tr>
				<th>Name</th>
			</tr>
		</thead>

	</table><br><br>
	
	<a href="/users/logout/"><button>Logout</button></a>

</body>
</html>