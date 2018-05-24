<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
	<h2>Create a new show</h2>
    <form method="POST" action="/show/new">
        <p>
            <label for="name">Title</label>
            <input type="text" id="name" name="name"/>
        </p>
        <p>
            <label for="network">Network</label>
            <input type="text" id="network" name="network"/>
        </p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Create"/>
    </form>
</body>
</html>