<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="/resources/css/adminItem.css">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>AdminItemJsp</title>
</head>
<body>

<%-- 		<form:form action="/admin/item" method="post" modelAttribute="form"> --%>
		

			
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
		
		
		
<!-- 		Selected po staromy be eqvel ta hashcode -->
<%-- 		<form:hidden path="id"/> --%>
<!-- 		<table>	 -->
<!-- 			<tr> -->
<!-- 				<td> -->
			
<%-- 					<form:select path="category"> --%>
<!-- 					<option value="0">Choose category</option> -->
<%-- 						<c:forEach items="${categories}" var="category"> --%>

<%-- 							<c:choose> --%>
<%-- 								<c:when test="${form.category.id eq category.id}"> --%>
<%-- 									<option value="${category.id}" selected="selected">${category.name}</option> --%>
<%-- 								</c:when> --%>
<%-- 								<c:otherwise> --%>
<%-- 									<option value="${category.id}">${category.name}</option> --%>
<%-- 								</c:otherwise> --%>
<%-- 							</c:choose> --%>
							
<%-- 						</c:forEach> --%>
<%-- 					</form:select> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 		Selected po staromy be eqvel ta hashcode -->


<%-- <form:errors path="*"/>			 --%>
<!-- 			<tr> -->
<%-- 				<td><form:errors path="name" /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<%-- 				<td><form:input path="name" placeholder="Name item"/></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 			<tr> -->
<%-- 				<td><form:errors path="price" /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<%-- 				<td><form:input path="price" placeholder="Price item"/></td> --%>
<!-- 			</tr> -->
<!-- 			<tr>			 -->
<!-- 				<td><input type="submit" value="Add item"></td> -->
<!-- 			</tr> -->
		
<!-- 	</table>				 -->

		<div class="row-fluid">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="collapse navbar-collapse" id="">
						<ul class="nav navbar-nav">
							<li><a href="/admin/category">Category</a></li>
							<li class="active"><a href="/admin/item">Item</a><span class="sr-only">(current)</span></li>
							<li><a href="/admin/myUser">MyUser</a></li>
							<li><a href="/admin/role">Role</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>

	<form:form action="/admin/item" method="post" modelAttribute="form" class="form-inline" enctype="multipart/form-data">
		<form:errors path="*"/>
		<form:hidden path="id" />
		<form:hidden path="path" />
		<form:hidden path="version" />
		<custom:hiddenInputs excludeParams="name, id, price, path, version, category"/>
		<div class="form-group">
			<form:select path="category" items="${categories}" itemLabel="name" itemValue="id" class="form-control">
				<option value="0">Category</option>
			</form:select>
		<label for="name"><form:errors path="name"/></label>
			<form:input path="name" id="name" class="form-control"  placeholder="Item name" />
			
			<label for="price"><form:errors path="price" /></label>
			<form:input path="price" id="price" class="form-control" placeholder="Price 00.00" />
			
			<label class="btn btn-default btn-file">
        	
        		Browse <input type="file" name="file" style="display: none;">
    		</label>
			<button type="submit" class="btn btn-primary">Create Item NEWe</button>
		
		
<!-- search		 -->
			<form:form action="/admin/item" class="form-inline" method="get" modelAttribute="filter">
				<custom:hiddenInputs excludeParams="search"/>
				<div class="form-group">
					<form:input path="search" placeholder="search" class="form-control" />
					<button type="submit" class="btn btn-danger">Ok</button>
				</div>
			</form:form>
		
		</div>
			






	</form:form>
<div class="container-fluid">
	<div class="col-md-3  col-xs-12">
			<form:form action="/admin/item" class="form-inline" method="get" modelAttribute="filter">
				<custom:hiddenInputs excludeParams="min, max,  categoryIds, _categoryIds"/>
				<div class="form-group">
					<form:input path="min" placeholder="min" class="form-control"/>
					<form:input path="max" placeholder="max" class="form-control"/>
				</div>
				<div class="form-group">
					<h4>Category</h4>
				</div>
				<div class="form-group">
					<form:checkboxes items="${categories}" path="categoryIds" itemLabel="name" itemValue="id"/><br>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>				
			
				<custom:hiddenInputs excludeParams="search"/>
				<div class="form-group">
					<form:input path="search" placeholder="search" class="form-control" />
					<button type="submit" class="btn btn-danger">Ok</button>
				</div>
			</form:form>
			
		</div>
	<div class="col-md-9">
		<div class="row">
			<div class="col-md-2"><h3>Photo</h3></div>
			<div class="col-md-2"><h3>Item name</h3></div>
			<div class="col-md-2"><h3>Price</h3></div>
			<div class="col-md-2"><h3>Category</h3></div>
			<div class="col-md-2"><h3>Delete</h3></div>
			<div class="col-md-2"><h3>Update</h3></div>
						
		</div>
		<c:forEach items="${page.content}" var="item">
			<div class="row">
				<div class="col-md-2"><img class="img-thumbnail" width="100" src="/images/Item/${item.id}${item.path}?version=${item.version}" /></div>
				<div class="col-md-2"><h4>${item.name}</h4></div>
				<div class="col-md-2"><h4>${item.price}</h4></div>
				<div class="col-md-2"><h4>${item.category.name}</h4></div>
				<div class="col-md-2"><h4><a href="/admin/item/delete/${item.id}<custom:allParams/>">delete</a></h4></div>
				<div class="col-md-2"><h4><a href="/admin/item/update/${item.id}<custom:allParams/>">update</a></h4></div>
			</div>		
		</c:forEach>
	</div>
		
	</div>
	<div class="col-md-2 col-md-offset-9">
			<div class="col-md-6">
				<div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<custom:sort innerHtml="Name asc" paramValue="name"/>
						<custom:sort innerHtml="Name desc" paramValue="name,desc"/>
						<custom:sort innerHtml="Category name asc" paramValue="category.name"/>
						<custom:sort innerHtml="Category name desc" paramValue="category.name,desc"/>
					</ul>
				</div>
			</div>
	</div>

	<div class="col-md-1">
		<custom:size posibleSizes="1,2,5,10" size="${page.size}" title="Page size"/>
	</div>
	<div class="col-md-12 text-center">
	<br>
	<br>
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>

</div>
</body>
</html>