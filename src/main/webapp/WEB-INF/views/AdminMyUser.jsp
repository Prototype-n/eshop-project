<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Тут буде адмінка користувачів</h3>
	<form:form action="/admin/myUser" method="post" modelAttribute="myUser">
	
	<form:errors path="*"/>
		<form:hidden path="id"/>
		<table>	
			<tr>
				<td>
					<form:select path="role">
					<option value="0">Choose role</option>
						<c:forEach items="${roles}" var="role">
							<c:choose>
								<c:when test="${myUser.role.id eq role.id}">
									<option value="${role.id}" selected="selected">${role.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${role.id}">${role.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</td>
			</tr>
	</table>	
	
	<table>
			<tr>
				<td><form:errors path="name" /></td>
			</tr>
			<tr>
				<td><form:input path="name" placeholder="First Name"/></td>
			</tr>
			
			<tr>
				<td><form:errors path="lastName" /></td>
			</tr>
			<tr>
				<td><form:input path="lastName" placeholder="last Name"/></td>
			</tr>
			
			<tr>
				<td><form:errors path="mail" /></td>
			</tr>
			<tr>
				<td><form:input path="mail" placeholder="Mail"/></td>
			</tr>
			
			<tr>
				<td><form:errors path="phone" /></td>
			</tr>
			<tr>
				<td><form:input path="phone" placeholder="Phone"/></td>
			</tr>
			<tr>
			<td><input type="submit" value="Add admin/user"></td>
			</tr>
	</table>
	</form:form>
	<table>
		<tr>
			<th>MyUser  name</th>
		</tr>
		<c:forEach items="${myUsers}" var="myUser">
			<tr>
				<td>${myUser.name}</td>	
				<td>
					<a href="/admin/myUser/delete/${myUser.id}">delete</a>
				</td>
				<td>
					<a href="/admin/myUser/update/${myUser.id}">update</a>
				</td>		
			</tr>	
		</c:forEach>
	</table>
	
	<table>
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
	</table>
	
	<br>
	<a href="/admin/">Back to admin panel.jsp</a><br>
</body>
</body>
</html>