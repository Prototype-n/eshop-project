<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset=UTF-8">
<title>Admincategory jsp</title>
</head>
<body>
<h3>Тут буде адмінка категорій</h3>

	<form:form action="/admin/category" method="post" modelAttribute="category">
		
		<form:hidden path="id"/>
		<table>
			<tr>
				<td><form:errors path="name"/></td>
			</tr>
			<tr>
				<td><form:input path="name"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Add country"/></td>
			</tr>
		</table>
	</form:form>

	<table>
		<tr>
			<th>Category name</th>
		</tr>
		<c:forEach items="${categories}" var="category">
			<tr>
				<td><font color="blue">${category.name}</font></td>	
			
				<td>
					<a href="/admin/category/delete/${category.id}">delete</a>
				</td>
				<td>
					<a href="/admin/category/update/${category.id}">update</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="/admin/">Back to admin panel.jsp</a><br>
</body>
</html>