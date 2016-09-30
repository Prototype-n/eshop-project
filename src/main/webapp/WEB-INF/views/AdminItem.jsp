<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>AdminItemJsp</title>
</head>
<body>
<h3>Admin Items</h3>
		<form:form action="/admin/item" method="post" modelAttribute="form">
			
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					<span>Name item</span><td><input name="name"></td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					<span>Price item</span><td><input name="price"></td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					<span>Name category</span><td><input name="nameCategory"></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td><input type="submit" value="Add item"></td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
		
		
		<form:hidden path="id"/>
		<table>	
			<tr>
				<td>
			
					<form:select path="category">
					<option value="0">Choose category</option>
<!-- 							<select name="categoryId"> -->
						<c:forEach items="${categories}" var="category">
<!-- 							<option value="${category.id}">${category.name}</option> -->

							<c:choose>
								<c:when test="${form.category.id eq category.id}">
									<option value="${category.id}" selected="selected">${category.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${category.id}">${category.name}</option>
								</c:otherwise>
							</c:choose>
							
						</c:forEach>
					</form:select>
<!-- 					</select> -->
				</td>
			</tr>

<form:errors path="*"/>			
			<tr>
				<td><form:errors path="name" /></td>
			</tr>
			<tr>
				<td><form:input path="name" placeholder="Name item"/></td>
			</tr>
			<tr>
			<tr>
				<td><form:errors path="price" /></td>
			</tr>
			<tr>
				<td><form:input path="price" placeholder="Price item"/></td>
			</tr>
			<tr>			
			
<!-- 			<tr> -->
<!-- 				<td><input name="name" placeholder="Name item"></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td><input name="price" placeholder="Price"></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
				<td><input type="submit" value="Add item"></td>
			</tr>
		
	</table>				
	</form:form>
	
	<table>	
		<tr>
			<th>item name</th><br>
		</tr>	
		<c:forEach items="${items}" var="item">
			<tr>
				<td>${item.name}</td>
				<td>
					<a href="/admin/item/delete/${item.id}">delete</a>
				</td>
				<td>
					<a href="/admin/item/update/${item.id}">update</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="/admin/">Back to admin panel.jsp</a><br>	

	<div class="col-md-2">
			<div class="col-md-6">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Name asc" paramValue="name"/>
						<custom:sort innerHtml="Name desc" paramValue="name,desc"/>
						<custom:sort innerHtml="Time asc" paramValue="time"/>
						<custom:sort innerHtml="Time desc" paramValue="time,desc"/>
						<custom:sort innerHtml="Country name asc" paramValue="country.name"/>
						<custom:sort innerHtml="Country name desc" paramValue="country.name,desc"/>
					</ul>
				</div>
			</div>
	</div>

	<div class="col-md-6">
		<custom:size posibleSizes="1,2,5,10" size="${page.size}" title="Page size"/>
	</div>

</body>
</html>