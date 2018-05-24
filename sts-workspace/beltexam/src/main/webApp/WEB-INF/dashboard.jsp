<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Dasboard</title>
</head>
<body>
	<h2>Hi ${user.name}</h2>
	
	<h2>Sell Product</h2>
	
	<form:form action="/products/new" method="POST" modelAttribute="product">
        <p>
            <form:label path="name">Name:
            	<form:errors path="name"></form:errors>
            	<form:input path="name"></form:input>
            </form:label>
        </p>
        <p>
            <form:label path="amount">Amount:
            	<form:errors path="amount"></form:errors>
            	<form:input path="amount"></form:input>
            </form:label>
        </p>
        <input type="submit" value="Sell"/>
    </form:form>
    <table>
		<thead>
			<tr>
				<th>Product</th>
				<th>Created By</th>
				<th>Date Posted</th>
				<th>Amount</th>
				<th>Action</th>
			</tr>
		</thead>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.name}</td>
					<td>${product.seller.name}</td>
					<td>${product.ceatedAt}</td>
					<td>${product.amount}</td>
					<td><a href="users/new/">Buy</a>
				</tr>	
			</c:forEach>
	</table><br><br>
    
  
</body>
</html>