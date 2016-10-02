<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row-fluid">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="collapse navbar-collapse" id="">
					<ul class="nav navbar-nav">
						<li><a href="/admin/category">Category</a></li>
						<li><a href="/admin/item">Item</a></li>
						<li><a href="/admin/myUser">MyUser</a></li>
						<li class="active"><a href="/admin/role">Role</a><span class="sr-only">(current)</span></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	
	
	<form:form action="/admin/role" method="post" modelAttribute="role">	
		<form:hidden path="id"/>
		<table>
			<tr>
				<td><form:errors path="name"/></td>
			</tr>
			<tr>
				<td><form:input path="name"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Add role"/></td>
			</tr>
		</table>
	</form:form>

	<table>
		<tr>
			<th>Role name</th>
		</tr>
		<c:forEach items="${roles}" var="role">
			<tr>
				<td><font color="blue">${role.name}</font></td>	
			
				<td>
					<a href="/admin/role/delete/${role.id}">delete</a>
				</td>
				<td>
					<a href="/admin/role/update/${role.id}">update</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="/admin/">Back to admin panel.jsp</a><br>

</body>
</html>