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
	<h2>Hi, ${user.name}</h2>
	<a href="/products/dashboard/${user.id}"><button>Go to Dashboard</button></a>
	<a href="/users/logout/"><button>Logout</button></a>
	
	<h2>All Products</h2>
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
			<!-- The forEach tag has the following attributes −
			     Attribute	     Description          Required	Default
					items	Information to loop over	No	     None  -->
					<!-- ideas is what I passed thought from my controller -  model.addAttribute("ideas",iS.all());  -->
					<!-- var is what we are naming items  -->
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