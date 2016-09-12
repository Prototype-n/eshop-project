<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
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
		<c:forEach items="${page.content}" var="category">
			<tr>
				<td><font color="blue">${category.name}</font></td>	
			
				<td>
<%-- 					<a href="/admin/category/delete/${category.id}">delete</a> --%>
					
					<a href="/admin/category/delete/${category.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}"">delete</a>
					
				</td>
				<td>
					<a href="/admin/category/update/${category.id}">update</a>
				</td>
			</tr>
		</c:forEach>
		
			<tr>
				<c:if test="${page.hasPrevious()}">
					<td><a href="?page=${page.number}&size=${page.size}&sort=${param['sort']}">Previous</a></td>
				</c:if>
				<c:if test="${page.hasNext()}">
					<td><a href="?page=${page.number+2}&size=${page.size}&sort=${param['sort']}">Next</a></td>
				</c:if>
			</tr>
			<tr>
				<td><a href="?page=1&size=1&sort=${param['sort']}">1</a></td>
				<td><a href="?page=1&size=5&sort=${param['sort']}">5</a></td>
				<td><a href="?page=1&size=10&sort=${param['sort']}">10</a></td>
				<td><a href="?page=1&size=20&sort=${param['sort']}">20</a></td>
			</tr>
			<tr>
				<td><a href="?page=1&size=${page.size}&sort=name">Name asc</a></td>
				<td><a href="?page=1&size=${page.size}&sort=name,desc">Name desc</a></td>
			</tr>
		
		
		
	</table>
	
	<table>
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
	</table>
	
	<br>
	<a href="/admin/">Back to admin panel.jsp</a><br>
</body>
</html>