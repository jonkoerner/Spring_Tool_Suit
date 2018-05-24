<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create New Course</title>
</head>
<body>
	<h1>Create a new course</h1>
	<form method="POST" action="/courses/create">
		<p>
		<label for="coursename">Course name</label>
	    <input type="text" id="coursename" name="coursename"/>
	    </p>
		<p>
		<label for="instructor">Instructor</label>
	    <input type="text" id="instructor" name="instructor"/>
	    </p>
	    <p>
		<label for="limit">limit</label>
	    <input type="number" name="limit" id="limit">
	    </p>
	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Create"/>
	</form>
</body>
</html>