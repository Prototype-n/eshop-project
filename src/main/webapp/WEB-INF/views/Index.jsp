<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="/resources/css/adminItem.css">


<security:authorize access="!isAuthenticated()">
	<br>
	<br>
	<div class="hero-unit col-md-6 well col-md-offset-1">
	  <h1>Hello, world!</h1>
  		<p>This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
  		<p>
		<a href="/registration" class="btn btn-primary btn-large">Register Now!</a>
  		</p>
	</div>
</security:authorize>


<security:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
<a href="/admin">Admin panel</a>
</security:authorize>

<security:authorize access="isAuthenticated() and hasRole('ROLE_USER')">
		
	<!-- search		 -->
	<div>
		<form:form action="/user/item" class="form-inline" method="get" modelAttribute="filter">
			<custom:hiddenInputs excludeParams="search"/>
				<div class="form-group">
					<form:input path="search" placeholder="search" class="form-control" />
				<button type="submit" class="btn btn-danger">Ok</button>
				</div>
		</form:form>
	</div>				

	<div class="container-fluid">
		<div class="col-md-3  col-xs-12">
<%-- 				<form:form action="/index" class="form-inline" method="get" modelAttribute="filter"> --%>
<%-- 				<custom:hiddenInputs excludeParams="min, max"/> --%>
<!-- 				<div class="form-group"> -->
<%-- 					<form:input path="min" placeholder="min" class="form-control"/> --%>
<%-- 					<form:input path="max" placeholder="max" class="form-control"/> --%>
<!-- 				</div> -->
<!-- 				<div class="form-group"> -->
<!-- 					<button type="submit" class="btn btn-primary">Ok</button> -->
<!-- 				</div>				 -->
<%-- 				</form:form>		 --%>
			
			<c:forEach items="${categories}" var="category">
				<div class="sidebar-nav"><a href="item/findItemByCategory/${category.id}">${category.name}</a></div>
			</c:forEach>
		</div>
		<div class="col-md-9">
<%-- 			<c:forEach items="${listRandom}" var="item"> --%>
<!-- 				<div class="row"> -->
<%-- 					<div class="col-md-2"><img class="img-thumbnail" width="100" src="/images/Item/${item.id}${item.path}?version=${item.version}" /></div> --%>
<%-- 					<div class="col-md-2"><h4>${item.name}</h4></div> --%>
<%-- 					<div class="col-md-2"><h4>${item.price} грн.</h4></div> --%>
<%-- 					<div class="col-md-2"><h4><a href="/item/addToCart/${item.id}">Add to cart</a></h4></div> --%>
<!-- 				</div>		 -->
<%-- 			</c:forEach> --%>

			<c:forEach items="${listRandom}" var="item">
				<div class="col-md-3">
					<div class="row-md-2"><img class="img-thumbnail" width="100" src="/images/Item/${item.id}${item.path}?version=${item.version}" /></div>
					<div class="row-md-2"><h4>${item.name}</h4></div>
					<div class="row-md-2"><h4>${item.price} грн.</h4></div>
					<div class="row-md-2"><h4><a href="/item/addToCart/${item.id}">Add to cart</a></h4></div>
				</div>		
			</c:forEach>



		</div>
			
		</div>
<!-- 		<div class="col-md-2 col-md-offset-9"> -->
<!-- 				<div class="col-md-6"> -->
<!-- 					<div class="dropdown"> -->
<!-- 						<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort <span class="caret"></span> -->
<!-- 						</button> -->
<!-- 						<ul class="dropdown-menu"> -->
<%-- 							<custom:sort innerHtml="Name asc" paramValue="name"/> --%>
<%-- 							<custom:sort innerHtml="Name desc" paramValue="name,desc"/> --%>
<%-- 							<custom:sort innerHtml="Category name asc" paramValue="category.name"/> --%>
<%-- 							<custom:sort innerHtml="Category name desc" paramValue="category.name,desc"/> --%>
<!-- 						</ul> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 		</div> -->
	
<!-- 		<div class="col-md-1"> -->
<%-- 			<custom:size posibleSizes="1,2,5,10" size="${page.size}" title="Page size"/> --%>
<!-- 		</div> -->
<!-- 		<div class="col-md-12 text-center"> -->
<!-- 		<br> -->
<!-- 		<br> -->
<%-- 			<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" /> --%>
<!-- 		</div> -->
	</div>
</security:authorize>