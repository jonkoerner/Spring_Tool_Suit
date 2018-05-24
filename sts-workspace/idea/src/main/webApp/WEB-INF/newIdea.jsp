<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Idea</title>
</head>
<body>
	<h1>Create a new idea</h1>

	<form:form action="/ideas/new" method="POST" modelAttribute="idea">
		<p>
            <form:label path="name">Content:
            	<form:errors path="name"></form:errors>
            	<form:input path="name"></form:input>
            </form:label>
        </p>
        
        <input type="submit" value="Create"/>
	</form:form>
</body>
</html>