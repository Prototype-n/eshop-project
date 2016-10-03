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
	<div class="row-fluid">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="collapse navbar-collapse" id="">
					<ul class="nav navbar-nav">
						<li><a href="/admin/category">Category</a></li>
						<li><a href="/admin/item">Item</a></li>
						<li class="active"><a href="/admin/myUser">MyUser</a><span class="sr-only">(current)</span></li>
						<li><a href="/admin/role">Role</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>

	<form:form action="/admin/myUser" method="post" modelAttribute="myUser">
	
	<form:errors path="*"/>
	<form:hidden path="id"/>
		
	<custom:hiddenInputs excludeParams="id, login, name, lastName, phone, mail, role"/>
		
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
	
	<div class="form-group">
			<label for="login"><form:errors path="login" /></label>
				<form:input path="login" id="login" class="form-control" placeholder="Login"/>
			<label for="name"><form:errors path="name" /></label>
				<form:input path="name" id="name" class="form-control" placeholder="First Name"/>
			<label><form:errors path="lastName" /></label>
				<form:input path="lastName" id="lastName" class="form-control"  placeholder="last Name"/>
			<label><form:errors path="mail" /></label>
				<form:input path="mail" id="mail" class="form-control"   placeholder="Mail"/>
			<label><form:errors path="phone" /></label>
				<form:input path="phone" id="phone" class="form-control" placeholder="Phone"/>
			<label><form:errors path="password" /></label>
				<form:input path="password" id="password" class="form-control" placeholder="password"/>

				<br>
			<button type="submit" class="btn btn-primary">Add admin/user</button>
<!-- 			<input type="submit" value="Add admin/user">	 -->
	</div>

	</form:form>
	
		<div class="row">		
			<div class="col-md-1"><h3>Login</h3></div>
			<div class="col-md-2"><h3>LastName</h3></div>
			<div class="col-md-2"><h3>Name</h3></div>
			<div class="col-md-2"><h3>Mail</h3></div>
			<div class="col-md-2"><h3>Phone</h3></div>
			<div class="col-md-1"><h3>Role</h3></div>
			<div class="col-md-1"><h3>Delete</h3></div>
			<div class="col-md-1"><h3>Update</h3></div>
		</div>	
	
			<br>
			<c:forEach items="${page.content}" var="myUser">
				<div class="row">
					<div class="col-md-1">${myUser.login}</div>
					<div class="col-md-2">${myUser.lastName}</div>
					<div class="col-md-2">${myUser.name}</div>
					<div class="col-md-2">${myUser.mail}</div>
					<div class="col-md-2">${myUser.phone}</div>
					<div class="col-md-1">${myUser.role.name}</div>
					<div class="col-md-1"><a href="/admin/myUser/delete/${myUser.id}<custom:allParams/>">delete</a></div>
					<div class="col-md-1"><a href="/admin/myUser/update/${myUser.id}<custom:allParams/>">update</a></div>
				</div>
			</c:forEach>
					
		<br>	
		<div class="col-md-2 col-md-offset-9">
			<div class="col-md-6">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Name asc" paramValue="name"/>
						<custom:sort innerHtml="Name desc" paramValue="name,desc"/>
						<custom:sort innerHtml="Role name asc" paramValue="role.name"/>
						<custom:sort innerHtml="Role name desc" paramValue="role.name,desc"/>
					</ul>
				</div>
			</div>
	</div>

	<div class="col-md-1">
		<custom:size posibleSizes="1,2,5,10" size="${page.size}" title="Page size"/>
	</div>
	<div class="col-md-12 text-center">
	<br>
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>
	
	<br>
	<a href="/admin/">Back to admin panel.jsp</a><br>
</body>
</body>
</html>